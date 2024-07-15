package com.lyy.autointerface.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasicInterfaceInfo {
    private int interfaceId;
    private String interfaceName;
    private String interfaceUrl;
    private String interfaceRequestType;
    private int interfaceOrderId;
    private int taskId;
    private int status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    List<CaseDataInfo> caseDataInfoList;
}
