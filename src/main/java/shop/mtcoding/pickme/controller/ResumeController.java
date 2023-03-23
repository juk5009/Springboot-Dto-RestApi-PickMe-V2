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
import shop.mtcoding.pickme.dto.ResponseDto;
import shop.mtcoding.pickme.dto.resume.ResumeDetailDto;
import shop.mtcoding.pickme.dto.resume.ResumeUpdateDto;
import shop.mtcoding.pickme.dto.resume.ResumeReq.ResumeSaveReqDto;
import shop.mtcoding.pickme.dto.resume.ResumeReq.ResumeUpdateReqDto;
import shop.mtcoding.pickme.dto.resume.ResumeRespDto.ResumeUpdateRespDto;
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
    public @ResponseBody ResponseEntity<?> saveResume(@RequestBody ResumeSaveReqDto resumeSaveReqDto) {
        String usSkill = resumeSaveReqDto.getUserskillList();

        User userPrincipal = (User) session.getAttribute("userPrincipal");
        if (userPrincipal == null) {
            throw new CustomApiException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }

        if (resumeSaveReqDto.getResumeUsername() == null ||
                resumeSaveReqDto.getResumeUsername().isEmpty()) {
            throw new CustomApiException("이름을 작성해주세요");
        }
        if (resumeSaveReqDto.getResumeBirth() == null ||
                resumeSaveReqDto.getResumeBirth().isEmpty()) {
            throw new CustomApiException("생년월일을 작성해주세요");
        }
        if (resumeSaveReqDto.getResumeEmail() == null ||
                resumeSaveReqDto.getResumeEmail().isEmpty()) {
            throw new CustomApiException("email 작성해주세요");
        }
        if (resumeSaveReqDto.getResumeAddress() == null ||
                resumeSaveReqDto.getResumeAddress().isEmpty()) {
            throw new CustomApiException("주소를 작성해주세요");
        }
        if (resumeSaveReqDto.getResumeCareer() == null ||
                resumeSaveReqDto.getResumeCareer().isEmpty()) {
            throw new CustomApiException("경력를 작성해주세요");
        }
        if (resumeSaveReqDto.getResumeLocation() == null ||
                resumeSaveReqDto.getResumeLocation().isEmpty()) {
            throw new CustomApiException("지역을 선택해주세요");
        }
        if (resumeSaveReqDto.getResumeGrade() == null ||
                resumeSaveReqDto.getResumeGrade().isEmpty()) {
            throw new CustomApiException("학력을 작성해주세요");
        }
        if (resumeSaveReqDto.getResumePhoneNumber() == null ||
                resumeSaveReqDto.getResumePhoneNumber().isEmpty()) {
            throw new CustomApiException("전화번호를 작성해주세요");
        }
        if (resumeSaveReqDto.getResumeSex() == null ||
                resumeSaveReqDto.getResumeSex().isEmpty()) {
            throw new CustomApiException("성별을 작성해주세요");
        }
        if (resumeSaveReqDto.getResumeContent() == null ||
                resumeSaveReqDto.getResumeContent().isEmpty()) {
            throw new CustomApiException("자기소개서를 작성해주세요");
        }

        resumeService.이력서작성(resumeSaveReqDto, userPrincipal.getId(), usSkill);
        return new ResponseEntity<>(new ResponseDto<>(1, "이력서 작성 성공", null), HttpStatus.CREATED);
    }

    @PutMapping("/resume/{id}")
    public @ResponseBody ResponseEntity<?> updateNotice(@PathVariable int id,
            @RequestBody ResumeUpdateReqDto resumeUpdateReqDto) {
        String usSkill = resumeUpdateReqDto.getUserskillList();

        User userPrincipal = (User) session.getAttribute("userPrincipal");
        if (userPrincipal == null) {
            throw new CustomApiException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }
        if (resumeUpdateReqDto.getResumeUsername() == null ||
                resumeUpdateReqDto.getResumeUsername().isEmpty()) {
            throw new CustomApiException("이름을 작성해주세요");
        }
        if (resumeUpdateReqDto.getResumeBirth() == null ||
                resumeUpdateReqDto.getResumeBirth().isEmpty()) {
            throw new CustomApiException("생년월일을 작성해주세요");
        }
        if (resumeUpdateReqDto.getResumeEmail() == null ||
                resumeUpdateReqDto.getResumeEmail().isEmpty()) {
            throw new CustomApiException("email 작성해주세요");
        }
        if (resumeUpdateReqDto.getResumeAddress() == null ||
                resumeUpdateReqDto.getResumeAddress().isEmpty()) {
            throw new CustomApiException("주소를 작성해주세요");
        }
        if (resumeUpdateReqDto.getResumeCareer() == null ||
                resumeUpdateReqDto.getResumeCareer().isEmpty()) {
            throw new CustomApiException("경력사항을 작성해주세요");
        }
        if (resumeUpdateReqDto.getResumeLocation() == null ||
                resumeUpdateReqDto.getResumeLocation().isEmpty()) {
            throw new CustomApiException("지역을 선택해주세요");
        }
        if (resumeUpdateReqDto.getResumeGrade() == null ||
                resumeUpdateReqDto.getResumeGrade().isEmpty()) {
            throw new CustomApiException("학력을 작성해주세요");
        }
        if (resumeUpdateReqDto.getResumePhoneNumber() == null ||
                resumeUpdateReqDto.getResumePhoneNumber().isEmpty()) {
            throw new CustomApiException("전화번호를 작성해주세요");
        }
        if (resumeUpdateReqDto.getResumeSex() == null ||
                resumeUpdateReqDto.getResumeSex().isEmpty()) {
            throw new CustomApiException("성별을 작성해주세요");
        }
        if (resumeUpdateReqDto.getResumeContent() == null ||
                resumeUpdateReqDto.getResumeContent().isEmpty()) {
            throw new CustomApiException("자기소개서를 작성해주세요");
        }

        resumeService.이력서수정(id, resumeUpdateReqDto, userPrincipal.getId(), usSkill);

        return new ResponseEntity<>(new ResponseDto<>(1, "이력서 수정 완료", null), HttpStatus.CREATED);
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
