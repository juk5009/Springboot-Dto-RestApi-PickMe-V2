package shop.mtcoding.pickme.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.pickme.dto.companyskill.CompanyskillReqDto.CompanyskillSaveReqDto;
import shop.mtcoding.pickme.dto.notice.NoticeReq.NoticeSaveReqDto;

@Transactional
@MybatisTest
public class CompanyskillRepositoryTest {

    @Autowired
    private CompanyskillRepository companyskillRepository;

    @Test
    public void insert_test() throws Exception {

        // given
        CompanyskillSaveReqDto companyskillSaveReqDto = new CompanyskillSaveReqDto();
        NoticeSaveReqDto noticeSaveReqDto = new NoticeSaveReqDto();

        companyskillSaveReqDto.setNoticeId(1);
        companyskillSaveReqDto.setCompanyskillName("java");
        noticeSaveReqDto.setCompanyId(1);

        System.out.println("테스트 : " + companyskillSaveReqDto.getCompanyskillName());

        ObjectMapper om = new ObjectMapper();
        // when
        int result = companyskillRepository.insert(companyskillSaveReqDto.getNoticeId(),
                noticeSaveReqDto.getCompanyId(), companyskillSaveReqDto.getCompanyskillName());
        String responseBody = om.writeValueAsString(result);
        System.out.println("테스트 : " + responseBody);

        // then
        assertThat(result).isEqualTo(1);

    }

}