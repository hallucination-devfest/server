package com.cheep.hallucination.service.room;

import com.cheep.hallucination.domain.Character;
import com.cheep.hallucination.domain.Keyword;
import com.cheep.hallucination.domain.Room;
import com.cheep.hallucination.domain.User;
import com.cheep.hallucination.dto.response.CharacterListDto;
import com.cheep.hallucination.dto.response.CreateRoomResponseDto;
import com.cheep.hallucination.exception.CommonException;
import com.cheep.hallucination.exception.ErrorCode;
import com.cheep.hallucination.repository.CharacterRepository;
import com.cheep.hallucination.repository.KeywordRepository;
import com.cheep.hallucination.repository.RoomRepository;
import com.cheep.hallucination.repository.UserRepository;
import com.cheep.hallucination.usecase.room.CreateRoomUsecase;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateRoomService implements CreateRoomUsecase {
    private final UserRepository userRepository;
    private final KeywordRepository keywordRepository;
    private final CharacterRepository characterRepository;
    private final RoomRepository roomRepository;

    @Override
    public CreateRoomResponseDto execute(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));

        Keyword randKeyword = keywordRepository.findRandomKeyword();
        Character randCharacter = characterRepository.findRandomCharacter();

        Room room = roomRepository.findMostRecentByUserId(user)
                .orElse(null);

        Room newRoom = null;

        if (room == null) {
             newRoom = roomRepository.save(
                    Room.builder()
                                .round(1)
                                .user(user)
                                .keyword(randKeyword)
                                .liarCharacter(randCharacter)
                                .questionCount(3)
                                .build()
            );
        } else {
            if(room.getIsSuccess()) {
                newRoom = switch (room.getRound()) {
                    case 1 -> saveNewRoom(2, user, randCharacter, randKeyword, 2);
                    case 2 -> saveNewRoom(3, user, randCharacter, randKeyword,1);
                    case 3 -> saveNewRoom(1, user, randCharacter, randKeyword, 3);
                    default -> newRoom;
                };
            } else {
                newRoom = saveNewRoom(1, user, randCharacter, randKeyword, 3);
            }

        }
        List<Character> characterList = characterRepository.findAll();

        List<CharacterListDto> characterListDtos = characterList.stream()
                .map(character -> CharacterListDto.builder()
                        .characterId(character.getId())
                        .characterName(character.getName())
                        .build())
                .toList();

        return CreateRoomResponseDto.builder()
                .characterList(characterListDtos)
                .round(newRoom.getRound())
                .category(newRoom.getKeyword().getCategory().getName())
                .chatCount(newRoom.getQuestionCount())
                .build();

    }

    private Room saveNewRoom(Integer round, User user, Character randCharacter, Keyword randKeyword, Integer questionCount) {
        return roomRepository.save(Room.builder()
                .round(round)
                .user(user)
                .keyword(randKeyword)
                .liarCharacter(randCharacter)
                .questionCount(questionCount)
                .build());
    }
}
