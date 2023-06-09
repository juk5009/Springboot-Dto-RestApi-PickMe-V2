package shop.mtcoding.pickme.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.pickme.dto.company.CompanyJoinRespDto;
import shop.mtcoding.pickme.dto.company.CompanyMypageRespDto;
import shop.mtcoding.pickme.dto.company.CompanyProfileReqDto;
import shop.mtcoding.pickme.dto.company.CompanyReq.CompanyJoinReqDto;
import shop.mtcoding.pickme.dto.company.CompanyReq.CompanyLoginReqDto;
import shop.mtcoding.pickme.dto.company.CompanyReq.CompanyMypageReqDto;
import shop.mtcoding.pickme.handler.ex.CustomApiException;
import shop.mtcoding.pickme.handler.ex.CustomException;
import shop.mtcoding.pickme.model.Company;
import shop.mtcoding.pickme.model.CompanyRepository;
import shop.mtcoding.pickme.util.PathUtil;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Transactional
    public CompanyJoinRespDto 기업회원가입(CompanyJoinReqDto companyJoinReqDto) {
        Company CompanyUser = companyRepository.findByCompanyname(companyJoinReqDto.getCompanyName());
        if (CompanyUser != null) {
            throw new CustomException("동일한 companyname이 존재합니다");
        }
        int result = companyRepository.insert(companyJoinReqDto);
        if (result != 1) {
            throw new CustomException("회원가입실패");
        }
        Company company = companyRepository.findByCompanyname(companyJoinReqDto.getCompanyName());
        CompanyJoinRespDto companyjoin = new CompanyJoinRespDto();
        companyjoin.setCompanyName(company.getCompanyName());
        companyjoin.setCompanyPassword(company.getCompanyPassword());
        companyjoin.setCompanyEmail(company.getCompanyEmail());
        companyjoin.setRole(company.getRole());
        return companyjoin;
    }

    @Transactional
    public CompanyMypageRespDto 기업정보수정(int id, CompanyMypageReqDto companyMypageReqDto, Integer comprincipalId) {
        Company companyPS = companyRepository.findById(id);
        if (companyPS == null) {
            throw new CustomApiException("해당 기업정보를 찾을 수 없습니다");
        }
        if (companyPS.getId() != comprincipalId) {
            throw new CustomApiException("기업정보를 수정할 권한이 없습니다", HttpStatus.FORBIDDEN);
        }
        int result = companyRepository.updateById(id, companyMypageReqDto.getCompanyName(),
                companyMypageReqDto.getCompanyPassword(), companyMypageReqDto.getCompanyEmail());
        if (result != 1) {
            throw new CustomApiException("기업정보 수정에 실패하였습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        companyPS = companyRepository.findById(id);
        return new CompanyMypageRespDto(companyPS.getCompanyName(), companyPS.getCompanyPassword(),
                companyPS.getCompanyEmail());
    }

    @Transactional
    public Company 회사프로필사진수정(CompanyProfileReqDto companyProfileReqDto, Integer comPrincipalId) {
        Company comPS = companyRepository.findById(comPrincipalId);

        comPS.setCompanyProfile(companyProfileReqDto.getCompanyProfile());
        comPS.setId(comPrincipalId);

        companyRepository.updateCompanyProfile(comPS.getId(), comPS.getCompanyProfile());

        return comPS;
    }
}
