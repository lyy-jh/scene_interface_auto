package com.lyy.autointerface.mapper;

import com.lyy.autointerface.entity.BasicInterfaceInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BasicInterfaceInfoMapper {
    @Select("select * from basic_interface_info where task_id=#{taskId} order by interface_order_id asc")
    List<BasicInterfaceInfo> selByTaskId(int taskId);
}
