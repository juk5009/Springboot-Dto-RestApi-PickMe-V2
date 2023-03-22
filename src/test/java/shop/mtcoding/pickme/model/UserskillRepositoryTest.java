package shop.mtcoding.pickme.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.pickme.dto.resume.ResumeReq.ResumeSaveReqDto;
import shop.mtcoding.pickme.dto.userskill.UserskillReqDto.UserskillSaveReqDto;

@Transactional
@MybatisTest
public class UserskillRepositoryTest {

    @Autowired
    private UserskillRepository userskillRespository;

    @Test
    public void insert_test() throws Exception {

        // given
        UserskillSaveReqDto userskillSaveReqDto = new UserskillSaveReqDto();
        ResumeSaveReqDto resumeSaveReqDto = new ResumeSaveReqDto();

        userskillSaveReqDto.setResumeId(1);
        userskillSaveReqDto.setUserskillName("java");
        resumeSaveReqDto.setUserId(1);

        System.out.println("테스트 : " + userskillSaveReqDto.getUserskillName());

        ObjectMapper om = new ObjectMapper();
        // when
        int result = userskillRespository.insert(userskillSaveReqDto.getResumeId(), resumeSaveReqDto.getUserId(),
                userskillSaveReqDto.getUserskillName());
        String responseBody = om.writeValueAsString(result);
        System.out.println("테스트 : " + responseBody);

        // then
        assertThat(result).isEqualTo(1);

    }

}