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
    public @ResponseBody ResponseEntity<?> saveNotice(@RequestBody NoticeSaveReqDto noticeSaveReqDto) {
        String comSkill = noticeSaveReqDto.getCompanyskillList();
        // Company comPrincipal = (Company) session.getAttribute("comPrincipal");
        // if (comPrincipal == null) {
        // throw new CustomApiException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        // }
        if (noticeSaveReqDto.getNoticeTitle() == null || noticeSaveReqDto.getNoticeTitle().isEmpty()) {
            throw new CustomApiException("제목을 작성해주세요");
        }
        if (noticeSaveReqDto.getNoticeCompanyname() == null || noticeSaveReqDto.getNoticeCompanyname().isEmpty()) {
            throw new CustomApiException("회사 이름을 작성해주세요");
        }
        if (noticeSaveReqDto.getNoticeCareer() == null || noticeSaveReqDto.getNoticeCareer().isEmpty()) {
            throw new CustomApiException("경력을 작성해주세요");
        }
        if (noticeSaveReqDto.getNoticePay() == null || noticeSaveReqDto.getNoticePay().isEmpty()) {
            throw new CustomApiException("급여를 작성해주세요");
        }
        if (noticeSaveReqDto.getNoticeEmploytype() == null || noticeSaveReqDto.getNoticeEmploytype().isEmpty()) {
            throw new CustomApiException("근무형태를 작성해주세요");
        }
        if (noticeSaveReqDto.getNoticeGrade() == null || noticeSaveReqDto.getNoticeGrade().isEmpty()) {
            throw new CustomApiException("학력을 작성해주세요");
        }
        if (noticeSaveReqDto.getNoticeLocation() == null || noticeSaveReqDto.getNoticeLocation().isEmpty()) {
            throw new CustomApiException("근무지역을 작성해주세요");
        }
        if (noticeSaveReqDto.getNoticeContent() == null || noticeSaveReqDto.getNoticeContent().isEmpty()) {
            throw new CustomApiException("내용을 작성해주세요");
        }
        NoticeSaveRespDto noticesave = noticeService.공고작성(noticeSaveReqDto, 1, comSkill);
        return new ResponseEntity<>(new ResponseDto<>(1, "공고 작성 완료", noticesave), HttpStatus.CREATED);
    }

    @PutMapping("/notice/{id}")
    public ResponseEntity<?> updateNotice(@PathVariable int id,
            @RequestBody NoticeUpdateReqDto noticeUpdateReqDto) {
        String comSkill = noticeUpdateReqDto.getCompanyskillList();

        // Company comPrincipal = (Company) session.getAttribute("comPrincipal");
        // if (comPrincipal == null) {
        // throw new CustomApiException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        // }
        if (noticeUpdateReqDto.getNoticeTitle() == null || noticeUpdateReqDto.getNoticeTitle().isEmpty()) {
            throw new CustomApiException("제목을 작성해주세요");
        }
        if (noticeUpdateReqDto.getNoticeCompanyname() == null || noticeUpdateReqDto.getNoticeCompanyname().isEmpty()) {
            throw new CustomApiException("회사 이름을 작성해주세요");
        }
        if (noticeUpdateReqDto.getNoticeCareer() == null || noticeUpdateReqDto.getNoticeCareer().isEmpty()) {
            throw new CustomApiException("경력을 작성해주세요");
        }
        if (noticeUpdateReqDto.getNoticePay() == null || noticeUpdateReqDto.getNoticePay().isEmpty()) {
            throw new CustomApiException("급여를 작성해주세요");
        }
        if (noticeUpdateReqDto.getNoticeEmploytype() == null || noticeUpdateReqDto.getNoticeEmploytype().isEmpty()) {
            throw new CustomApiException("근무형태를 작성해주세요");
        }
        if (noticeUpdateReqDto.getNoticeGrade() == null || noticeUpdateReqDto.getNoticeGrade().isEmpty()) {
            throw new CustomApiException("학력을 작성해주세요");
        }
        if (noticeUpdateReqDto.getNoticeLocation() == null || noticeUpdateReqDto.getNoticeLocation().isEmpty()) {
            throw new CustomApiException("근무지역을 작성해주세요");
        }
        if (noticeUpdateReqDto.getNoticeContent() == null || noticeUpdateReqDto.getNoticeContent().isEmpty()) {
            throw new CustomApiException("내용을 작성해주세요");
        }

        NoticeUpdateRespDto noticeupdate = noticeService.공고수정(id, noticeUpdateReqDto, 1, comSkill);

        return new ResponseEntity<>(new ResponseDto<>(1, "공고 수정 완료", noticeupdate), HttpStatus.CREATED);
    }

    @DeleteMapping("/notice/{id}")
    public @ResponseBody ResponseEntity<?> deleteNotice(@PathVariable int id) {

        Company comPrincipal = (Company) session.getAttribute("comPrincipal");
        if (comPrincipal == null) {
            throw new CustomApiException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }
        noticeService.공고삭제(id, comPrincipal.getId());
        return new ResponseEntity<>(new ResponseDto<>(1, "공고 삭제 완료", null), HttpStatus.OK);

    }

    @GetMapping("/notice/saveNoticeForm")
    public String saveNoticeForm() {
        Company comPrincipal = (Company) session.getAttribute("comPrincipal");
        if (comPrincipal == null) {
            throw new CustomException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }
        return "notice/saveNoticeForm";
    }

    @GetMapping("/notice/{id}")
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
        // Company comPrincipal = (Company) session.getAttribute("comPrincipal");
        // if (comPrincipal == null) {
        // throw new CustomException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        // }
        NoticeUpdateDto noticeUpdateDto = noticeRepository.noticeJoinCompanySkillJoinCompany(id);

        return new ResponseEntity<>(new ResponseDto<>(1, "성공", noticeUpdateDto), HttpStatus.OK);
    }

}
