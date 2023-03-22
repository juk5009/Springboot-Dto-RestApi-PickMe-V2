package shop.mtcoding.pickme.model;

import java.sql.Timestamp;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.pickme.dto.notice.NoticeReq.NoticeSaveReqDto;
import shop.mtcoding.pickme.dto.notice.NoticeReq.NoticeUpdateReqDto;

@Setter
@Getter
public class Notice {
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
    private Timestamp createdAt;
    private List<Companyskill> skill;
    private String companyProfile;

    public Notice(NoticeSaveReqDto NoticeSaveReqDto) {
        this.companyId = NoticeSaveReqDto.getCompanyId();
        this.noticeCompanyname = NoticeSaveReqDto.getNoticeCompanyname();
        this.noticeTitle = NoticeSaveReqDto.getNoticeTitle();
        this.noticeCareer = NoticeSaveReqDto.getNoticeCareer();
        this.noticePay = NoticeSaveReqDto.getNoticePay();
        this.noticeEmploytype = NoticeSaveReqDto.getNoticeEmploytype();
        this.noticeGrade = NoticeSaveReqDto.getNoticeGrade();
        this.noticeLocation = NoticeSaveReqDto.getNoticeLocation();
        this.noticeContent = NoticeSaveReqDto.getNoticeContent();
    }

    public Notice(NoticeUpdateReqDto noticeupdateReqDto) {
        this.id = noticeupdateReqDto.getId();
        this.companyId = noticeupdateReqDto.getCompanyId();
        this.noticeCompanyname = noticeupdateReqDto.getNoticeCompanyname();
        this.noticeTitle = noticeupdateReqDto.getNoticeTitle();
        this.noticeCareer = noticeupdateReqDto.getNoticeCareer();
        this.noticePay = noticeupdateReqDto.getNoticePay();
        this.noticeEmploytype = noticeupdateReqDto.getNoticeEmploytype();
        this.noticeGrade = noticeupdateReqDto.getNoticeGrade();
        this.noticeLocation = noticeupdateReqDto.getNoticeLocation();
        this.noticeContent = noticeupdateReqDto.getNoticeContent();
    }

    public Notice() {

    }

}
