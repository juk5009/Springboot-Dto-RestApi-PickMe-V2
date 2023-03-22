package shop.mtcoding.pickme.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.pickme.dto.userskill.UserskillReqDto.UserskillSaveReqDto;

@Mapper
public interface UserskillRepository {
        public List<Userskill> findAll();

        public User findById(int id);

        public List<Userskill> findByUserId(int id);

        public List<Userskill> findByResumeId(int id);

        public List<Notice> findByCompanyskillName(@Param("userId") int userId, @Param("resumeId") int resumeId);

        public int insert(@Param("resumeId") int resumeId, @Param("userId") int userId,
                        @Param("userskillName") String userskillName);

        public int updateById(UserskillSaveReqDto userskillSaveReqDto);

        public int deleteById(int id);

        public int deleteByResumeId(int resumeId);
}
