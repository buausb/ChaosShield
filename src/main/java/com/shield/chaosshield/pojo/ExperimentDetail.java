package com.shield.chaosshield.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class ExperimentDetail {

    /**
     * ID
     */
    private Integer  id;
    /**
     * 归属实验ID
     */
    private Integer  testId;
    /**
     * 故障脚本ID
     */
    private Integer  chaosId;
    /**
     * 故障名称
     */
    private  String chaosName;
    /**
     * 脚本进程Pid
     */
    private Integer pid;
    /**
     * 执行位次
     */
    private Integer  order;
    /**
     * 执行状态
     */
    private Integer  state;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date  createTime;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /**
     * 删除标志
     */
    private Integer  isDeleted;

}
