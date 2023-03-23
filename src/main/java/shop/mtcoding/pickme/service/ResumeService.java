package shop.mtcoding.pickme.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.pickme.dto.resume.ResumeReq.ResumeSaveReqDto;
import shop.mtcoding.pickme.dto.resume.ResumeReq.ResumeUpdateReqDto;
import shop.mtcoding.pickme.dto.resume.ResumeRespDto.ResumeUpdateRespDto;
import shop.mtcoding.pickme.handler.ex.CustomApiException;
import shop.mtcoding.pickme.model.Resume;
import shop.mtcoding.pickme.model.ResumeRepository;
import shop.mtcoding.pickme.model.Userskill;
import shop.mtcoding.pickme.model.UserskillRepository;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;

    private final UserskillRepository userskillRepository;

    @Transactional
    public void 이력서작성(ResumeSaveReqDto resumeSaveReqDto, int principalId, String usSkill) {
        resumeSaveReqDto.setUserId(principalId);
        Resume resume = new Resume(resumeSaveReqDto);

        int result = resumeRepository.insert(resume);
        if (result != 1) {
            throw new CustomApiException("이력서 작성 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        resumeSaveReqDto.setId(resume.getId());
        resumeSaveReqDto.setUserId(resume.getUserId());

        List<String> userskillList = Arrays.asList(usSkill.split(","));
        Userskill us = new Userskill(resumeSaveReqDto);

        if (userskillList != null) {
            for (String userskill : userskillList) {
                int result2 = userskillRepository.insert(us.getResumeId(), us.getUserId(), userskill);
                if (result2 != 1) {
                    throw new CustomApiException("보유기술작성 실패", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        }
    }

    @Transactional
    public void 이력서삭제(int id, int userPrincipalId) {
        ResumeUpdateRespDto resumePS = resumeRepository.findById(id);
        if (resumePS == null) {
            throw new CustomApiException("없는 공고입니다.");
        }
        if (resumePS.getUserId() != userPrincipalId) {
            throw new CustomApiException("해당 공고를 삭제할 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }

        try {
            resumeRepository.deleteById(id);
        } catch (Exception e) {
            throw new CustomApiException("서버에 일시적인 문제가 생겼습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public void 이력서수정(int id, ResumeUpdateReqDto resumeUpdateReqDto, int userPrincipalId, String usSkill) {

        ResumeUpdateRespDto resumePS = resumeRepository.findById(id);

        if (resumePS == null) {
            throw new CustomApiException("없는 이력서입니다.");
        }
        if (resumePS.getUserId() != userPrincipalId) {
            throw new CustomApiException("이력서를 삭제할 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }
        resumeUpdateReqDto.setId(resumePS.getId());
        resumeUpdateReqDto.setUserId(resumePS.getId());

        int result = resumeRepository.updateById(id,
                resumeUpdateReqDto.getResumeUsername(),
                resumeUpdateReqDto.getResumeBirth(),
                resumeUpdateReqDto.getResumeEmail(),
                resumeUpdateReqDto.getResumeAddress(),
                resumeUpdateReqDto.getResumeLocation(),
                resumeUpdateReqDto.getResumeCareer(),
                resumeUpdateReqDto.getResumeGrade(), resumeUpdateReqDto.getResumePhoneNumber(),
                resumeUpdateReqDto.getResumeSex(),
                resumeUpdateReqDto.getResumeContent());
        if (result != 1) {
            throw new CustomApiException("공고수정실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        userskillRepository.deleteByResumeId(id);

        List<String> userskillList = Arrays.asList(usSkill.split(","));
        Userskill us = new Userskill(resumeUpdateReqDto);

        if (userskillList != null) {
            for (String userskill : userskillList) {
                int result2 = userskillRepository.insert(us.getResumeId(), us.getUserId(), userskill);
                if (result2 != 1) {
                    throw new CustomApiException("보유기술작성 실패", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }

        }
    }

}
