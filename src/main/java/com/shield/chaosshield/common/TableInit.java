package com.shield.chaosshield.common;

import com.shield.chaosshield.dao.ChaosShellDao;
import com.shield.chaosshield.dao.ExperimentDetailDao;
import com.shield.chaosshield.dao.ExperimentTestDao;
import com.shield.chaosshield.dao.impl.ChaosShellDaoImpl;
import com.shield.chaosshield.dao.impl.ExperimentDetailDaoImpl;
import com.shield.chaosshield.dao.impl.ExperimentTestDaoImpl;


public class TableInit {

    static {
        initExperimentTestTable();
        initExperimentDetailTable();
    }

    public static void initExperimentTestTable() {
        ExperimentTestDao dao = new ExperimentTestDaoImpl();
        dao.createTable();
        dao.createTrigger();
    }

    public static void initExperimentDetailTable() {
        ExperimentDetailDao dao = new ExperimentDetailDaoImpl();
        dao.createTable();
        dao.createTrigger();
    }

    public static void initChaosShellTable() {
        ChaosShellDao dao = new ChaosShellDaoImpl();
        dao.createTable();
        // TODO 插入注入本机的数据，注意数据不能重复
        // TODO 之后插入java application类型故障时候单独手动操作
    }

}
