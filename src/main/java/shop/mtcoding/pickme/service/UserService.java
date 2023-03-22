package shop.mtcoding.pickme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import shop.mtcoding.pickme.dto.user.UserReq.UserJoinReqDto;
import shop.mtcoding.pickme.dto.user.UserReq.UserLoginReqDto;
import shop.mtcoding.pickme.dto.user.UserReq.UserMyPageReqDto;
import shop.mtcoding.pickme.handler.ex.CustomApiException;
import shop.mtcoding.pickme.handler.ex.CustomException;
import shop.mtcoding.pickme.model.User;
import shop.mtcoding.pickme.model.UserRepository;
import shop.mtcoding.pickme.util.PathUtil;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void 회원가입(UserJoinReqDto userJoinReqDto) {
        User sameUser = userRepository.findByUsername(userJoinReqDto.getUserName());
        if (sameUser != null) {
            throw new CustomException("동일한 username이 존재합니다");
        }
        int result = userRepository.insert(userJoinReqDto);
        
        if (result != 1) {
            throw new CustomException("회원가입실패");
        }
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
    public void 회원정보수정(int id, UserMyPageReqDto userMyPageReqDto, int principalId) {
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
    }

    @Transactional
    public User 유저프로필사진수정(MultipartFile userProfile, Integer userPrincipalId) {
        String uuidImageName = PathUtil.writeImageFile(userProfile);

        User userPS = userRepository.findById(userPrincipalId);
        userPS.setUserProfile(uuidImageName);
        userRepository.updateUserProfile(userPS.getId(), userPS.getUserName(), userPS.getUserPassword(),
                userPS.getUserEmail(),
                userPS.getUserProfile());
        return userPS;
    }
}
