package com.lyy.autointerface.service.impl;

import com.lyy.autointerface.entity.BasicInterfaceInfo;
import com.lyy.autointerface.mapper.BasicInterfaceInfoMapper;
import com.lyy.autointerface.service.BasicInterfaceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BasicInterfaceInfoServiceImpl implements BasicInterfaceInfoService {
    @Autowired
    BasicInterfaceInfoMapper basicInterfaceInfoMapper;

    @Override
    public List<BasicInterfaceInfo> selByTaskId(int TaskId) {
        return basicInterfaceInfoMapper.selByTaskId(TaskId);
    }
}
