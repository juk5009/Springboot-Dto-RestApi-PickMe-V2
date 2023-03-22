package shop.mtcoding.pickme.dto.notice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeMainRespDto {
    private int id;
    private String noticeCompanyname;
    private String noticeTitle;
    private int companyId;
    private CompanyMainDto companyProfile;

    @Getter
    @Setter
    public static class CompanyMainDto {
        private Integer id;
        private String companyProfile;
    }
}
