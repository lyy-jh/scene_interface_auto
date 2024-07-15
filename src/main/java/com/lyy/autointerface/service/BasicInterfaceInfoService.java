package com.lyy.autointerface.service;

import com.lyy.autointerface.entity.BasicInterfaceInfo;

import java.util.List;

public interface BasicInterfaceInfoService {
    List<BasicInterfaceInfo> selByTaskId(int TaskId);
}
