package shop.mtcoding.pickme.dto.apply;

import lombok.Getter;
import lombok.Setter;

public class ApplyReq {

    @Setter
    @Getter
    public static class ApplyResumeSelectReqDto {
        private Integer resumeId;
        private Integer noticeId;
        private Integer userId;
        private Integer companyId;
    }
}
