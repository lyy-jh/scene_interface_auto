package com.lyy.autointerface.service.impl;

import com.lyy.autointerface.entity.CaseDataInfo;
import com.lyy.autointerface.mapper.CaseDataInfoMapper;
import com.lyy.autointerface.service.CaseDataInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaseDataInfoServiceImpl implements CaseDataInfoService {
    @Autowired
    CaseDataInfoMapper caseDataInfoMapper;
    @Override
    public List<CaseDataInfo> selByInterfaceId(int interfaceId, int taskId) {
        return caseDataInfoMapper.selByInterfaceId(interfaceId,taskId);
    }
}
