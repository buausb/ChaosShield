package com.shield.chaosshield.common;

import com.shield.chaosshield.dao.ExperimentDetailDao;
import com.shield.chaosshield.dao.ExperimentTestDao;
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


}
