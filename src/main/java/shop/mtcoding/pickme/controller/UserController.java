package shop.mtcoding.pickme.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.pickme.config.annotation.Validation;
import shop.mtcoding.pickme.config.auth.JwtProvider;
import shop.mtcoding.pickme.config.auth.LoginUser;
import shop.mtcoding.pickme.dto.ResponseDto;
import shop.mtcoding.pickme.dto.notice.NoticeMainRespDto;
import shop.mtcoding.pickme.dto.resume.ResumeResp.ResumeSelectRespDto;
import shop.mtcoding.pickme.dto.user.UserJoinRespDto;
import shop.mtcoding.pickme.dto.user.UserListRespDto;
import shop.mtcoding.pickme.dto.user.UserMyPageDto;
import shop.mtcoding.pickme.dto.user.UserMyPageRespDto;
import shop.mtcoding.pickme.dto.user.UserProfileReqDto;
import shop.mtcoding.pickme.dto.user.UserReq.UserJoinReqDto;
import shop.mtcoding.pickme.dto.user.UserReq.UserLoginReqDto;
import shop.mtcoding.pickme.dto.user.UserReq.UserMyPageReqDto;
import shop.mtcoding.pickme.model.CompanyRepository;
import shop.mtcoding.pickme.model.Companyskill;
import shop.mtcoding.pickme.model.CompanyskillRepository;
import shop.mtcoding.pickme.model.Notice;
import shop.mtcoding.pickme.model.NoticeRepository;
import shop.mtcoding.pickme.model.User;
import shop.mtcoding.pickme.model.UserRepository;
import shop.mtcoding.pickme.model.Userskill;
import shop.mtcoding.pickme.model.UserskillRepository;
import shop.mtcoding.pickme.service.UserService;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserRepository userRepository;

    private final NoticeRepository noticeRepository;

    private final UserskillRepository userskillRepository;

    private final CompanyskillRepository companyskillRepository;

    private final UserService userService;

    private final HttpSession session;

    private final CompanyRepository companyRepository;

    @PutMapping("/user/{id}")
    public @ResponseBody ResponseEntity<?> MyPage(@PathVariable int id,
            @RequestBody @Validation UserMyPageReqDto userMyPageReqDto) {
        LoginUser loginUser = (LoginUser) session.getAttribute("userPrincipal");
        UserMyPageRespDto usermypage = userService.회원정보수정(id, userMyPageReqDto, loginUser.getId());
        return new ResponseEntity<>(new ResponseDto<>(1, "정보수정완료", usermypage), HttpStatus.OK);
    }

    @PostMapping("/ns/userJoin")
    public ResponseEntity<ResponseDto<UserJoinRespDto>> join(@RequestBody @Validation UserJoinReqDto userJoinReqDto) {
        UserJoinRespDto userjoin = userService.회원가입(userJoinReqDto);
        return new ResponseEntity<>(new ResponseDto<>(1, "성공", userjoin), HttpStatus.OK);
    }

    @PostMapping("/ns/userlogin")
    public ResponseEntity<?> userlogin(@RequestBody @Validation UserLoginReqDto userLoginReqDto, HttpSession session) {
        Optional<User> userOptional = userRepository.findByUsernameAndPassword(
                userLoginReqDto.getUserName(), userLoginReqDto.getUserPassword());

        if (userOptional.isPresent()) { // 값이 있다면
            String jwt = JwtProvider.create(userOptional.get());
            return ResponseEntity.ok().header(JwtProvider.HEADER, jwt).body("로그인 성공");
        } else {
            return ResponseEntity.badRequest().body("로그인 실패");
        }
    }

    /*
     * 23. 3. 5. 안정훈
     * 추천공고 페이지 호출
     */
    @GetMapping("/user/userSkillMatchForm")
    public ResponseEntity<?> userSkillMatchForm(@RequestParam(name = "resumeId", defaultValue = "1") int resumeId) {
        LoginUser loginUser = (LoginUser) session.getAttribute("userPrincipal");

        List<Notice> userSkillMatch = userskillRepository.findByCompanyskillName(loginUser.getId(), resumeId);
        List<Userskill> Uskill = userskillRepository.findByUserId(loginUser.getId());

        for (int i = 0; i < userSkillMatch.size(); i++) {// 공고문 수만큼 도는 for문

            userSkillMatch.get(i)
                    .setSkill(companyskillRepository.findByNoticeId(userSkillMatch.get(i).getId()));
            userSkillMatch.get(i)
                    .setCompanyProfile(
                            companyRepository.findById(userSkillMatch.get(i).getCompanyId()).getCompanyProfile());

            List<Companyskill> Cskill = userSkillMatch.get(i).getSkill();
            for (int x = 0; x < Uskill.size(); x++) {// user가 가진 skill 수만큼 도는 for문
                for (int j = 0; j < Cskill.size(); j++) {// notice가 가진 skill 수만큼 도는 for문
                    if (Cskill.get(j).getCompanyskillName().equals(Uskill.get(x).getUserskillName())) {
                        Companyskill skill = Cskill.get(j);
                        skill.setColor("Y");
                        Cskill.set(j, skill);
                    }
                } // for j end
            } // for x end
        } // for i end

        return new ResponseEntity<>(userSkillMatch, HttpStatus.OK);
    }

    @GetMapping("/ns/main")
    public ResponseEntity<?> main() {
        List<NoticeMainRespDto> noticeMainList = noticeRepository.findMainList();
        return new ResponseEntity<>(new ResponseDto<>(1, "성공", noticeMainList), HttpStatus.OK);
    }

    @GetMapping("/ns/user/userList")
    public ResponseEntity<?> userList() {
        List<UserListRespDto> userList = userRepository.findUserList();
        return new ResponseEntity<>(new ResponseDto<>(1, "성공", userList), HttpStatus.OK);
    }

    @GetMapping("/user/{id}/userMyPage")
    public ResponseEntity<?> MyPage(@PathVariable int id) {
        UserMyPageDto dto = userRepository.userJoinResume(id);
        return new ResponseEntity<>(new ResponseDto<>(1, "성공", dto), HttpStatus.OK);
    }

    @PostMapping("/user/userProfileUpdate")
    public ResponseEntity<?> userProfileUpdate(@RequestBody UserProfileReqDto userProfileReqDto) {
        LoginUser loginUser = (LoginUser) session.getAttribute("userPrincipal");
        if (userProfileReqDto.getUserProfile().isEmpty()) {
            return ResponseEntity.badRequest().body("사진이 전송되지 않았습니다");
        }
        User userPS = userService.유저프로필사진수정(userProfileReqDto, loginUser.getId());
        session.setAttribute("userPrincipal", userPS.getUserProfile());

        return new ResponseEntity<>(new ResponseDto<>(1, "성공", userPS), HttpStatus.OK);
    }

}