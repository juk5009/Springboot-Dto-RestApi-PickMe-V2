package shop.mtcoding.pickme.model;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.pickme.dto.company.CompanyMypageDto;
import shop.mtcoding.pickme.dto.company.CompanyReq.CompanyJoinReqDto;
import shop.mtcoding.pickme.dto.company.CompanyReq.CompanyLoginReqDto;
import shop.mtcoding.pickme.dto.company.CompanyResp.CompanyListRespDto;

@Mapper
public interface CompanyRepository {

        public List<CompanyListRespDto> findCompanyList();

        public int insert(CompanyJoinReqDto companyJoinReqDto);

        public int updateById(@Param("id") int id, @Param("companyName") String companyName,
                        @Param("companyPassword") String companyPassword, @Param("companyEmail") String companyEmail);

        public int deleteById(int id);

        public List<Company> findAll();

        public Company findById(int id);

        public Optional<Company> findByCompanynameAndPassword(CompanyLoginReqDto CompanyLoginReqDto);

        public int updateCompanyProfile(@Param("id") int id, @Param("companyProfile") String companyProfile);

        public Company findByCompanyname(String companyName);

        public CompanyMypageDto companyJoinNotice(int id);

        public CompanyMypageDto findByNotice(int companyId);

}
