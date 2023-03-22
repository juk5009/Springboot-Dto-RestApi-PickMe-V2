package shop.mtcoding.pickme.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.pickme.dto.companyskill.CompanyskillReqDto.CompanyskillSaveReqDto;

@Mapper
public interface CompanyskillRepository {
        public List<Companyskill> findAll();

        public Companyskill findById(int id);

        public List<Companyskill> findByNoticeId(int id);

        public int insert(@Param("noticeId") int noticeId, @Param("companyId") int companyId,
                        @Param("companyskillName") String companyskillName);

        public int updateById(CompanyskillSaveReqDto companyskillSaveReqDto);

        public int deleteById(int id);

        public int deleteByNoticeId(int noticeId);

}
