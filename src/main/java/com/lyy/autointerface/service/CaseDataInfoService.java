package com.lyy.autointerface.service;

import com.lyy.autointerface.entity.CaseDataInfo;

import java.util.List;

public interface CaseDataInfoService {
    List<CaseDataInfo> selByInterfaceId(int interfaceId, int taskId);
}
