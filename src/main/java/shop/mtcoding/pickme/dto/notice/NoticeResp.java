package shop.mtcoding.pickme.dto.notice;

import lombok.Getter;
import lombok.Setter;

public class NoticeResp {

    @Setter
    @Getter
    public static class NoticeMainRespDto {
        private int id;
        private String noticeCompanyname;
        private String noticeTitle;
        private String companyProfile;
    }

    @Setter
    @Getter
    public static class NoticeSelectRespDto {
        private String companyProfile;
        private String noticeTitle;
        private Integer id;
        private Integer companyId;
    }
}
