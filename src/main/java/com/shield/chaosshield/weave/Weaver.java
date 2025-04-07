package com.shield.chaosshield.weave;

import com.shield.chaosshield.common.State;
import com.shield.chaosshield.dao.ChaosShellDao;
import com.shield.chaosshield.dao.ExperimentDetailDao;
import com.shield.chaosshield.dao.impl.ChaosShellDaoImpl;
import com.shield.chaosshield.dao.impl.ExperimentDetailDaoImpl;
import com.shield.chaosshield.pojo.ChaosShell;
import com.shield.chaosshield.pojo.ExperimentDetail;

import java.util.List;

public class Weaver {

    private static ChaosShellDao chaosShellDao = new ChaosShellDaoImpl();
    private static ExperimentDetailDao experimentDetailDao = new ExperimentDetailDaoImpl();

    public static void weave(int testId, List<Integer> shellIds) {
        ChaosShell shell;
        int num = 0;
        for (int id : shellIds) {
             shell = chaosShellDao.selectById(id);
             if (shell == null) {
                 continue;
             }
             experimentDetailDao.insert(
                     ExperimentDetail.builder()
                             .testId(testId)
                             .chaosId(id)
                             .chaosName(shell.getName())
                             .order(num++)
                             .state(State.NOT_RUNNING.getState())
                             .isDeleted(0).build()
             );
        }
    }

}
