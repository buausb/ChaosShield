package com.shield.chaosshield.pojo;

import com.shield.chaosshield.common.ChaosType;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChaosShell {

    /**
     * 故障ID
     */
    private Integer id;
    /**
     * 故障名称
     */
    private String name;
    /**
     * 启动脚本
     */
    private String startPath;
    /**
     * 终止脚本
     */
    private String endPath;
    /**
     * 故障类别
     */
    private Integer type;
    /**
     * 运行状态
     */
    private Integer state;
    /**
     * 本机JAVA_HOME路径
     */
    private String javaHome;
    /**
     * 执行参数
     */
    private String params;
    /**
     * 删除标志
     */
    private Integer  isDeleted;

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startPath='" + startPath + '\'' +
                ", endPath='" + endPath + '\'' +
                ", type=" + ChaosType.getName(type) +
                ", state=" + state +
                ", javaHome='" + javaHome + '\'' +
                ", params='" + params + '\'' +
                '}';
    }
}
