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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.pickme.dto.ResponseDto;
import shop.mtcoding.pickme.dto.company.CompanyMypageDto;
import shop.mtcoding.pickme.dto.company.CompanyReq.CompanyJoinReqDto;
import shop.mtcoding.pickme.dto.company.CompanyReq.CompanyLoginReqDto;
import shop.mtcoding.pickme.dto.company.CompanyReq.CompanyMypageReqDto;
import shop.mtcoding.pickme.dto.company.CompanyResp.CompanyListRespDto;
import shop.mtcoding.pickme.dto.notice.NoticeSelectRespDto;
import shop.mtcoding.pickme.handler.ex.CustomApiException;
import shop.mtcoding.pickme.handler.ex.CustomException;
import shop.mtcoding.pickme.model.Company;
import shop.mtcoding.pickme.model.CompanyRepository;
import shop.mtcoding.pickme.model.NoticeRepository;
import shop.mtcoding.pickme.service.CompanyService;

@RequiredArgsConstructor
@RestController
public class CompanyController {

    private final CompanyService companyService;

    private final HttpSession session;

    private final CompanyRepository companyRepository;

    private final NoticeRepository noticeRepository;

    @PostMapping("/companyJoin")
    public String companyJoin(CompanyJoinReqDto companyJoinReqDto) {
        if (companyJoinReqDto.getCompanyName() == null ||
                companyJoinReqDto.getCompanyName().isEmpty()) {
            throw new CustomException("CompanyName을 입력해주세요", HttpStatus.BAD_REQUEST);
        }
        if (companyJoinReqDto.getCompanyPassword() == null ||
                companyJoinReqDto.getCompanyPassword().isEmpty()) {
            throw new CustomException("CompanyPassword을 입력해주세요", HttpStatus.BAD_REQUEST);
        }
        if (companyJoinReqDto.getCompanyEmail() == null ||
                companyJoinReqDto.getCompanyEmail().isEmpty()) {
            throw new CustomException("CompanyEmail입력해주세요", HttpStatus.BAD_REQUEST);
        }

        companyService.기업회원가입(companyJoinReqDto);

        return "redirect:/loginForm";
    }

    @PostMapping("/companylogin")
    public String companylogin(CompanyLoginReqDto companyLoginReqDto) {
        if (companyLoginReqDto.getCompanyName() == null || companyLoginReqDto.getCompanyName().isEmpty()) {
            throw new CustomException("CompanyName을 작성해주세요");
        }
        if (companyLoginReqDto.getCompanyPassword() == null || companyLoginReqDto.getCompanyPassword().isEmpty()) {
            throw new CustomException("CompanyPassword을 작성해주세요");
        }
        Company comPrincipal = companyService.기업로그인(companyLoginReqDto);
        session.setAttribute("comPrincipal", comPrincipal);
        return "redirect:/";
    }

    @GetMapping("/companyJoinForm")
    public String companyJoinForm() {
        return "company/companyJoinForm";
    }

    @GetMapping("/company/{id}/companyMyPage")
    public ResponseEntity<?> companyMyPage(@PathVariable int id) {
        // Company comprincipal = (Company) session.getAttribute("comPrincipal");
        // if (comprincipal == null) {
        // throw new CustomException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        // }
        Company companyPS = companyRepository.findById(id);
        // if (companyPS == null) {
        // throw new CustomException("없는 기업정보를 수정할 수 없습니다");
        // }
        // if (companyPS.getId() != comprincipal.getId()) {
        // throw new CustomException("기업정보를 수정할 권한이 없습니다", HttpStatus.FORBIDDEN);
        // }
        Company companyProfilePS = companyRepository.findById(1);
        List<NoticeSelectRespDto> noticeSelectList = noticeRepository.findAllWithNotice();

        CompanyMypageDto dto = new CompanyMypageDto();
        dto.setCompanyPS(companyPS);
        dto.setCompanyProfilePS(companyProfilePS);
        dto.setNoticeSelectList(noticeSelectList);
        return new ResponseEntity<>(new ResponseDto<>(1, "성공", dto), HttpStatus.OK);
    }

    @PutMapping("/company/{id}")
    public @ResponseBody ResponseEntity<?> update(@PathVariable int id,
            @RequestBody CompanyMypageReqDto companyMypageReqDto) {
        Company comprincipal = (Company) session.getAttribute("comPrincipal");
        if (comprincipal == null) {
            throw new CustomApiException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }
        if (companyMypageReqDto.getCompanyName() == null || companyMypageReqDto.getCompanyName().isEmpty()) {
            throw new CustomApiException("CompanyName을 작성해주세요");
        }
        if (companyMypageReqDto.getCompanyPassword() == null || companyMypageReqDto.getCompanyPassword().isEmpty()) {
            throw new CustomApiException("CompanyPassword를 작성해주세요");
        }
        if (companyMypageReqDto.getCompanyEmail() == null || companyMypageReqDto.getCompanyEmail().isEmpty()) {
            throw new CustomApiException("CompanyEmail을 작성해주세요");
        }
        companyService.기업정보수정(id, companyMypageReqDto, comprincipal.getId());
        return new ResponseEntity<>(new ResponseDto<>(1, "기업정보수정성공", null), HttpStatus.OK);
    }

    @PostMapping("/company/companyProfileUpdate")
    public String companyProfileUpdate(MultipartFile companyProfile) {
        Company comPrincipal = (Company) session.getAttribute("comPrincipal");
        if (comPrincipal == null) {
            return "redirect:/loginForm";
        }
        if (companyProfile.isEmpty()) {
            throw new CustomException("사진이 전송되지 않았습니다");
        }
        Company comPS = companyService.유저프로필사진수정(companyProfile, comPrincipal.getId());
        session.setAttribute("comPrincipal", comPS);

        return "redirect:/";
    }

    @GetMapping("/company/companyList")
    public ResponseEntity<?> companyList(CompanyListRespDto companyListRespDto) {
        List<CompanyListRespDto> companyList = companyRepository.findCompanyList();
        return new ResponseEntity<>(new ResponseDto<>(1, "성공", companyList), HttpStatus.OK);
    }
}
