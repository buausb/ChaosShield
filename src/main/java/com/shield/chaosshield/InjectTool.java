package com.shield.chaosshield;

import com.shield.chaosshield.chaos.machine.Chaos;
import com.shield.chaosshield.chaos.CpuHigh;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Scanner;

public class InjectTool {

    static int pidNum;

    public static void inject(Chaos chaos) throws Exception {
        ProcessBuilder processBuilder = new ProcessBuilder("chmod", "+x", chaos.getStartPath());
        Process start = processBuilder.start();
        start.waitFor();

        ProcessBuilder builder = new ProcessBuilder(chaos.getInjectCommands(new ArrayList<>()));
        Process process = builder.start();
        Field pid = process.getClass().getDeclaredField("pid");
        pid.setAccessible(true);
        pidNum = pid.getInt(process);
        System.out.println("===================> " + pidNum);
    }

    public static void kill(Chaos chaos) throws Exception {
        ProcessBuilder processBuilder = new ProcessBuilder("chmod", "+x", chaos.getKillerPath());
        Process start = processBuilder.start();
        start.waitFor();

//        List<String> attrs = new ArrayList<>();
//        attrs.add(Integer.toString(pidNum));
        Scanner s = new Scanner(System.in);
        int num = s.nextInt();

        ProcessBuilder builder = new ProcessBuilder(chaos.getKillerPath(), Integer.toString(num));
        builder.start();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Java工作目录：" + System.getProperty("user.dir"));
        boolean flag = true;
        Scanner s = new Scanner(System.in);
        CpuHigh chaos = new CpuHigh();
        while (flag) {
            int i = s.nextInt();
            if (i == 1) {
                inject(chaos);
            } else if (i == 2) {
                kill(chaos);
            } else {
                flag = false;
            }
        }
    }

}
