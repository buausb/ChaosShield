package com.shield.chaosshield.execute.executer;

import com.shield.chaosshield.common.ChaosType;
import com.shield.chaosshield.execute.Executer;
import com.shield.chaosshield.pojo.ChaosShell;

import java.lang.reflect.Field;

public class MachineChaosExecuter  implements Executer {
    @Override
    public int start(int pid, int javaPid, ChaosShell shell) throws Exception{
        if (ChaosType.Application.getType().equals(shell.getType())) {
            return -1;
        }
        ProcessBuilder pb = new ProcessBuilder("sh",shell.getStartPath(), Integer.toString(pid));
        return executeProcess(pb);

    }

    @Override
    public void stop(int pid, int javaPid, ChaosShell shell)  throws Exception{
        if (ChaosType.Application.getType().equals(shell.getType())) {
            return;
        }
        ProcessBuilder pb = new ProcessBuilder("sh",shell.getEndPath(), Integer.toString(pid));
        executeProcess(pb);
    }
}
