package shop.mtcoding.pickme.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.pickme.dto.resume.ResumeReq.ResumeSaveReqDto;
import shop.mtcoding.pickme.dto.resume.ResumeReq.ResumeUpdateReqDto;

@Transactional
@MybatisTest
public class ResumeRepositoryTest {

    @Autowired
    private ResumeRepository resumeRepository;

    @Test
    public void insert_test() throws Exception {

        // given
        ResumeSaveReqDto resumeSaveReqDto = new ResumeSaveReqDto();
        resumeSaveReqDto.setId(1);
        resumeSaveReqDto.setUserId(1);
        resumeSaveReqDto.setResumeUsername("ssar");
        resumeSaveReqDto.setResumeBirth("1997-02-10");
        resumeSaveReqDto.setResumeEmail("hong@nate.com");
        resumeSaveReqDto.setResumeAddress("부산 사상");
        resumeSaveReqDto.setResumeLocation("부산");
        resumeSaveReqDto.setResumeCareer("경력 2년이상");
        resumeSaveReqDto.setResumeGrade("전문학사");
        resumeSaveReqDto.setResumePhoneNumber("01055558888");
        resumeSaveReqDto.setResumeSex("남");
        resumeSaveReqDto.setResumeContent("너너넝");
        resumeSaveReqDto.setUserskillList("spring");

        Resume resume = new Resume(resumeSaveReqDto);
        System.out.println("테스트2 pw : " + resumeSaveReqDto.getResumePhoneNumber());

        ObjectMapper om = new ObjectMapper();
        // when
        int result = resumeRepository.insert(resume);
        String responseBody = om.writeValueAsString(result);
        System.out.println("테스트 : " + responseBody);

        // then
        assertThat(result).isEqualTo(1);

    }

    @Test
    public void updatebyId_test() throws Exception {

        // given
        int id = 1;
        ResumeUpdateReqDto resumeUpdateReqDto = new ResumeUpdateReqDto();
        resumeUpdateReqDto.setId(1);
        resumeUpdateReqDto.setUserId(1);
        resumeUpdateReqDto.setResumeUsername("ssar");
        resumeUpdateReqDto.setResumeBirth("1997-02-10");
        resumeUpdateReqDto.setResumeEmail("hong@nate.com");
        resumeUpdateReqDto.setResumeAddress("부산 사상");
        resumeUpdateReqDto.setResumeLocation("부산");
        resumeUpdateReqDto.setResumeCareer("경력 2년이상");
        resumeUpdateReqDto.setResumeGrade("전문학사");
        resumeUpdateReqDto.setResumePhoneNumber("01055558888");
        resumeUpdateReqDto.setResumeSex("남");
        resumeUpdateReqDto.setResumeContent("너너넝");
        resumeUpdateReqDto.setUserskillList("spring");

        System.out.println("테스트 1 : " + resumeUpdateReqDto.getResumeContent());

        ObjectMapper om = new ObjectMapper();
        // when
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
        String responseBody = om.writeValueAsString(result);
        System.out.println("테스트 : " + responseBody);

        // then
        assertThat(result).isEqualTo(1);

    }

    @Test
    public void findByUserIdWithResume_test() throws Exception {
        // given
        int id = 1;
        ObjectMapper om = new ObjectMapper(); // Jackson

        // when
        ResumeSaveReqDto resumeSaveReqDto = resumeRepository.findByUserIdWithResume(id);
        String responseBody = om.writeValueAsString(resumeSaveReqDto);
        System.out.println("테스트 : " + responseBody);
        // then
        assertThat(resumeSaveReqDto.getResumeGrade()).isEqualTo("학사");
    }

}