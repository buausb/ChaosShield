package com.shield.chaosshield.schedule;

import com.shield.chaosshield.common.ChaosType;
import com.shield.chaosshield.common.State;
import com.shield.chaosshield.dao.ChaosShellDao;
import com.shield.chaosshield.dao.ExperimentDetailDao;
import com.shield.chaosshield.dao.impl.ChaosShellDaoImpl;
import com.shield.chaosshield.dao.impl.ExperimentDetailDaoImpl;
import com.shield.chaosshield.execute.Executer;
import com.shield.chaosshield.execute.ExecuterFactory;
import com.shield.chaosshield.pojo.ChaosShell;
import com.shield.chaosshield.pojo.ExperimentDetail;

import java.util.Collections;
import java.util.List;

public class Scheduler {

    private static ExperimentDetailDao experimentDetailDao = new ExperimentDetailDaoImpl();
    private static ChaosShellDao chaosShellDao = new ChaosShellDaoImpl();

    private static String startLock = "start";
    private static String shutdownLock = "shutdown";

    public static void startTest(int testId, int javaPid) {
        synchronized (startLock) {
            List<ExperimentDetail> experimentDetails = experimentDetailDao.selectByTestId(testId);
            ChaosShell shell;
            for (ExperimentDetail detail : experimentDetails) {
                shell = chaosShellDao.selectById(detail.getChaosId());
                // 没找到脚本或脚本正在执行
                if (shell == null || State.RUNNING.getState() == shell.getState()) {
                    continue;
                }
                // 网络型脚本同一时间只能执行一个
                if (ChaosType.Network.getType().equals(shell.getType())) {
                    List<ChaosShell> chaosShells = chaosShellDao.selectRunningByType(ChaosType.Network.getType());
                    if (chaosShells != null && chaosShells.size() > 0) {
                        continue;
                    }
                }
                // 获取执行器
                Executer executer = ExecuterFactory.getExecuter(shell);
                if (executer == null) {
                    continue;
                }
                try {
                    // 执行脚本并更改状态
                    int pid = executer.start(javaPid, shell);
                    detail.setPid(pid);
                    detail.setState(State.RUNNING.getState());
                    experimentDetailDao.update(detail);
                    shell.setState(State.RUNNING.getState());
                    chaosShellDao.update(shell);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void shutdownTest(int testId, int javaPid) {

        synchronized (shutdownLock) {
            List<ExperimentDetail> experimentDetails = experimentDetailDao.selectByTestId(testId);
            ChaosShell shell;
            for (ExperimentDetail detail : experimentDetails) {
                shell = chaosShellDao.selectById(detail.getChaosId());
                // 没找到脚本或脚本没在执行
                if (shell == null || State.NOT_RUNNING.getState() == shell.getState()) {
                    continue;
                }
                // 获取执行器
                Executer executer = ExecuterFactory.getExecuter(shell);
                if (executer == null) {
                    continue;
                }
                try {
                    // 执行并更改状态
                    executer.stop(detail.getPid(), javaPid, shell);
                    detail.setPid(0);
                    detail.setState(State.NOT_RUNNING.getState());
                    experimentDetailDao.update(detail);
                    shell.setState(State.NOT_RUNNING.getState());
                    chaosShellDao.update(shell);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

}
