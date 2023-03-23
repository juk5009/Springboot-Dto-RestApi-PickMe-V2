package shop.mtcoding.pickme.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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

import shop.mtcoding.pickme.dto.resume.ResumeReq.ResumeSaveReqDto;
import shop.mtcoding.pickme.dto.resume.ResumeReq.ResumeUpdateReqDto;
import shop.mtcoding.pickme.model.User;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class ResumeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    private MockHttpSession mockSession;

    @BeforeEach // Test 메서드 실행 직전 마다 호출됨
    public void setUp() {
        // 테이블 insert

        // 세션 주입
        User user = new User();
        user.setId(1);
        user.setUserName("ssar");
        user.setUserPassword("1234");
        user.setUserEmail("ssar@nate.com");
        user.setUserProfile("/images/samsung.png");
        user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));

        mockSession = new MockHttpSession();
        mockSession.setAttribute("userPrincipal", user);
    }

    // private Integer id;
    // private Integer userId;
    // private String resumeUsername;
    // private String resumeBirth;
    // private String resumeEmail;
    // private String resumeAddress;
    // private String resumeLocation;
    // private String resumeCareer;
    // private String resumeGrade;
    // private String resumePhoneNumber;
    // private String resumeSex;
    // private String resumeContent;
    // private String userskillList;

    @Test
    public void resume_test() throws Exception {
        // given
        ResumeSaveReqDto resumeSaveReqDto = new ResumeSaveReqDto();
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

        String requestBody = om.writeValueAsString(resumeSaveReqDto);
        // when
        ResultActions resultActions = mvc.perform(
                post("/saveResume")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .session(mockSession));

        System.out.println("테스트 : " + requestBody);

        // then
        resultActions.andExpect(status().isCreated());
    }

    @Test
    public void deleteResume_test() throws Exception {
        // given
        int id = 1;

        // when
        ResultActions resultActions = mvc.perform(
                delete("/resume/" + id).session(mockSession));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("delete_test : " + responseBody);

        resultActions.andExpect(jsonPath("$.code").value(1));
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void updateResume_test() throws Exception {
        // given
        int id = 1;
        ResumeUpdateReqDto resumeUpdateReqDto = new ResumeUpdateReqDto();
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

        String requestBody = om.writeValueAsString(resumeUpdateReqDto);

        System.out.println("테스트 : " + requestBody);
        // when
        ResultActions resultActions = mvc.perform(put("/resume/" + id)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .session(mockSession));

        // then
        resultActions.andExpect(status().isCreated());
        resultActions.andExpect(jsonPath("$.code").value(1));
    }

}