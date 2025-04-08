package com.shield.chaosshield.weave;

import com.shield.chaosshield.common.State;
import com.shield.chaosshield.dao.ChaosShellDao;
import com.shield.chaosshield.dao.ExperimentDetailDao;
import com.shield.chaosshield.dao.ExperimentTestDao;
import com.shield.chaosshield.dao.impl.ChaosShellDaoImpl;
import com.shield.chaosshield.dao.impl.ExperimentDetailDaoImpl;
import com.shield.chaosshield.dao.impl.ExperimentTestDaoImpl;
import com.shield.chaosshield.pojo.ChaosShell;
import com.shield.chaosshield.pojo.ExperimentDetail;
import com.shield.chaosshield.pojo.ExperimentTest;

import java.util.List;

public class Weaver {

    private static ChaosShellDao chaosShellDao = new ChaosShellDaoImpl();
    private static ExperimentDetailDao experimentDetailDao = new ExperimentDetailDaoImpl();
    private static ExperimentTestDao experimentTestDao = new ExperimentTestDaoImpl();

    public static void weave(int testId, List<Integer> shellIds) {
        ExperimentTest test = experimentTestDao.selectById(testId);
        if (test == null) {
            System.out.println("==> 编排失败：Test ID Not Found <==");
            return;
        }
        List<ExperimentDetail> experimentDetails = experimentDetailDao.selectByTestId(testId);
        if (experimentDetails != null && experimentDetails.size() > 0) {
            System.out.println("==> 编排失败：单个实验只能编排一次 <==");
            return;
        }
        ChaosShell shell;
        int num = 1;
        for (int id : shellIds) {
             shell = chaosShellDao.selectById(id);
             if (shell == null) {
                 System.out.println("=>ID不存在:" + id + "<=");
                 continue;
             }
             experimentDetailDao.insert(
                     ExperimentDetail.builder()
                             .testId(testId)
                             .chaosId(id)
                             .chaosName(shell.getName())
                             .orderNum(num++)
                             .state(State.NOT_RUNNING.getState())
                             .isDeleted(0).build()
             );
        }
    }

}
