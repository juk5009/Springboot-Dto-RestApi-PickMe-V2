package shop.mtcoding.pickme.dto.notice;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeSelectRespDto {
    private CompanySelectDto companyProfile;
    private String noticeTitle;
    private Integer id;
    private Integer companyId;

    @Getter
    @Setter
    public static class CompanySelectDto {
        private Integer id;
        private String companyName;
        private String companyPassword;
        private String companyEmail;
        private String companyProfile;
        private Timestamp createdAt;
    }
}