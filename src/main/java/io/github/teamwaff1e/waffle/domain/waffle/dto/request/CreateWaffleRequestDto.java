package io.github.teamwaff1e.waffle.domain.waffle.dto.request;

import io.github.teamwaff1e.waffle.global.dto.request.RequestDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateWaffleRequestDto implements RequestDto {

    private Long memberId;
    @NotBlank(message = "내용 입력이 없습니다.")
    private String content;

}
