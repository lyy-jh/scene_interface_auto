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
public class Task {
    private int taskId;
    private String taskName;
    private String taskEnvironment;
    private int status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private List<BasicInterfaceInfo> basicInterfaceInfoList;
}
