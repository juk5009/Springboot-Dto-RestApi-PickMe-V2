package shop.mtcoding.pickme.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import shop.mtcoding.pickme.dto.apply.ApplyReq.ApplyResumeSelectReqDto;
import shop.mtcoding.pickme.dto.apply.ApplyResp.ApplyListRespDto;

@Mapper
public interface ApplyRepository {
        public int insert(ApplyResumeSelectReqDto applyResumeSelectReqDto);

        public int updateById(int id);

        public int deleteById(int id);

        public List<Apply> findAll();

        public Apply findById(int id);

        public List<ApplyListRespDto> findApplyList();

}
