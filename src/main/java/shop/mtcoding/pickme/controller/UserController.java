package shop.mtcoding.pickme.controller;

import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.pickme.config.annotation.Validation;
import shop.mtcoding.pickme.dto.ResponseDto;
import shop.mtcoding.pickme.dto.notice.NoticeMainRespDto;
import shop.mtcoding.pickme.dto.resume.ResumeResp.ResumeSelectRespDto;
import shop.mtcoding.pickme.dto.user.UserListRespDto;
import shop.mtcoding.pickme.dto.user.UserMyPageDto;
import shop.mtcoding.pickme.dto.user.UserReq.UserJoinReqDto;
import shop.mtcoding.pickme.dto.user.UserReq.UserLoginReqDto;
import shop.mtcoding.pickme.dto.user.UserReq.UserMyPageReqDto;
import shop.mtcoding.pickme.handler.ex.CustomApiException;
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
        User principal = (User) session.getAttribute("userPrincipal");
        if (principal == null) {
            throw new CustomApiException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }

        userService.회원정보수정(id, userMyPageReqDto, principal.getId());

        return new ResponseEntity<>(new ResponseDto<>(1, "정보수정완료", null), HttpStatus.OK);
    }

    @PostMapping("/userJoin")
    public @ResponseBody ResponseEntity<?> join(@RequestBody @Validation UserJoinReqDto userJoinReqDto) {

        userService.회원가입(userJoinReqDto);

        return new ResponseEntity<>(new ResponseDto<>(1, "성공", null), HttpStatus.OK);
    }

    @GetMapping("/loginForm")
    public ResponseDto<?> loginForm() {
        return new ResponseDto<>(1, "로그인 페이지 불러오기 성공", null);
    }

    @PostMapping("/userlogin")
    public ResponseEntity<?> userlogin(@RequestBody @Validation UserLoginReqDto userLoginReqDto) {

        User userPrincipal = userService.유저로그인(userLoginReqDto);
        session.setAttribute("userPrincipal", userPrincipal);
        return new ResponseEntity<>(new ResponseDto<>(1, "성공", null), HttpStatus.OK);
    }

    /*
     * 23. 3. 5. 안정훈
     * 추천공고 페이지 호출
     */
    @GetMapping("/user/userSkillMatchForm")
    public ResponseEntity<?> userSkillMatchForm(@RequestParam(name = "resumeId", defaultValue = "1") int resumeId) {
        // if (principal == null) {
        // throw new CustomException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        // }

        List<Notice> userSkillMatch = userskillRepository.findByCompanyskillName(1, resumeId);
        List<Userskill> Uskill = userskillRepository.findByUserId(1);

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

    @GetMapping("/")
    public ResponseEntity<?> main() {
        List<NoticeMainRespDto> noticeMainList = noticeRepository.findMainList();
        return new ResponseEntity<>(new ResponseDto<>(1, "성공", noticeMainList), HttpStatus.OK);
    }

    @GetMapping("/user/userList")
    public ResponseEntity<?> userList() {
        List<UserListRespDto> userList = userRepository.findUserList();
        return new ResponseEntity<>(new ResponseDto<>(1, "성공", userList), HttpStatus.OK);
    }

    @GetMapping("/userJoinForm")
    public String userJoinForm() {
        return "user/userJoinForm";
    }

    @GetMapping("/user/resumeForm")
    public String resumeForm() {
        return "user/resumeForm";
    }

    @GetMapping("/user/joinType")
    public String joinType() {
        return "user/joinType";
    }

    @GetMapping("/user/{id}/userMyPage")
    public ResponseEntity<?> MyPage(@PathVariable int id) {
        // User principal = (User) session.getAttribute("userPrincipal");
        // if (principal == null) {
        // throw new CustomException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        // }
        User userPS = userRepository.findById(1);
        // if (userPS == null) {
        // throw new CustomException("해당 정보를 수정할 수 없습니다");
        // }
        // if (userPS.getUserName() != principal.getUserName()) {
        // throw new CustomException("해당정보를 수정할 권한이 없습니다", HttpStatus.FORBIDDEN);
        // }
        List<ResumeSelectRespDto> resumeSelectList = noticeRepository.findAllWithResume();
        UserMyPageDto dto = userRepository.userJoinResume(id);
        return new ResponseEntity<>(new ResponseDto<>(1, "성공", dto), HttpStatus.OK);
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/user/userProfileUpdate")
    public ResponseEntity<String> userProfileUpdate(MultipartFile userProfile) {
        // User userPrincipal = (User) session.getAttribute("userPrincipal");
        // if (userPrincipal == null) {
        // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다");
        // }
        // if (userProfile.isEmpty()) {
        // return ResponseEntity.badRequest().body("사진이 전송되지 않았습니다");
        // }
        try {
            User userPS = userService.유저프로필사진수정(userProfile, 1);
            session.setAttribute("userPrincipal", userPS);
            return ResponseEntity.ok("프로필 사진이 업데이트 되었습니다");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류가 발생하였습니다");
        }
    }

}