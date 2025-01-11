package com.cheep.hallucination.service.chat;

import com.cheep.hallucination.domain.Character;
import com.cheep.hallucination.domain.Room;
import com.cheep.hallucination.dto.response.ChatHistoryDto;
import com.cheep.hallucination.dto.response.InterviewHistoryResponseDto;
import com.cheep.hallucination.exception.CommonException;
import com.cheep.hallucination.exception.ErrorCode;
import com.cheep.hallucination.repository.CharacterRepository;
import com.cheep.hallucination.repository.RoomInterviewRepository;
import com.cheep.hallucination.repository.RoomRepository;
import com.cheep.hallucination.usecase.chat.InterviewChatHistoryUsecase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class InterviewChatHistoryService implements InterviewChatHistoryUsecase {

    private final RoomRepository roomRepository;
    private final CharacterRepository characterRepository;
    private final RoomInterviewRepository roomInterviewRepository;

    @Override
    public InterviewHistoryResponseDto execute(Long roomId, Long characterId) {

        // 방 정보 조회
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_ROOM));

        // 캐릭터 정보 조회
        Character character = characterRepository.findById(characterId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_CHARACTER));

        // 방과 캐릭터 정보를 이용하여 채팅 내역 조회
        return roomInterviewRepository.findByRoomAndCharacter(room, character)
                // 채팅 내역이 있을 경우 InterviewHistoryResponseDto로 변환
                .filter(riList -> !riList.isEmpty()) // 빈 리스트인지 확인
                .map(riList -> {
                    List<ChatHistoryDto> chatHistoryList = riList.stream()
                            .map(ri -> ChatHistoryDto.builder()
                                    .id(ri.getId())
                                    .question(ri.getQuestion())
                                    .answer(ri.getAnswer())
                                    .build())
                            .toList();
                    return InterviewHistoryResponseDto.builder()
                            .isEmpty(false)
                            .chatHistoryList(chatHistoryList)
                            .build();
                })
                // 채팅 내역이 없을 경우 빈 InterviewHistoryResponseDto 반환
                .orElseGet(() -> InterviewHistoryResponseDto.builder()
                        .isEmpty(true)
                        .chatHistoryList(null)
                        .build());
    }
}
