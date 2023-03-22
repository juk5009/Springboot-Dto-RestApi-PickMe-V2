package shop.mtcoding.pickme.dto.apply;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.pickme.dto.resume.ResumeRespDto;
import shop.mtcoding.pickme.dto.resume.ResumeRespDto.ResumeDetailRespDtoV2.UserskillDto;

@Setter
@Getter
public class ApplyDetailDto {
    private List<UserskillDto> userskill;
    private ResumeRespDto resumeRespDto;
}