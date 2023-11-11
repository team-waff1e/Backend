package io.github.teamwaff1e.waffle.domain.waffle.dto.request;

import io.github.teamwaff1e.waffle.global.dto.request.RequestDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateWaffleRequestDto implements RequestDto {

//    private Long waffleId;
    @NotBlank(message = "내용 입력이 없습니다.")
    private String content;
}
