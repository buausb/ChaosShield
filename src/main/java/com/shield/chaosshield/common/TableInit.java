package com.shield.chaosshield.common;

import com.shield.chaosshield.dao.ChaosShellDao;
import com.shield.chaosshield.dao.ExperimentDetailDao;
import com.shield.chaosshield.dao.ExperimentTestDao;
import com.shield.chaosshield.dao.impl.ChaosShellDaoImpl;
import com.shield.chaosshield.dao.impl.ExperimentDetailDaoImpl;
import com.shield.chaosshield.dao.impl.ExperimentTestDaoImpl;
import com.shield.chaosshield.pojo.ChaosShell;


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
//        ChaosShellDao dao = new ChaosShellDaoImpl();
//        dao.createTable();
//        insertIfNotExists(
//                ChaosShell.builder()
//                        .name("内存使用率高")
//                        .startPath("")
//                        .endPath("")
//                        .type()
//                        .javaHome("")
//                        .params("")
//                        .state()
//                        .isDeleted(0).build()
//        );
//        insertIfNotExists(
//                ChaosShell.builder()
//                        .name("CPU利用率高")
//                        .startPath("")
//                        .endPath("")
//                        .type()
//                        .javaHome("")
//                        .params("")
//                        .state()
//                        .isDeleted(0).build()
//        );
//        insertIfNotExists(
//                ChaosShell.builder()
//                        .name("磁盘满")
//                        .startPath("")
//                        .endPath("")
//                        .type()
//                        .javaHome("")
//                        .params("")
//                        .state()
//                        .isDeleted(0).build()
//        );
//        insertIfNotExists(
//                ChaosShell.builder()
//                        .name("磁盘IO高")
//                        .startPath("")
//                        .endPath("")
//                        .type()
//                        .javaHome("")
//                        .params("")
//                        .state()
//                        .isDeleted(0).build()
//        );
//        insertIfNotExists(
//                ChaosShell.builder()
//                        .name("网络延迟")
//                        .startPath("")
//                        .endPath("")
//                        .type()
//                        .javaHome("")
//                        .params("")
//                        .state()
//                        .isDeleted(0).build()
//        );
//        insertIfNotExists(
//                ChaosShell.builder()
//                        .name("网络丢包")
//                        .startPath("")
//                        .endPath("")
//                        .type()
//                        .javaHome("")
//                        .params("")
//                        .state()
//                        .isDeleted(0).build()
//        );
//        insertIfNotExists(
//                ChaosShell.builder()
//                        .name("包重复")
//                        .startPath("")
//                        .endPath("")
//                        .type()
//                        .javaHome("")
//                        .params("")
//                        .state()
//                        .isDeleted(0).build()
//        );
//        insertIfNotExists(
//                ChaosShell.builder()
//                        .name("包乱序")
//                        .startPath("")
//                        .endPath("")
//                        .type()
//                        .javaHome("")
//                        .params("")
//                        .state()
//                        .isDeleted(0).build()
//        );
//        insertIfNotExists(
//                ChaosShell.builder()
//                        .name("")
//                        .startPath("")
//                        .endPath("")
//                        .type()
//                        .javaHome("")
//                        .params("")
//                        .state()
//                        .isDeleted(0).build()
//        );
//        insertIfNotExists(
//                ChaosShell.builder()
//                        .name("")
//                        .startPath("")
//                        .endPath("")
//                        .type()
//                        .javaHome("")
//                        .params("")
//                        .state()
//                        .isDeleted(0).build()
//        );
//        insertIfNotExists(
//                ChaosShell.builder()
//                        .name("")
//                        .startPath("")
//                        .endPath("")
//                        .type()
//                        .javaHome("")
//                        .params("")
//                        .state()
//                        .isDeleted(0).build()
//        );
        // TODO 插入注入本机的数据，注意数据不能重复
        // TODO 之后插入java application类型故障时候单独手动操作
    }


    public static Integer insertIfNotExists(ChaosShellDao dao, ChaosShell shell) {
        ChaosShell chaosShell = dao.selectByName(shell.getName());
        if (chaosShell == null) {
            return dao.insert(shell);
        }
        return 0;
    }

    public static void main(String[] args) {
//        ProcessBuilder pb = new ProcessBuilder("sh","")
    }

}
