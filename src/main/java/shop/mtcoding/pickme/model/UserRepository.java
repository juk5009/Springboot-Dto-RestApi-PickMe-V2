package shop.mtcoding.pickme.model;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.pickme.dto.user.UserListRespDto;
import shop.mtcoding.pickme.dto.user.UserMyPageDto;
import shop.mtcoding.pickme.dto.user.UserReq.UserJoinReqDto;

@Mapper
public interface UserRepository {
        public int insert(UserJoinReqDto userJoinReqDto);

        public List<UserListRespDto> findUserList();

        public User findByUsername(String userName);

        public int updateById(@Param("id") int id, @Param("userName") String userName,
                        @Param("userPassword") String userPassword, @Param("userEmail") String userEmail);

        public int deleteById(int id);

        public List<User> findAll();

        public User findById(int id);

        public Optional<User> findByUsernameAndPassword(@Param("userName") String userName,
                        @Param("userPassword") String userPassword);

        public int updateUserProfile(@Param("id") int id, @Param("userProfile") String userProfile);

        public UserMyPageDto userJoinResume(int id);

        public UserMyPageDto findByResume(int userId);

}
