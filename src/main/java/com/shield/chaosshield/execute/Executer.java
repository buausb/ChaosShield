package com.shield.chaosshield.execute;

import com.shield.chaosshield.pojo.ChaosShell;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public interface Executer {

    ThreadPoolExecutor pool = new ThreadPoolExecutor(5,20,60, TimeUnit.SECONDS, new SynchronousQueue<>());

    int start(int javaPid, ChaosShell shell) throws Exception;
    void stop(int pid, int javaPid, ChaosShell shell) throws Exception;

    default int executeProcess(ProcessBuilder pb) throws Exception {
        // 合并输出
        pb.redirectErrorStream(true);
        Process process = pb.start();
        // 新线程读取IO，不阻塞主线程
        pool.submit(() -> {
            String ret;
            // 输出
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                while ((ret = reader.readLine()) != null) {
                    System.out.println(ret);
                }
            } catch (Exception e) {
                System.out.println("<** IO 异常 **>");
            }
        });
        // 进程pid
        Field pidField = process.getClass().getDeclaredField("pid");
        pidField.setAccessible(true);
        int pid = pidField.getInt(process);
        System.out.println("=> 程序执行 pid:" + pid + " <=");
        return pid;
    }
}
