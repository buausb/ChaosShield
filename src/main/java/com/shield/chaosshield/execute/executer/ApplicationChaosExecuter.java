package com.shield.chaosshield.execute.executer;

import com.shield.chaosshield.common.ChaosType;
import com.shield.chaosshield.execute.Executer;
import com.shield.chaosshield.pojo.ChaosShell;

import java.io.File;

public class ApplicationChaosExecuter implements Executer {

    @Override
    public int start(int javaPid, ChaosShell shell) throws Exception {
        if (!ChaosType.Application.getType().equals(shell.getType())) {
            return -1;
        }
        // 挂载JVM
        ProcessBuilder pb = new ProcessBuilder("./sandbox.sh", "-p", Integer.toString(javaPid));
        pb.directory(new File("./sandbox/bin"));
        pb.environment().put("JAVA_HOME",shell.getJavaHome());
        executeProcess(pb);
        // 执行注入
        pb.command("./sandbox/bin/sandbox.sh", "-p", Integer.toString(javaPid), "-d", shell.getParams());
        return executeProcess(pb);
    }

    @Override
    public void stop(int pid, int javaPid, ChaosShell shell) throws Exception {
        if (!ChaosType.Application.getType().equals(shell.getType())) {
            return;
        }
        // 卸载Sandbox
        ProcessBuilder pb = new ProcessBuilder("./sandbox/bin/sandbox.sh", "-p", Integer.toString(javaPid), "-S");
        pb.environment().put("JAVA_HOME",shell.getJavaHome());
        executeProcess(pb);
    }
}
