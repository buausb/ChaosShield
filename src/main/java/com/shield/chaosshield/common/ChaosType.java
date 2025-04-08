package com.shield.chaosshield.common;

import lombok.Getter;

@Getter
public enum ChaosType {

    Application(1,"JAVA应用"),
    Cache(2,"缓存类"),
    Cpu(3,"Cpu类"),
    Disk(4,"磁盘类"),
    Network(5,"网络类");


    Integer type;
    String name;

    ChaosType(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    public static String getName(int type) {
        for (ChaosType t : ChaosType.values()) {
            if (type == t.type) {
                return t.name;
            }
        }
        return "未找到";
    }
}
