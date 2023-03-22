package shop.mtcoding.pickme.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.pickme.dto.notice.NoticeReq.NoticeSaveReqDto;
import shop.mtcoding.pickme.dto.notice.NoticeReq.NoticeUpdateReqDto;
import shop.mtcoding.pickme.dto.notice.NoticeResp.NoticeMainRespDto;
import shop.mtcoding.pickme.dto.resume.ResumeResp.ResumeSelectRespDto;

@Transactional
@MybatisTest
public class NoticeRepositoryTest {

    @Autowired
    private NoticeRepository noticeRepository;

    @Test
    public void insert_test() throws Exception {

        // given
        NoticeSaveReqDto noticeSaveReqDto = new NoticeSaveReqDto();
        noticeSaveReqDto.setCompanyId(1);
        noticeSaveReqDto.setNoticeCompanyname("lg");
        noticeSaveReqDto.setNoticeTitle("다다닥");
        noticeSaveReqDto.setNoticeCareer("1년");
        noticeSaveReqDto.setNoticeEmploytype("정규직");
        noticeSaveReqDto.setNoticeGrade("학사");
        noticeSaveReqDto.setNoticeLocation("서울");
        noticeSaveReqDto.setNoticePay("5000");
        noticeSaveReqDto.setNoticeContent("다다다닥");

        Notice notice = new Notice(noticeSaveReqDto);
        System.out.println("테스트 1 : " + noticeSaveReqDto.getNoticeContent());

        ObjectMapper om = new ObjectMapper();
        // when
        int result = noticeRepository.insert(notice);
        String responseBody = om.writeValueAsString(result);
        System.out.println("테스트 : " + responseBody);

        // then
        assertThat(result).isEqualTo(1);

    }

    @Test
    public void updatebyId_test() throws Exception {

        // given
        int id = 1;
        NoticeUpdateReqDto noticeUpdateReqDto = new NoticeUpdateReqDto();
        noticeUpdateReqDto.setCompanyId(1);
        noticeUpdateReqDto.setNoticeCompanyname("lg");
        noticeUpdateReqDto.setNoticeTitle("다다닥");
        noticeUpdateReqDto.setNoticeCareer("1년");
        noticeUpdateReqDto.setNoticeEmploytype("정규직");
        noticeUpdateReqDto.setNoticeGrade("학사");
        noticeUpdateReqDto.setNoticeLocation("서울");
        noticeUpdateReqDto.setNoticePay("5000");
        noticeUpdateReqDto.setNoticeContent("다다다닥");

        System.out.println("테스트 1 : " + noticeUpdateReqDto.getNoticeContent());

        ObjectMapper om = new ObjectMapper();
        // when
        int result = noticeRepository.updateById(id,
                noticeUpdateReqDto.getNoticeTitle(),
                noticeUpdateReqDto.getNoticeCompanyname(),
                noticeUpdateReqDto.getNoticeCareer(),
                noticeUpdateReqDto.getNoticeEmploytype(),
                noticeUpdateReqDto.getNoticeLocation(),
                noticeUpdateReqDto.getNoticePay(), noticeUpdateReqDto.getNoticeContent(),
                noticeUpdateReqDto.getNoticeGrade());
        String responseBody = om.writeValueAsString(result);
        System.out.println("테스트 : " + responseBody);

        // then
        assertThat(result).isEqualTo(1);

    }

    @Test
    public void findMainList_test() throws Exception {
        // given
        ObjectMapper om = new ObjectMapper(); // Jackson

        // when
        List<NoticeMainRespDto> noticeMainRespDto = noticeRepository.findMainList();
        String responseBody = om.writeValueAsString(noticeMainRespDto);
        System.out.println("테스트 : " + responseBody);
        // then
        assertThat(noticeMainRespDto.get(1).getNoticeCompanyname()).isEqualTo("삼성");
    }

    @Test
    public void findByCompanyIdWithNotice_test() throws Exception {
        // given
        int id = 1;
        ObjectMapper om = new ObjectMapper(); // Jackson

        // when
        NoticeSaveReqDto noticeSaveReqDto = noticeRepository.findByCompanyIdWithNotice(id);
        String responseBody = om.writeValueAsString(noticeSaveReqDto);
        System.out.println("테스트 : " + responseBody);
        // then
        assertThat(noticeSaveReqDto.getNoticeCareer()).isEqualTo("경력 2년이상");
    }

    @Test
    public void findAllWithResume_test() throws Exception {
        // given
        ObjectMapper om = new ObjectMapper(); // Jackson

        // when
        List<ResumeSelectRespDto> resumeSelectRespDto = noticeRepository.findAllWithResume();
        String responseBody = om.writeValueAsString(resumeSelectRespDto);
        System.out.println("테스트 : " + responseBody);
        // then
        assertThat(resumeSelectRespDto.get(1).getResumeUsername()).isEqualTo("김정욱");
    }

}