package shop.mtcoding.pickme.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.pickme.dto.ResponseDto;
import shop.mtcoding.pickme.dto.apply.ApplyDetailDto;
import shop.mtcoding.pickme.dto.apply.ApplyReq.ApplyResumeSelectReqDto;
import shop.mtcoding.pickme.dto.apply.ApplyResp.ApplyListRespDto;
import shop.mtcoding.pickme.dto.resume.ResumeRespDto;
import shop.mtcoding.pickme.dto.resume.ResumeRespDto.ResumeDetailRespDtoV2.UserskillDto;
import shop.mtcoding.pickme.model.ApplyRepository;
import shop.mtcoding.pickme.model.ResumeRepository;
import shop.mtcoding.pickme.model.UserskillRepository;

@RequiredArgsConstructor
@RestController
public class ApplyController {

    private final ApplyRepository applyRepository;

    private final ResumeRepository resumeRepository;

    private final HttpSession session;

    private final UserskillRepository userskillRespository;

    @GetMapping("/apply/applyUserList")
    public ResponseEntity<?> applyUserList() {
        // Company comprincipal = (Company) session.getAttribute("comPrincipal");
        // if (comprincipal == null) {
        // throw new CustomException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        // }
        List<ApplyListRespDto> applyUserList = applyRepository.findApplyList();
        return new ResponseEntity<>(new ResponseDto<>(1, "성공", applyUserList), HttpStatus.OK);
    }

    @GetMapping("/apply/{id}")
    public ResponseEntity<?> applyDetailForm(@PathVariable int id) {
        // Company comprincipal = (Company) session.getAttribute("comPrincipal");
        // if (comprincipal == null) {
        // throw new CustomException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        // }
        ResumeRespDto resumeRespDto = resumeRepository.findByUserIdWithResume(id);
        List<UserskillDto> userskill = userskillRespository.findByResumeId(id);
        ApplyDetailDto dto = new ApplyDetailDto();
        dto.setResumeRespDto(resumeRespDto);
        dto.setUserskill(userskill);

        return new ResponseEntity<>(new ResponseDto<>(1, "성공", dto), HttpStatus.OK);
    }

    @PostMapping("/apply/applyResumeSelect")
    public ResponseEntity<?> applyResumeSelect(@RequestBody ApplyResumeSelectReqDto applyResumeSelectReqDto) {
        int result = applyRepository.insert(applyResumeSelectReqDto);
        return new ResponseEntity<>(new ResponseDto<>(1, "지원성공", result), HttpStatus.CREATED);
    }

}
