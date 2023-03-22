package shop.mtcoding.pickme.dto.resume;

import lombok.Getter;
import lombok.Setter;

public class ResumeResp {
    @Setter
    @Getter
    public static class ResumeSelectRespDto {
        private String userProfile;
        private String resumeUsername;
        private Integer id;
        private Integer userId;
    }
}
