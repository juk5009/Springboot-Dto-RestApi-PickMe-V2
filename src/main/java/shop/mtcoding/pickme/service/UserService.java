package shop.mtcoding.pickme.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.pickme.dto.user.UserJoinRespDto;
import shop.mtcoding.pickme.dto.user.UserMyPageRespDto;
import shop.mtcoding.pickme.dto.user.UserProfileReqDto;
import shop.mtcoding.pickme.dto.user.UserReq.UserJoinReqDto;
import shop.mtcoding.pickme.dto.user.UserReq.UserLoginReqDto;
import shop.mtcoding.pickme.dto.user.UserReq.UserMyPageReqDto;
import shop.mtcoding.pickme.handler.ex.CustomApiException;
import shop.mtcoding.pickme.handler.ex.CustomException;
import shop.mtcoding.pickme.model.User;
import shop.mtcoding.pickme.model.UserRepository;
import shop.mtcoding.pickme.util.PathUtil;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserJoinRespDto 회원가입(UserJoinReqDto userJoinReqDto) {
        User sameUser = userRepository.findByUsername(userJoinReqDto.getUserName());
        if (sameUser != null) {
            throw new CustomException("동일한 username이 존재합니다");
        }
        int result = userRepository.insert(userJoinReqDto);

        if (result != 1) {
            throw new CustomException("회원가입실패");
        }
        User user = userRepository.findByUsername(userJoinReqDto.getUserName());
        UserJoinRespDto userjoin = new UserJoinRespDto();
        userjoin.setUserName(user.getUserName());
        ;
        userjoin.setUserPassword(user.getUserPassword());
        ;
        userjoin.setUserEmail(user.getUserEmail());
        ;
        return userjoin;
    }

    @Transactional
    public User 유저로그인(UserLoginReqDto userLoginReqDto) {
        User userPrincipal = userRepository.findByUsernameAndPassword(userLoginReqDto.getUserName(),
                userLoginReqDto.getUserPassword());
        if (userPrincipal == null) {
            throw new CustomException("아이디 혹은 패스워드가 잘못 입력되었습니다.");
        }
        return userPrincipal;
    }

    @Transactional
    public UserMyPageRespDto 회원정보수정(int id, UserMyPageReqDto userMyPageReqDto, int principalId) {
        User userPS = userRepository.findById(id);
        if (userPS == null) {
            throw new CustomApiException("회원정보를 찾을 수 없습니다");
        }
        if (userPS.getId() != principalId) {
            throw new CustomApiException("회원정보를 수정할 권한이 없습니다", HttpStatus.FORBIDDEN);
        }
        int result = userRepository.updateById(principalId, userMyPageReqDto.getUserName(),
                userMyPageReqDto.getUserPassword(), userMyPageReqDto.getUserEmail());
        if (result != 1) {
            throw new CustomApiException("회원정보 수정에 실패하였습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        userPS = userRepository.findById(id);
        return new UserMyPageRespDto(userPS.getUserName(), userPS.getUserPassword(), userPS.getUserEmail());
    }

    @Transactional
    public User 유저프로필사진수정(UserProfileReqDto userProfileReqDto, Integer userPrincipalId) {

        User userPS = userRepository.findById(userPrincipalId);
        userPS.setUserProfile(userProfileReqDto.getUserProfile());
        userPS.setId(userPrincipalId);

        userRepository.updateUserProfile(userPS.getId(), userPS.getUserProfile());

        return userPS;
    }
}
