package com.shield.chaosshield.chaos.machine;

import lombok.Getter;

import java.util.List;

@Getter
public class Chaos {
//    CPU High
//    private final String startPath = "src/main/java/com/shield/chaosshield/shell/CpuHigh/cpu_high.sh";
//    private final String killerPath = "src/main/java/com/shield/chaosshield/shell/CpuHigh/kill_cpu_high.sh";

//    disk io high
//    private final String startPath = "src/main/java/com/shield/chaosshield/shell/DiskIOHigh/disk_io_high.sh";
//    private final String killerPath = "src/main/java/com/shield/chaosshield/shell/DiskIOHigh/kill_disk_io_high.sh";

//    disk full
    private final String startPath = "src/main/java/com/shield/chaosshield/shell/DiskFull/disk_full.sh";
    private final String killerPath = "src/main/java/com/shield/chaosshield/shell/DiskFull/kill_disk_full.sh";


    public List<String> getInjectCommands(List<String> injectAttrs) {
        injectAttrs.add(0, startPath);
        return injectAttrs;
    }

    public List<String> getKillerCommands(List<String> killerAttrs) {
        killerAttrs.add(0, killerPath);
        return killerAttrs;
    }
}
