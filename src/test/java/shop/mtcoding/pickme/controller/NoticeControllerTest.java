package shop.mtcoding.pickme.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.pickme.dto.notice.NoticeReq.NoticeSaveReqDto;
import shop.mtcoding.pickme.dto.notice.NoticeReq.NoticeUpdateReqDto;
import shop.mtcoding.pickme.model.Company;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class NoticeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    private MockHttpSession mockSession;

    @BeforeEach // Test 메서드 실행 직전 마다 호출됨
    public void setUp() {
        // 테이블 insert

        // 세션 주입
        Company company = new Company();
        company.setId(1);
        company.setCompanyName("samsam");
        company.setCompanyPassword("1234");
        company.setCompanyEmail("samsam@nate.com");
        company.setCompanyProfile("/images/samsung.png");

        mockSession = new MockHttpSession();
        mockSession.setAttribute("comPrincipal", company);
    }

    @Test
    public void notice_test() throws Exception {
        // given

        NoticeSaveReqDto noticeSaveReqDto = new NoticeSaveReqDto();
        noticeSaveReqDto.setNoticeCompanyname("lg");
        noticeSaveReqDto.setNoticeTitle("다다다닥");
        noticeSaveReqDto.setNoticeEmploytype("정규직");
        noticeSaveReqDto.setNoticePay("3500");
        noticeSaveReqDto.setNoticeGrade("전문학사");
        noticeSaveReqDto.setNoticeLocation("부산");
        noticeSaveReqDto.setNoticeContent("다다다다다다닥");
        noticeSaveReqDto.setNoticeCareer("1년");
        noticeSaveReqDto.setCompanyskillList("java,jsp");

        String requestBody = om.writeValueAsString(noticeSaveReqDto);
        // when
        ResultActions resultActions = mvc.perform(
                post("/saveNotice")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .session(mockSession));

        System.out.println("테스트 : " + requestBody);

        // then
        resultActions.andExpect(status().isCreated());
    }

    @Test
    public void deleteNotice_test() throws Exception {
        // given
        int id = 1;

        // when
        ResultActions resultActions = mvc.perform(
                delete("/notice/" + id).session(mockSession));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("delete_test : " + responseBody);

        resultActions.andExpect(jsonPath("$.code").value(1));
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void updateNotice_test() throws Exception {
        // given
        int id = 1;
        NoticeUpdateReqDto noticeUpdateReqDto = new NoticeUpdateReqDto();
        noticeUpdateReqDto.setCompanyId(1);
        noticeUpdateReqDto.setNoticeCompanyname("lg");
        noticeUpdateReqDto.setNoticeTitle("다다다닥");
        noticeUpdateReqDto.setNoticeEmploytype("정규직");
        noticeUpdateReqDto.setNoticePay("3500");
        noticeUpdateReqDto.setNoticeGrade("전문학사");
        noticeUpdateReqDto.setNoticeLocation("부산");
        noticeUpdateReqDto.setNoticeContent("다다다다다다닥");
        noticeUpdateReqDto.setNoticeCareer("1년");
        noticeUpdateReqDto.setCompanyskillList("java");

        String requestBody = om.writeValueAsString(noticeUpdateReqDto);

        System.out.println("테스트 : " + requestBody);
        // when
        ResultActions resultActions = mvc.perform(put("/notice/" + id)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .session(mockSession));

        // then
        resultActions.andExpect(status().isCreated());
        resultActions.andExpect(jsonPath("$.code").value(1));
    }

}