package com.shield.chaosshield.execute;

import com.shield.chaosshield.execute.executer.ApplicationChaosExecuter;
import com.shield.chaosshield.execute.executer.MachineChaosExecuter;
import com.shield.chaosshield.pojo.ChaosShell;

public class ExecuterFactory {

    Executer applicationExecuter = new ApplicationChaosExecuter();
    Executer machineExecuter = new MachineChaosExecuter();

    public Executer getExecuter(ChaosShell shell) {
        switch (shell.getType()) {
            case 1 : return applicationExecuter;
            case 2 :
            case 3 :
            case 4 :
            case 5 :
                return machineExecuter;
            default: return null;
        }
    }

}
