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
import shop.mtcoding.pickme.dto.company.CompanyJoinRespDto;
import shop.mtcoding.pickme.dto.company.CompanyMypageDto;
import shop.mtcoding.pickme.dto.company.CompanyMypageRespDto;
import shop.mtcoding.pickme.dto.company.CompanyProfileReqDto;
import shop.mtcoding.pickme.dto.company.CompanyReq.CompanyJoinReqDto;
import shop.mtcoding.pickme.dto.company.CompanyReq.CompanyLoginReqDto;
import shop.mtcoding.pickme.dto.company.CompanyReq.CompanyMypageReqDto;
import shop.mtcoding.pickme.dto.company.CompanyResp.CompanyListRespDto;
import shop.mtcoding.pickme.handler.ex.CustomApiException;
import shop.mtcoding.pickme.handler.ex.CustomException;
import shop.mtcoding.pickme.model.Company;
import shop.mtcoding.pickme.model.CompanyRepository;
import shop.mtcoding.pickme.service.CompanyService;

@RequiredArgsConstructor
@RestController
public class CompanyController {

    private final CompanyService companyService;

    private final HttpSession session;

    private final CompanyRepository companyRepository;

    @PostMapping("/companyJoin")
    public ResponseEntity<ResponseDto<CompanyJoinRespDto>> companyJoin(
            @RequestBody @Validation CompanyJoinReqDto companyJoinReqDto) {

        CompanyJoinRespDto companyjoin = companyService.기업회원가입(companyJoinReqDto);

        return new ResponseEntity<>(new ResponseDto<>(1, "회원가입 성공", companyjoin), HttpStatus.OK);
    }

    @PostMapping("/companylogin")
    public ResponseEntity<?> companylogin(@RequestBody @Validation CompanyLoginReqDto companyLoginReqDto) {

        Company comPrincipal = companyService.기업로그인(companyLoginReqDto);
        session.setAttribute("comPrincipal", comPrincipal);
        return new ResponseEntity<>(new ResponseDto<>(1, "로그인 성공", comPrincipal), HttpStatus.OK);
    }

    @GetMapping("/companyJoinForm")
    public ResponseEntity<?> companyJoinForm() {
        return new ResponseEntity<>(new ResponseDto<>(1, "기업 회원가입 페이지 성공", null), HttpStatus.OK);
    }

    @GetMapping("/company/{id}/companyMyPage")
    public ResponseEntity<?> companyMyPage(@PathVariable int id) {
        // Company comprincipal = (Company) session.getAttribute("comPrincipal");
        // if (comprincipal == null) {
        // throw new CustomException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        // }
        // Company companyPS = companyRepository.findById(1);
        // if (companyPS == null) {
        // throw new CustomException("없는 기업정보를 수정할 수 없습니다");
        // }
        // if (companyPS.getId() != comprincipal.getId()) {
        // throw new CustomException("기업정보를 수정할 권한이 없습니다", HttpStatus.FORBIDDEN);
        // }
        // Company companyProfilePS = companyRepository.findById(1);
        // List<NoticeSelectRespDto> noticeSelectList =
        // noticeRepository.findAllWithNotice(1);

        CompanyMypageDto dto = companyRepository.companyJoinNotice(id);
        return new ResponseEntity<>(new ResponseDto<>(1, "성공", dto), HttpStatus.OK);
    }

    @PutMapping("/company/{id}")
    public @ResponseBody ResponseEntity<?> update(@PathVariable int id,
            @RequestBody @Validation CompanyMypageReqDto companyMypageReqDto) {
        Company comprincipal = (Company) session.getAttribute("comPrincipal");
        if (comprincipal == null) {
            throw new CustomApiException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }

        CompanyMypageRespDto companymypage = companyService.기업정보수정(id, companyMypageReqDto, 1);
        return new ResponseEntity<>(new ResponseDto<>(1, "기업정보수정성공", companymypage), HttpStatus.OK);
    }

    // 프로필 업데이트
    @PostMapping("/company/companyProfileUpdate")
    public ResponseEntity<?> companyProfileUpdate(@RequestBody CompanyProfileReqDto companyProfileReqDto) {
        Company comPrincipal = (Company) session.getAttribute("comPrincipal");
        if (comPrincipal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        if (companyProfileReqDto.getCompanyProfile().isEmpty()) {
            throw new CustomException("사진이 전송되지 않았습니다");
        }

        Company comPS = companyService.회사프로필사진수정(companyProfileReqDto, comPrincipal.getId());
        session.setAttribute("comPrincipal", comPS);

        return new ResponseEntity<>(new ResponseDto<>(1, "성공", comPS),
                HttpStatus.OK);
    }

    @GetMapping("/company/companyList")
    public ResponseEntity<?> companyList(CompanyListRespDto companyListRespDto) {
        List<CompanyListRespDto> companyList = companyRepository.findCompanyList();
        return new ResponseEntity<>(new ResponseDto<>(1, "성공", companyList), HttpStatus.OK);
    }
}
