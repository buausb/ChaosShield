package com.shield.chaosshield.execute.executer;

import com.shield.chaosshield.common.ChaosType;
import com.shield.chaosshield.execute.Executer;
import com.shield.chaosshield.pojo.ChaosShell;

public class ApplicationChaosExecuter implements Executer {

    @Override
    public int start(int pid, int javaPid, ChaosShell shell) throws Exception {
        if (!ChaosType.Application.getType().equals(shell.getType())) {
            return -1;
        }
        // 挂载JVM
        ProcessBuilder pb = new ProcessBuilder("./sandbox/bin/sandbox.sh", "-p", Integer.toString(javaPid));
        executeProcess(pb);
        // 执行注入
        pb.command("./sandbox/bin/sandbox.sh", "-p", Integer.toString(pid), "-d", shell.getParams());
        return executeProcess(pb);
    }

    @Override
    public void stop(int pid, int javaPid, ChaosShell shell) throws Exception {
        if (!ChaosType.Application.getType().equals(shell.getType())) {
            return;
        }
        // 卸载Sandbox
        ProcessBuilder pb = new ProcessBuilder("./sandbox/bin/sandbox.sh", "-p", Integer.toString(javaPid), "-S");
        executeProcess(pb);
    }
}
