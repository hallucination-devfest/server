package com.cheep.hallucination.service.room;

import com.cheep.hallucination.domain.Keyword;
import com.cheep.hallucination.domain.Room;
import com.cheep.hallucination.domain.User;
import com.cheep.hallucination.dto.request.ChooseKeywordRequestDto;
import com.cheep.hallucination.exception.CommonException;
import com.cheep.hallucination.exception.ErrorCode;
import com.cheep.hallucination.repository.KeywordRepository;
import com.cheep.hallucination.repository.RoomRepository;
import com.cheep.hallucination.repository.UserRepository;
import com.cheep.hallucination.usecase.room.ChooseKeywordUsecase;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ChooseKeywordService implements ChooseKeywordUsecase {
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final KeywordRepository keywordRepository;

    @Override
    public Boolean execute(ChooseKeywordRequestDto chooseKeywordRequestDto, Long roomId, UUID userId)  {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_ROOM));

        Keyword keyword = keywordRepository.findById(room.getId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_KEYWORD));

        if(keyword.getName().equals(chooseKeywordRequestDto.keyword())) {
            return true;
        }
        else {
            user.updateChancePlay();
            return false;
        }
    }
}
