package shop.mtcoding.pickme.controller;

import java.util.List;

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
import shop.mtcoding.pickme.model.ApplyRepository;

@RequiredArgsConstructor
@RestController
public class ApplyController {

    private final ApplyRepository applyRepository;

    @GetMapping("/apply/applyUserList")
    public ResponseEntity<?> applyUserList() {
        List<ApplyListRespDto> applyUserList = applyRepository.findApplyList();
        return new ResponseEntity<>(new ResponseDto<>(1, "성공", applyUserList), HttpStatus.OK);
    }

    @GetMapping("/apply/{id}")
    public ResponseEntity<?> applyDetailForm(@PathVariable int id) {
        ApplyDetailDto dto = applyRepository.resumeJoinUserskill(id);
        return new ResponseEntity<>(new ResponseDto<>(1, "성공", dto), HttpStatus.OK);
    }

    @PostMapping("/apply/applyResumeSelect")
    public ResponseEntity<?> applyResumeSelect(@RequestBody ApplyResumeSelectReqDto applyResumeSelectReqDto) {
        int result = applyRepository.insert(applyResumeSelectReqDto);
        return new ResponseEntity<>(new ResponseDto<>(1, "지원성공", result), HttpStatus.CREATED);
    }

}
