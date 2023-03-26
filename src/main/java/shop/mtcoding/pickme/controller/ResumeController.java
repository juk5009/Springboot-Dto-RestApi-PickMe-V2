package shop.mtcoding.pickme.controller;

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
import shop.mtcoding.pickme.dto.ResponseDto;
import shop.mtcoding.pickme.dto.resume.ResumeDetailDto;
import shop.mtcoding.pickme.dto.resume.ResumeReq.ResumeSaveReqDto;
import shop.mtcoding.pickme.dto.resume.ResumeReq.ResumeUpdateReqDto;
import shop.mtcoding.pickme.dto.resume.ResumeRespDto.ResumeUpdateRespDto;
import shop.mtcoding.pickme.dto.resume.ResumeSaveRespDto;
import shop.mtcoding.pickme.dto.resume.ResumeUpdateDto;
import shop.mtcoding.pickme.handler.ex.CustomApiException;
import shop.mtcoding.pickme.handler.ex.CustomException;
import shop.mtcoding.pickme.model.ResumeRepository;
import shop.mtcoding.pickme.model.User;
import shop.mtcoding.pickme.service.ResumeService;

@RequiredArgsConstructor
@RestController
public class ResumeController {

    private final ResumeService resumeService;

    private final ResumeRepository resumeRepository;

    private final HttpSession session;

    @PostMapping("/saveResume")
    public @ResponseBody ResponseEntity<?> saveResume(@RequestBody @Validation ResumeSaveReqDto resumeSaveReqDto) {
        String usSkill = resumeSaveReqDto.getUserskillList();

        // User userPrincipal = (User) session.getAttribute("userPrincipal");
        // if (userPrincipal == null) {
        // throw new CustomApiException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        // }

        ResumeSaveRespDto resumesave = resumeService.이력서작성(resumeSaveReqDto, 1, usSkill);
        return new ResponseEntity<>(new ResponseDto<>(1, "이력서 작성 성공", resumesave), HttpStatus.CREATED);
    }

    @PutMapping("/resume/{id}")
    public @ResponseBody ResponseEntity<?> updateNotice(@PathVariable int id,
            @RequestBody @Validation ResumeUpdateReqDto resumeUpdateReqDto) {
        String usSkill = resumeUpdateReqDto.getUserskillList();

        // User userPrincipal = (User) session.getAttribute("userPrincipal");
        // if (userPrincipal == null) {
        // throw new CustomApiException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        // }

        ResumeUpdateRespDto resumeupdate = resumeService.이력서수정(id, resumeUpdateReqDto, 1, usSkill);

        return new ResponseEntity<>(new ResponseDto<>(1, "이력서 수정 완료", resumeupdate), HttpStatus.CREATED);
    }

    @DeleteMapping("/resume/{id}")
    public @ResponseBody ResponseEntity<?> deleteResume(@PathVariable int id) {

        User userPrincipal = (User) session.getAttribute("userPrincipal");
        if (userPrincipal == null) {
            throw new CustomApiException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }
        resumeService.이력서삭제(id, userPrincipal.getId());
        return new ResponseEntity<>(new ResponseDto<>(1, "이력서 삭제 완료", null), HttpStatus.OK);

    }

    @GetMapping("/resume/saveResumeForm")
    public String saveResumeForm() {
        User userPrincipal = (User) session.getAttribute("userPrincipal");
        if (userPrincipal == null) {
            throw new CustomException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }
        return "resume/saveResumeForm";
    }

    @GetMapping("/resume/{id}")
    public ResponseEntity<?> resumeDetailForm(@PathVariable int id) {
        // User userPrincipal = (User) session.getAttribute("userPrincipal");
        // Company comprincipal = (Company) session.getAttribute("comPrincipal");
        // if (userPrincipal == null && comprincipal == null) {
        // throw new CustomException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        // }

        // resumeRepository.findById(id);

        // ResumeRespDto resumeDto = resumeRepository.findByUserIdWithResume(id);

        // List<UserskillDto> userskillDto = userskillRepository.findByResumeId(id);

        // ResumeDetailRespDtoV2 resumeDetailRespDtoV2 =
        // ResumeDetailRespDtoV2.ofResumeDetailRespDtoV2(resumeDto);
        // resumeDetailRespDtoV2.setUserSkills(userskillDto);

        ResumeDetailDto resumeDetailDto = resumeRepository.resumeJoinUserskills(id);

        return new ResponseEntity<>(new ResponseDto<>(1, "성공", resumeDetailDto), HttpStatus.OK);

    }

    @GetMapping("/resume/{id}/updateResumeForm")
    public ResponseEntity<?> resumeUpdateForm(@PathVariable int id) {

        ResumeUpdateDto resumeUpdateDto = resumeRepository.resumeJoinUserskillJoinUser(id);
        return new ResponseEntity<>(new ResponseDto<>(1, "성공", resumeUpdateDto), HttpStatus.OK);
    }

}
