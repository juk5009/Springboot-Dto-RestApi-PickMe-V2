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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.pickme.config.annotation.Validation;
import shop.mtcoding.pickme.config.auth.JwtProvider;
import shop.mtcoding.pickme.config.auth.LoginCompany;
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

    @PostMapping("/nc/companyJoin")
    public ResponseEntity<ResponseDto<CompanyJoinRespDto>> companyJoin(
            @RequestBody @Validation CompanyJoinReqDto companyJoinReqDto) {
        CompanyJoinRespDto companyjoin = companyService.기업회원가입(companyJoinReqDto);
        return new ResponseEntity<>(new ResponseDto<>(1, "회원가입 성공", companyjoin), HttpStatus.OK);
    }

    @PostMapping("/nc/companylogin")
    public ResponseEntity<?> companylogin(@RequestBody @Validation CompanyLoginReqDto companyLoginReqDto) {
        Optional<Company> companyOptional = companyRepository.findByCompanynameAndPassword(
                companyLoginReqDto.getCompanyName(), companyLoginReqDto.getCompanyPassword());
        if (companyOptional.isPresent()) { // 값이 있다면
            String jwt = JwtProvider.create(companyOptional.get());
            return ResponseEntity.ok().header(JwtProvider.HEADER, jwt).body("로그인 성공");
        } else {
            return ResponseEntity.badRequest().body("로그인 실패");
        }
    }

    @GetMapping("/company/{id}/companyMyPage")
    public ResponseEntity<?> companyMyPage(@PathVariable int id) {
        CompanyMypageDto dto = companyRepository.companyJoinNotice(id);
        return new ResponseEntity<>(new ResponseDto<>(1, "성공", dto), HttpStatus.OK);
    }

    @PutMapping("/company/{id}")
    public @ResponseBody ResponseEntity<?> update(@PathVariable int id,
            @RequestBody @Validation CompanyMypageReqDto companyMypageReqDto) {
        LoginCompany loginCompany = (LoginCompany) session.getAttribute("comPrincipal");
        CompanyMypageRespDto companymypage = companyService.기업정보수정(id, companyMypageReqDto, loginCompany.getId());
        return new ResponseEntity<>(new ResponseDto<>(1, "기업정보수정성공", companymypage), HttpStatus.OK);
    }

    // 프로필 업데이트
    @PostMapping("/company/companyProfileUpdate")
    public ResponseEntity<?> companyProfileUpdate(@RequestBody CompanyProfileReqDto companyProfileReqDto) {
        LoginCompany loginCompany = (LoginCompany) session.getAttribute("comPrincipal");
        if (companyProfileReqDto.getCompanyProfile().isEmpty()) {
            throw new CustomException("사진이 전송되지 않았습니다");
        }
        Company comPS = companyService.회사프로필사진수정(companyProfileReqDto, loginCompany.getId());
        session.setAttribute("comPrincipal", comPS);

        return new ResponseEntity<>(new ResponseDto<>(1, "성공", comPS),
                HttpStatus.OK);
    }

    @GetMapping("/nc/company/companyList")
    public ResponseEntity<?> companyList(CompanyListRespDto companyListRespDto) {
        List<CompanyListRespDto> companyList = companyRepository.findCompanyList();
        return new ResponseEntity<>(new ResponseDto<>(1, "성공", companyList), HttpStatus.OK);
    }
}
