package com.shield.chaosshield.common;

import com.shield.chaosshield.dao.ExperimentTestDao;
import com.shield.chaosshield.dao.impl.ExperimentTestDaoImpl;
import com.shield.chaosshield.pojo.ExperimentTest;

import java.util.List;

public class ExperimentTestTableInit {
    public static void init() {
        ExperimentTestDao dao = new ExperimentTestDaoImpl();
        dao.createTable();
        dao.createTrigger();

        ExperimentTest test = new ExperimentTest();
        test.setName("testName1");
        dao.insert(test);
        test.setName("testName2");
        dao.insert(test);
        test.setName("testName3");
        dao.insert(test);

        List<ExperimentTest> experimentTests = dao.selectAll();
        for (ExperimentTest t : experimentTests) {
            System.out.println("?=>" + t);
        }

        System.out.println("=============================================");

        System.out.println("slectt =>"+dao.selectById(1));

        dao.deleteById(1);

        experimentTests = dao.selectAll();
        for (ExperimentTest t : experimentTests) {
            System.out.println(t);
        }
    }

    public static void main(String[] args) {
        init();
    }
}
