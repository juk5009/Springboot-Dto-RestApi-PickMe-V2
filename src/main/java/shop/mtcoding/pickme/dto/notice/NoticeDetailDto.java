package shop.mtcoding.pickme.dto.notice;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.pickme.dto.notice.NoticeReq.NoticeSaveReqDto;
import shop.mtcoding.pickme.dto.resume.ResumeResp.ResumeSelectRespDto;
import shop.mtcoding.pickme.model.Companyskill;

@Setter
@Getter
public class NoticeDetailDto {
    private NoticeSaveReqDto noticeDto;
    private List<Companyskill> Companyskills;
    private List<ResumeSelectRespDto> resumeSelectList;
}
