package com.shield.chaosshield.chaos;

import com.shield.chaosshield.chaos.machine.Chaos;

import java.util.ArrayList;
import java.util.List;

public class CpuHigh extends Chaos {
    private final String shellPath = "/home/buausb/Desktop/test.sh";
    private final String killerPath = "/home/buausb/Desktop/kill.sh";

    private static final List<String> injectAttrs = new ArrayList<>();
    private static final List<String> killerAttrs = new ArrayList<>();
}
