package shop.mtcoding.pickme.dto.user;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.pickme.dto.resume.ResumeResp.ResumeSelectRespDto;
import shop.mtcoding.pickme.model.User;

@Getter
@Setter
public class UserMyPageDto {
    private List<ResumeSelectRespDto> resumeSelectList;
    private User userPS;
    private User userProfilePS;
}
