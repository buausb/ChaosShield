package com.shield.chaosshield.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ExperimentTest {
    /**
     * 实验ID
     * */
    private Integer id;
    /**
     * 实验名称
     */
    private String name;
    /**
     * 实验进程Pid
     */
    private Integer pid;
    /**
     * 挂载Java项目Pid
     */
    private Integer javaPid;
    /**
     * 执行状态
     */
    private Integer state;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 删除标志
     */
    private Integer isDeleted;

    @Override
    public String toString() {
        return "ExperimentTest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pid=" + pid +
                ", javaPid=" + javaPid +
                ", state=" + state +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
