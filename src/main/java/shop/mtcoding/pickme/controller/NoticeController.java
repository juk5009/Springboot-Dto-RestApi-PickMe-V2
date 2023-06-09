package shop.mtcoding.pickme.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.pickme.config.annotation.Validation;
import shop.mtcoding.pickme.config.auth.LoginCompany;
import shop.mtcoding.pickme.dto.ResponseDto;
import shop.mtcoding.pickme.dto.notice.NoticeDetailDto;
import shop.mtcoding.pickme.dto.notice.NoticeDto;
import shop.mtcoding.pickme.dto.notice.NoticeReq.NoticeSaveReqDto;
import shop.mtcoding.pickme.dto.notice.NoticeReq.NoticeUpdateReqDto;
import shop.mtcoding.pickme.dto.notice.NoticeSaveRespDto;
import shop.mtcoding.pickme.dto.notice.NoticeUpdateDto;
import shop.mtcoding.pickme.dto.notice.NoticeUpdateRespDto;
import shop.mtcoding.pickme.dto.resume.ResumeResp.ResumeSelectRespDto;
import shop.mtcoding.pickme.handler.ex.CustomApiException;
import shop.mtcoding.pickme.handler.ex.CustomException;
import shop.mtcoding.pickme.model.Company;
import shop.mtcoding.pickme.model.NoticeRepository;
import shop.mtcoding.pickme.service.NoticeService;

@RequiredArgsConstructor
@RestController
public class NoticeController {

    private final HttpSession session;

    private final NoticeService noticeService;

    private final NoticeRepository noticeRepository;

    @PostMapping("/saveNotice")
    public @ResponseBody ResponseEntity<?> saveNotice(@RequestBody @Validation NoticeSaveReqDto noticeSaveReqDto) {
        String comSkill = noticeSaveReqDto.getCompanyskillList();
        LoginCompany loginCompany = (LoginCompany) session.getAttribute("comPrincipal");
        NoticeSaveRespDto noticesave = noticeService.공고작성(noticeSaveReqDto, loginCompany.getId(), comSkill);
        return new ResponseEntity<>(new ResponseDto<>(1, "공고 작성 완료", noticesave), HttpStatus.CREATED);
    }

    @PutMapping("/notice/{id}")
    public ResponseEntity<?> updateNotice(@PathVariable int id,
            @RequestBody @Validation NoticeUpdateReqDto noticeUpdateReqDto) {
        String comSkill = noticeUpdateReqDto.getCompanyskillList();
        LoginCompany loginCompany = (LoginCompany) session.getAttribute("comPrincipal");
        NoticeUpdateRespDto noticeupdate = noticeService.공고수정(id, noticeUpdateReqDto, loginCompany.getId(), comSkill);
        return new ResponseEntity<>(new ResponseDto<>(1, "공고 수정 완료", noticeupdate), HttpStatus.CREATED);
    }

    @DeleteMapping("/notice/{id}")
    public @ResponseBody ResponseEntity<?> deleteNotice(@PathVariable int id) {
        LoginCompany loginCompany = (LoginCompany) session.getAttribute("comPrincipal");
        noticeService.공고삭제(id, loginCompany.getId());
        return new ResponseEntity<>(new ResponseDto<>(1, "공고 삭제 완료", null), HttpStatus.OK);

    }

    @GetMapping("/nc/notice/{id}")
    public ResponseEntity<?> noticeDetailForm(@PathVariable int id) {
        List<ResumeSelectRespDto> resumeSelectList = noticeRepository.findAllWithResume();
        NoticeDto noticeDto = noticeRepository.noticeJoinCompanySkill(id);
        NoticeDetailDto noticeDetailDto = new NoticeDetailDto();
        noticeDetailDto.setNoticeDto(noticeDto);
        noticeDetailDto.setResumeSelect(resumeSelectList);
        return new ResponseEntity<>(new ResponseDto<>(1, "성공", noticeDetailDto), HttpStatus.OK);
    }

    @GetMapping("/notice/{id}/updateNoticeForm")
    public ResponseEntity<?> noticeUpdateForm(@PathVariable int id) {
        NoticeUpdateDto noticeUpdateDto = noticeRepository.noticeJoinCompanySkillJoinCompany(id);
        return new ResponseEntity<>(new ResponseDto<>(1, "성공", noticeUpdateDto), HttpStatus.OK);
    }

}
