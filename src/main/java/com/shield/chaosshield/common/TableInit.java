package com.shield.chaosshield.common;

import com.shield.chaosshield.dao.ChaosShellDao;
import com.shield.chaosshield.dao.ExperimentDetailDao;
import com.shield.chaosshield.dao.ExperimentTestDao;
import com.shield.chaosshield.dao.impl.ChaosShellDaoImpl;
import com.shield.chaosshield.dao.impl.ExperimentDetailDaoImpl;
import com.shield.chaosshield.dao.impl.ExperimentTestDaoImpl;
import com.shield.chaosshield.pojo.ChaosShell;


public class TableInit {

    public static void main(String[] args) {
        System.out.println(ChaosShell.builder()
                .name("包乱序")
                .startPath("./src/main/java/com/shield/chaosshield/shell/network/packageShuffle/package_shuffle.sh")
                .endPath("./src/main/java/com/shield/chaosshield/shell/network/packageShuffle/kill_package_shuffle.sh")
                .type(5)
                .javaHome("")
                .params("")
                .state(0)
                .isDeleted(0).build());
    }

    public static void init() {
        initExperimentTestTable();
        initExperimentDetailTable();
        initChaosShellTable();
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
        insertIfNotExists(dao,
                ChaosShell.builder()
                        .name("内存使用率高")
                        .startPath("./src/main/java/com/shield/chaosshield/shell/cachHigh/cache_high.sh")
                        .endPath("./src/main/java/com/shield/chaosshield/shell/cachHigh/kill_cache_high.sh")
                        .type(2)
                        .javaHome("")
                        .params("")
                        .state(0)
                        .isDeleted(0).build()
        );
        insertIfNotExists(dao,
                ChaosShell.builder()
                        .name("CPU利用率高")
                        .startPath("./src/main/java/com/shield/chaosshield/shell/cpuHigh/cpu_high.sh")
                        .endPath("./src/main/java/com/shield/chaosshield/shell/cpuHigh/kill_cpu_high.sh")
                        .type(3)
                        .javaHome("")
                        .params("")
                        .state(0)
                        .isDeleted(0).build()
        );
        insertIfNotExists(dao,
                ChaosShell.builder()
                        .name("磁盘满")
                        .startPath("./src/main/java/com/shield/chaosshield/shell/diskFull/disk_full.sh")
                        .endPath("./src/main/java/com/shield/chaosshield/shell/diskFull/kill_disk_full.sh")
                        .type(4)
                        .javaHome("")
                        .params("")
                        .state(0)
                        .isDeleted(0).build()
        );
        insertIfNotExists(dao,
                ChaosShell.builder()
                        .name("磁盘IO高")
                        .startPath("./src/main/java/com/shield/chaosshield/shell/diskIOHigh/disk_io_high.sh")
                        .endPath("./src/main/java/com/shield/chaosshield/shell/diskIOHigh/kill_disk_io_high.sh")
                        .type(4)
                        .javaHome("")
                        .params("")
                        .state(0)
                        .isDeleted(0).build()
        );
        insertIfNotExists(dao,
                ChaosShell.builder()
                        .name("网络延迟")
                        .startPath("./src/main/java/com/shield/chaosshield/shell/network/packageDelay/package_delay.sh")
                        .endPath("./src/main/java/com/shield/chaosshield/shell/network/packageDelay/kill_package_delay.sh")
                        .type(5)
                        .javaHome("")
                        .params("")
                        .state(0)
                        .isDeleted(0).build()
        );
        insertIfNotExists(dao,
                ChaosShell.builder()
                        .name("网络丢包")
                        .startPath("./src/main/java/com/shield/chaosshield/shell/network/packageLost/package_lost.sh")
                        .endPath("./src/main/java/com/shield/chaosshield/shell/network/packageLost/kill_package_lost.sh")
                        .type(5)
                        .javaHome("")
                        .params("")
                        .state(0)
                        .isDeleted(0).build()
        );
        insertIfNotExists(dao,
                ChaosShell.builder()
                        .name("包重复")
                        .startPath("./src/main/java/com/shield/chaosshield/shell/network/packageRepeat/kill_package_repeat.sh")
                        .endPath("./src/main/java/com/shield/chaosshield/shell/network/packageRepeat/package_repeat.sh")
                        .type(5)
                        .javaHome("")
                        .params("")
                        .state(0)
                        .isDeleted(0).build()
        );
        insertIfNotExists(dao,
                ChaosShell.builder()
                        .name("包乱序")
                        .startPath("./src/main/java/com/shield/chaosshield/shell/network/packageShuffle/package_shuffle.sh")
                        .endPath("./src/main/java/com/shield/chaosshield/shell/network/packageShuffle/kill_package_shuffle.sh")
                        .type(5)
                        .javaHome("")
                        .params("")
                        .state(0)
                        .isDeleted(0).build()
        );
        insertIfNotExists(dao,
                ChaosShell.builder()
                        .name("项目APP->改变方法返回值->String类")
                        .startPath("")
                        .endPath("")
                        .type(1)
                        .javaHome("/usr/lib/jvm/java-8-openjdk-amd64")
                        .params("change-method-ret-val-moudle/change?class=com.shield.App&method=getStr&ret_class=java.lang.String&ret_val=2")
                        .state(0)
                        .isDeleted(0).build()
        );
        insertIfNotExists(dao,
                ChaosShell.builder()
                        .name("项目APP->延迟方法执行->5秒")
                        .startPath("")
                        .endPath("")
                        .type(1)
                        .javaHome("/usr/lib/jvm/java-8-openjdk-amd64")
                        .params("method-delay-module/delay?class=com.shield.App&method=getStr&delayTime=5000")
                        .state(0)
                        .isDeleted(0).build()
        );
    }


    private static Integer insertIfNotExists(ChaosShellDao dao, ChaosShell shell) {
        ChaosShell chaosShell = dao.selectByName(shell.getName());
        if (chaosShell == null) {
            return dao.insert(shell);
        }
        return 0;
    }

}
