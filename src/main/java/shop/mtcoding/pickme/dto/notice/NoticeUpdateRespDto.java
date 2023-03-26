package shop.mtcoding.pickme.dto.notice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeUpdateRespDto {
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
    private String companyskillList;

    public NoticeUpdateRespDto(Integer id, Integer companyId, String noticeCompanyname, String noticeTitle,
            String noticeCareer, String noticePay, String noticeEmploytype, String noticeGrade, String noticeLocation,
            String noticeContent, String companyskillList) {
        this.id = id;
        this.companyId = companyId;
        this.noticeCompanyname = noticeCompanyname;
        this.noticeTitle = noticeTitle;
        this.noticeCareer = noticeCareer;
        this.noticePay = noticePay;
        this.noticeEmploytype = noticeEmploytype;
        this.noticeGrade = noticeGrade;
        this.noticeLocation = noticeLocation;
        this.noticeContent = noticeContent;
        this.companyskillList = companyskillList;
    }
}