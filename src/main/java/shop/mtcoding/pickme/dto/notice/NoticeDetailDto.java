package shop.mtcoding.pickme.dto.notice;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.pickme.dto.resume.ResumeResp.ResumeSelectRespDto;

@Setter
@Getter
public class NoticeDetailDto {
    NoticeDto noticeDetailDto;
    List<ResumeSelectRespDto> resumeSelect;
}
