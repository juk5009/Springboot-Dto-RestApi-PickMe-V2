package shop.mtcoding.pickme.dto.apply;

import lombok.Getter;
import lombok.Setter;

public class ApplyResp {

    @Setter
    @Getter
    public static class ApplyListRespDto {
        private Integer id;
        private Integer userId;
        private Integer companyId;
        private Integer resumeId;
        private Integer noticeId;
        private String resumeUsername;
        private String resumeCareer;
        private String resumeGrade;

    }

}
