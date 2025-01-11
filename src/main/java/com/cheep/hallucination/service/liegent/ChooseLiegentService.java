package com.cheep.hallucination.service.liegent;

import com.cheep.hallucination.domain.Room;
import com.cheep.hallucination.domain.User;
import com.cheep.hallucination.dto.response.ChooseLiegentResponseDto;
import com.cheep.hallucination.exception.CommonException;
import com.cheep.hallucination.exception.ErrorCode;
import com.cheep.hallucination.repository.RoomRepository;
import com.cheep.hallucination.repository.UserRepository;
import com.cheep.hallucination.usecase.liegent.ChooseLiegentUsecase;
import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ChooseLiegentService implements ChooseLiegentUsecase {
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    @Override
    public ChooseLiegentResponseDto execute(Long roomId, Long chooseCharacterId, UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_ROOM));

        Long liarCharacterId = room.getLiarCharacter().getId();

        // 고른 캐릭터가 라이전트가 맞다면
        if (liarCharacterId.equals(chooseCharacterId)) {
            // 라이전트 검거 여부를 True로 변경
            boolean isSuccess = room.updateSuccess();
            // 라이전트 검거 여부와 플레이어의 다음 라운드를 반환
            return ChooseLiegentResponseDto.builder()
                    .isSuccess(isSuccess)
                    .build();
        }
        else{
            user.updateChancePlay();
            // 라이전트 검거 여부가 False이고, 플레이어의 다음 라운드는 처음부터 다시 시작이므로 1을 반환
            return ChooseLiegentResponseDto.builder()
                    .isSuccess(false)
                    .build();
        }
    }
}
