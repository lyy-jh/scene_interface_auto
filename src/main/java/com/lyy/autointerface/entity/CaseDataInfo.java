package com.lyy.autointerface.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CaseDataInfo {
    private int caseId;
    private String caseRequestParam;
    private String caseRequestHeader;
    private String caseAssert;
    private int interfaceId;
    private int taskId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
