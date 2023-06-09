package shop.mtcoding.pickme.dto.notice;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NoticeDto {
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

    @Setter
    @Getter
    public static class CompanyskillSaveRespDto {
        private Integer id;
        private String companyskillName;

    }

}
