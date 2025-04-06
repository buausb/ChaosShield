package com.shield.chaosshield.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ExperimentDetail {

    /**
     * ID
     */
    private Integer  id;
    /**
     * 归属实验ID
     */
    private Integer  test_id;
    /**
     * 故障脚本ID
     */
    private Integer  chaos_id;
    /**
     * 故障名称
     */
    private  String chaos_name;
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
    private Date  create_time;
    /**
     * 更新时间
     */
    private Date update_time;
    /**
     * 删除标志
     */
    private Integer  isDeleted;

}
