package com.shield.chaosshield.execute;

import com.shield.chaosshield.pojo.ChaosShell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;

public interface Executer {
    int start(int javaPid, ChaosShell shell) throws Exception;
    void stop(int pid, int javaPid, ChaosShell shell) throws Exception;

    default int executeProcess(ProcessBuilder pb) throws Exception {
        // 合并输出
        pb.redirectErrorStream(true);
        Process process = pb.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String ret;
        // 输出
        while ((ret = reader.readLine()) != null) {
            System.out.println(ret);
        }
        // 进程pid
        Field pidField = process.getClass().getDeclaredField("pid");
        pidField.setAccessible(true);
        int pid = pidField.getInt(process);
        // 执行结果
        int exit = process.waitFor();
        if (exit != 0) {
            return -1;
        }
        return pid;
    }
}
