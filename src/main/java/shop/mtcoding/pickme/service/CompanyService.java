package shop.mtcoding.pickme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
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
    public void 기업회원가입(CompanyJoinReqDto companyJoinReqDto) {
        Company CompanyUser = companyRepository.findByCompanyname(companyJoinReqDto.getCompanyName());
        if (CompanyUser != null) {
            throw new CustomException("동일한 companyname이 존재합니다");
        }
        int result = companyRepository.insert(companyJoinReqDto);
        if (result != 1) {
            throw new CustomException("회원가입실패");
        }
    }

    @Transactional
    public Company 기업로그인(CompanyLoginReqDto companyLoginReqDto) {
        Company comPrincipal = companyRepository.findByCompanynameAndPassword(companyLoginReqDto);
        if (comPrincipal == null) {
            throw new CustomException("아이디 혹은 패스워드가 잘못 입력되었습니다.");
        }
        return comPrincipal;
    }

    @Transactional
    public void 기업정보수정(int id, CompanyMypageReqDto companyMypageReqDto, Integer comprincipalId) {
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

    }

    @Transactional
    public Company 유저프로필사진수정(MultipartFile companyProfile, Integer comPrincipalId) {
        String uuidImageName = PathUtil.writeImageFile(companyProfile);

        Company comPS = companyRepository.findById(comPrincipalId);
        comPS.setCompanyProfile(uuidImageName);
        companyRepository.updateCompanyProfile(comPS.getId(), comPS.getCompanyName(), comPS.getCompanyPassword(),
                comPS.getCompanyEmail(),
                comPS.getCompanyProfile());
        return comPS;
    }
}
