package shop.mtcoding.pickme.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import shop.mtcoding.pickme.dto.notice.NoticeReq.NoticeSaveReqDto;
import shop.mtcoding.pickme.dto.notice.NoticeReq.NoticeUpdateReqDto;

@Getter
@Setter
public class Companyskill {
    private Integer id;
    private Integer noticeId;
    private Integer companyId;
    private String companyskillName;
    private String color = "N";
    private Timestamp createdAt;

    public Companyskill(NoticeSaveReqDto NoticeSaveReqDto) {
        this.noticeId = NoticeSaveReqDto.getId();
        this.companyId = NoticeSaveReqDto.getCompanyId();
        this.companyskillName = NoticeSaveReqDto.getCompanyskillList();
    }

    public Companyskill(NoticeUpdateReqDto noticeUpdateReqDto) {
        this.noticeId = noticeUpdateReqDto.getId();
        this.companyId = noticeUpdateReqDto.getCompanyId();
        this.companyskillName = noticeUpdateReqDto.getCompanyskillList();
    }

}
