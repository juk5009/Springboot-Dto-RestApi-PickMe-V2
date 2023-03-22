package shop.mtcoding.pickme.dto.apply;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.pickme.dto.resume.ResumeReq.ResumeSaveReqDto;
import shop.mtcoding.pickme.model.Userskill;

@Setter
@Getter
public class ApplyDetailDto {
    private List<Userskill> userskill;
    private ResumeSaveReqDto resumeSaveReqDto;
}