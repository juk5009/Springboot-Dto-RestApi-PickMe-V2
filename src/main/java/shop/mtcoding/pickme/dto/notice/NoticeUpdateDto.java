package shop.mtcoding.pickme.dto.notice;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NoticeUpdateDto {
    private Integer id;
    private Integer companyId;
    private String noticeCompanyname;
    private String noticeTitle;
    private String noticeCareer;
    private String noticePay;
    private String noticeEmploytype;
    private String noticeGrade;
    private String noticeLocation;
    private String noticeContent;
    private List<CompanyskillSaveRespDto> companyskillList;
    private CompanyDto company;

    @Setter
    @Getter
    public static class CompanyskillSaveRespDto {
        private Integer id;
        private String companyskillName;

    }

    @Setter
    @Getter
    public static class CompanyDto {
        private Integer id;
        private String companyProfile;

    }

}
