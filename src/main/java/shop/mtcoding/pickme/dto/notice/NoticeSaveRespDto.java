package shop.mtcoding.pickme.dto.notice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeSaveRespDto {
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
}
