package shop.mtcoding.pickme.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.pickme.dto.user.UserListRespDto;

@Transactional
@MybatisTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findUserList_test() throws Exception {
        // given
        ObjectMapper om = new ObjectMapper(); // Jackson

        // when
        List<UserListRespDto> userListRespDto = userRepository.findUserList();
        String responseBody = om.writeValueAsString(userListRespDto);
        System.out.println("테스트 : " + responseBody);
        // then
        assertThat(userListRespDto.get(1).getResumeUsername()).isEqualTo("김정욱");
    }
}
