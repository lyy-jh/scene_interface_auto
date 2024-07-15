package com.lyy.autointerface.mapper;

import com.lyy.autointerface.entity.CaseDataInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CaseDataInfoMapper {
    @Select("select * from case_data_info where interface_id=#{interfaceId} and task_id=#{taskId}")
    List<CaseDataInfo> selByInterfaceId(int interfaceId, int taskId);
}
