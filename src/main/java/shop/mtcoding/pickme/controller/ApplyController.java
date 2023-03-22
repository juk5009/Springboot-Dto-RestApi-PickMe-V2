package shop.mtcoding.pickme.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.pickme.dto.ResponseDto;
import shop.mtcoding.pickme.dto.apply.ApplyReq.ApplyResumeSelectReqDto;
import shop.mtcoding.pickme.dto.apply.ApplyResp.ApplyListRespDto;
import shop.mtcoding.pickme.handler.ex.CustomException;
import shop.mtcoding.pickme.model.ApplyRepository;
import shop.mtcoding.pickme.model.Company;
import shop.mtcoding.pickme.model.ResumeRepository;
import shop.mtcoding.pickme.model.Userskill;
import shop.mtcoding.pickme.model.UserskillRepository;

@RequiredArgsConstructor
@RestController
public class ApplyController {

    private final ApplyRepository applyRepository;

    private final ResumeRepository resumeRepository;

    private final HttpSession session;

    private final UserskillRepository userskillRespository;

    @GetMapping("/apply/applyUserList")
    public String applyUserList(Model model) {
        Company comprincipal = (Company) session.getAttribute("comPrincipal");
        if (comprincipal == null) {
            throw new CustomException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }
        List<ApplyListRespDto> applyUserList = applyRepository.findApplyList();
        model.addAttribute("applyUserList", applyUserList);
        return "apply/applyUserList";
    }

    @GetMapping("/apply/{id}")
    public String applyDetailForm(@PathVariable int id, Model model) {
        Company comprincipal = (Company) session.getAttribute("comPrincipal");
        if (comprincipal == null) {
            throw new CustomException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }
        model.addAttribute("resumeDto", resumeRepository.findByUserIdWithResume(id));
        List<Userskill> userskill = userskillRespository.findByResumeId(id);
        model.addAttribute("userskillDto", userskill);
        return "apply/applyDetailForm";
    }

    @PostMapping("/apply/applyResumeSelect")
    public ResponseEntity<?> applyResumeSelect(@RequestBody ApplyResumeSelectReqDto applyResumeSelectReqDto) {
        int result = applyRepository.insert(applyResumeSelectReqDto);
        return new ResponseEntity<>(new ResponseDto<>(1, "지원성공", result), HttpStatus.CREATED);
    }

}
