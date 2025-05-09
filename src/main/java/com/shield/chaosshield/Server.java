package com.shield.chaosshield;

import com.shield.chaosshield.common.ChaosType;
import com.shield.chaosshield.common.State;
import com.shield.chaosshield.common.TableInit;
import com.shield.chaosshield.dao.ChaosShellDao;
import com.shield.chaosshield.dao.ExperimentDetailDao;
import com.shield.chaosshield.dao.ExperimentTestDao;
import com.shield.chaosshield.dao.impl.ChaosShellDaoImpl;
import com.shield.chaosshield.dao.impl.ExperimentDetailDaoImpl;
import com.shield.chaosshield.dao.impl.ExperimentTestDaoImpl;
import com.shield.chaosshield.pojo.ChaosShell;
import com.shield.chaosshield.pojo.ExperimentDetail;
import com.shield.chaosshield.pojo.ExperimentTest;
import com.shield.chaosshield.schedule.Scheduler;
import com.shield.chaosshield.weave.Weaver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Server {

    ExperimentTestDao experimentTestDao = new ExperimentTestDaoImpl();
    ExperimentDetailDao experimentDetailDao = new ExperimentDetailDaoImpl();
    ChaosShellDao chaosShellDao = new ChaosShellDaoImpl();


    public static void main(String[] args) {
        TableInit.init();
        Server server = new Server();
        server.start();
    }

    private void start() {
        Scanner s = new Scanner(System.in);
        String cmd;
        while (true) {
            System.out.print("ChaosShield>");
            cmd = s.nextLine();
            /**
             *  shield -h 展示所有命令
             *  shield -at 展示所有实验 all test
             *  shield -d [testID] detail 展示实验编排细节
             *  shield -ac 展示所有故障 all chaos
             *  shield -act 展示故障类别 all chaos type
             *
             *  shield -nt [testName] [目标JAVA项目的ｐｉｄ（如有）]新建实验 new test
             *  shield -nd [testID] [chaosID,chaosID...] 编排实验内容 new details
             *  shield -ns [name] [type] [startPath] [endPath] [JAVA_HOME] [params]　新增chaos shell new shell
             *
             *  shield -e [testID] 执行实验 execute
             *  shield -s [testID] 终止实验 execute
             *
             *  shield -dt [testID] 删除实验，连带实验编排 delete test
             *  shield -dc [chaosID] 删除故障 delete chaos
             *
             *  shield -check_java 显示当前机器运行的Java项目pid
             *  shield -check_web 查看本机网卡规则
             * */
            if (cmd.isEmpty()) {continue;}
            String[] subCmd = cmd.split(" ");
            if (subCmd.length < 2 || !"shield".equals(subCmd[0])) {
                System.out.println("=>USE shield -h FOR HELP<=");
                continue;
            }
            String opt = subCmd[1];
            switch (opt) {
                case "-h" :showHelp(subCmd);break;
                case "-at" :showAllTest(subCmd);break;
                case "-d" :showDetail(subCmd);break;
                case "-ac" :showAllChaos(subCmd);break;
                case "-act" :showAllChaosType(subCmd);break;
                case "-nt" :showNewTest(subCmd);break;
                case "-nd" :showNewDetail(subCmd);break;
                case "-ns" :showNewShell(subCmd);break;
                case "-e" :executeTest(subCmd);break;
                case "-s" :stopTest(subCmd);break;
                case "-dt" :showDeleteTest(subCmd);break;
                case "-dc" :showDeleteChaos(subCmd);break;
                case "-check_java":checkJava(subCmd);break;
                case "-check_web":checkWeb(subCmd);break;
                default:
                    System.out.println("=>USE shield -h FOR HELP<=");break;
            }
        }
    }

    // shield -h 展示所有命令
    private void showHelp(String[] subCmd) {
        /**
         +——————————————————————————————————————————————————
         |  shield -h 展示所有命令
         |  shield -at 展示所有实验 all test
         |  shield -d [testID] detail 展示实验编排细节
         |  shield -ac 展示所有故障 all chaos
         |  shield -act 展示故障类别 all chaos type
         |  shield -nt [testName] [目标JAVA项目的ｐｉｄ（没有写0）]新建实验 new test
         |  shield -nd [testID] [chaosID,chaosID...] 编排实验内容 new details
         |  shield -ns [name] [type] [startPath] [endPath] [JAVA_HOME] [params]　新增chaos shell new shell
         |  shield -e [testID] 执行实验 execute
         |  shield -s [testID] 终止实验 shut down
         |  shield -dt [testID] 删除实验，连带实验编排 delete test
         |  shield -dc [chaosID] 删除故障 delete chaos
         |  shield -check_java 显示当前机器运行的Java项目pid
         |  shield -check_web 查看本机网卡规则
         +———————————————————————————————————————————————————
         */
        System.out.println("" +
                "+——————————————————————————————————————————————————\n" +
                "         |  shield -h 展示所有命令\n" +
                "         |  shield -at 展示所有实验 all test\n" +
                "         |  shield -d [testID] detail 展示实验编排细节\n" +
                "         |  shield -ac 展示所有故障 all chaos\n" +
                "         |  shield -act 展示故障类别 all chaos type\n" +
                "         |  shield -nt [testName] [目标JAVA项目的ｐｉｄ（没有写0）]新建实验 new test\n" +
                "         |  shield -nd [testID] [chaosID,chaosID...] 编排实验内容 new details\n" +
                "         |  shield -ns [name] [type] [startPath] [endPath] [JAVA_HOME] [params]　新增chaos shell new shell\n" +
                "         |  shield -e [testID] 执行实验 execute\n" +
                "         |  shield -s [testID] 终止实验 shut down\n" +
                "         |  shield -dt [testID] 删除实验，连带实验编排 delete test\n" +
                "         |  shield -dc [chaosID] 删除故障 delete chaos\n" +
                "         |  shield -check_java 显示当前机器运行的Java项目pid\n" +
                "         |  shield -check_web 查看本机网卡规则\n" +
                "         +———————————————————————————————————————————————————");
    }
    // shield -at 展示所有实验 all test
    private void showAllTest(String[] subCmd) {
        System.out.println("+————————————————————————————————————————————————————————");
        List<ExperimentTest> experimentTests = experimentTestDao.selectAll();
        for (ExperimentTest test : experimentTests) {
            System.out.println(test);
        }
        System.out.println("+————————————————————————————————————————————————————————");
    }
    // shield -d [testID] detail 展示实验编排细节
    private void showDetail(String[] subCmd) {
        if (subCmd.length != 3) {
            System.out.println("=> 展示实验编排细节 shield -d [testID] <=");
            return;
        }
        String testIdStr = subCmd[2];
        int testId;
        try {
            testId = Integer.parseInt(testIdStr);
        } catch (Exception e) {
            System.out.println("=> 展示实验编排细节 shield -d [testID] <=");
            return;
        }
        System.out.println("+————————————————————————————————————————————————————————");
        List<ExperimentDetail> experimentDetails = experimentDetailDao.selectByTestId(testId);
        for (ExperimentDetail detail : experimentDetails) {
            System.out.println(detail);
        }
        System.out.println("+————————————————————————————————————————————————————————");
    }
    // shield -ac 展示所有故障 all chaos
    private void showAllChaos(String[] subCmd) {
        System.out.println("+————————————————————————————————————————————————————————");
        List<ChaosShell> chaosShells = chaosShellDao.selectAll();
        for (ChaosShell shell : chaosShells) {
            System.out.println(shell);
        }
        System.out.println("+————————————————————————————————————————————————————————");
    }
    // shield -act 展示故障类别 all chaos type
    private void showAllChaosType(String[] subCmd) {
        System.out.println("+————————————————————————————————————————————————————————");
        for (ChaosType type : ChaosType.values()) {
            System.out.println("| " + type.getType() + " => " + type.getName());
        }
        System.out.println("+————————————————————————————————————————————————————————");
    }
    // shield -nt [testName] [目标JAVA项目的ｐｉｄ（没有写0）]新建实验 new test
    private void showNewTest(String[] subCmd) {
        if (subCmd.length != 3 && subCmd.length != 4) {
            System.out.println("=> 新建实验 shield -nt [testName] [目标JAVA项目的ｐｉｄ（如有）] <=");
            return;
        }
        String testName = subCmd[2];
        int javaPid;
        try {
            javaPid = subCmd.length == 4 ? Integer.parseInt(subCmd[3]) : 0;
        } catch (Exception e) {
            System.out.println("=> 新建实验 shield -nt [testName] [目标JAVA项目的ｐｉｄ（如有）] <=");
            return;
        }
        experimentTestDao.insert(ExperimentTest.builder()
                        .name(testName)
                        .javaPid(javaPid)
                        .state(0)
                        .isDeleted(0)
                        .build()
        );
        System.out.println("=> 新建实验成功 <=");
    }
    // shield -nd [testID] [chaosID,chaosID...] 编排实验内容 new details
    private void showNewDetail(String[] subCmd) {
        if (subCmd.length < 4) {
            System.out.println("=> 编排实验内容 shield -nd [testID] [chaosID,chaosID...] <+");
            return;
        }
        List<Integer> chaosIdList = new ArrayList<>();
        int testId;
        try {
            testId = Integer.parseInt(subCmd[2]);
            chaosIdList = new ArrayList<>();
            for (int i = 3; i < subCmd.length; i++) {
                chaosIdList.add(Integer.parseInt(subCmd[i]));
            }
        } catch (Exception e) {
            System.out.println("=> 编排实验内容 shield -nd [testID] [chaosID,chaosID...] <+");
            return;
        }
        Weaver.weave(testId, chaosIdList);
        System.out.println("=> 新建编排成功 <=");
    }
    // shield -ns [name] [type] [startPath] [endPath] [JAVA_HOME] [params]　新增chaos shell new shell
    private void showNewShell(String[] subCmd) {
        if (subCmd.length != 8) {
            System.out.println("=> 　新增chaos shield -ns [name] [type] [startPath] [endPath] [JAVA_HOME] [params] <=");
            return;
        }
        int type;
        try {
            type = Integer.parseInt(subCmd[3]);
        } catch (Exception e) {
            System.out.println("=> 　新增chaos shield -ns [name] [type] [startPath] [endPath] [JAVA_HOME] [params] <=");
            return;
        }
        chaosShellDao.insert(
                ChaosShell.builder()
                        .name(subCmd[2])
                        .type(type)
                        .startPath(subCmd[4])
                        .endPath(subCmd[5])
                        .javaHome(subCmd[6])
                        .params(subCmd[7])
                        .build()
        );
        System.out.println("=> 添加成功 <=");
    }
    // shield -e [testID] 执行实验 execute
    private void executeTest(String[] subCmd) {
        if (subCmd.length != 3) {
            System.out.println("=> 执行实验 shield -e [testID] <=");
            return;
        }
        int testId;
        try {
            testId = Integer.parseInt(subCmd[2]);
        } catch (Exception e) {
            System.out.println("=> 执行实验 shield -e [testID] <=");
            return;
        }
        ExperimentTest experimentTest = experimentTestDao.selectById(testId);
        if (experimentTest == null) {
            System.out.println("=> Test ID 不存在 <=");
            return;
        }
        Scheduler.startTest(testId, experimentTest.getJavaPid());
        System.out.println("=> 正在执行 <=");
    }
    // shield -s [testID] 终止实验 shut down
    private void stopTest(String[] subCmd) {
        if (subCmd.length != 3) {
            System.out.println("=> 终止实验 shield -s [testID] <=");
            return;
        }
        int testId;
        try {
            testId = Integer.parseInt(subCmd[2]);
        } catch (Exception e) {
            System.out.println("=> 终止实验 shield -s [testID] <=");
            return;
        }
        ExperimentTest experimentTest = experimentTestDao.selectById(testId);
        if (experimentTest == null) {
            System.out.println("=> Test ID 不存在 <=");
            return;
        }
        Scheduler.shutdownTest(testId, experimentTest.getJavaPid());
        System.out.println("=> 终止完毕 <=");
    }
    // shield -dt [testID] 删除实验，连带实验编排 delete test
    private void showDeleteTest(String[] subCmd) {
        if (subCmd.length != 3) {
            System.out.println("=> 删除实验，连带实验编排 shield -dt <=");
            return;
        }
        int testId;
        try {
            testId = Integer.parseInt(subCmd[2]);
        } catch (Exception e) {
            System.out.println("=> 删除实验，连带实验编排 shield -dt <=");
            return;
        }
        ExperimentTest experimentTest = experimentTestDao.selectById(testId);
        if (experimentTest == null || State.RUNNING.getState() == experimentTest.getState()) {
            System.out.println("=> 删除实验失败，实验不存在或正在运行<=");
            return;
        }
        experimentTestDao.deleteById(testId);
        experimentDetailDao.deleteByTestId(testId);
        System.out.println("=> 删除成功 <=");
    }
    // shield -dc [chaosID] 删除故障 delete chaos
    private void showDeleteChaos(String[] subCmd) {
        if (subCmd.length != 3) {
            System.out.println("=> 删除故障 shield -dc [chaosID] <=");
            return;
        }
        int chaosId;
        try {
            chaosId = Integer.parseInt(subCmd[2]);
        } catch (Exception e) {
            System.out.println("=> 删除故障 shield -dc [chaosID] <=");
            return;
        }
        chaosShellDao.deleteById(chaosId);
        System.out.println("=> 删除成功 <=");
    }
    // shield -check_java 显示当前机器运行的Java项目pid
    private void checkJava(String[] subCmd) {
        if (subCmd.length != 2) {
            System.out.println("=> shield -check_java 有参数则查看Java项目挂载的moudle，没有则显示当前机器运行的Java项目pid <=");
            return;
        }
        try {
            ProcessBuilder pb = new ProcessBuilder("jps");
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String str;
            while ((str = reader.readLine()) != null) {
                System.out.println(str);
            }
        } catch (Exception e) {
            System.out.println("=> 未知故障:shield -check_java [java_pid]  <=");
        }
    }
    // shield -check_web 查看本机网卡规则
    private void checkWeb(String[] subCmd) {
        if (subCmd.length != 2) {
            System.out.println("=> shield -check_web 查看本机网卡规则 <=");
            return;
        }
        try {
            // tc qdisc show dev ens33
            ProcessBuilder pb = new ProcessBuilder("tc", "qdisc", "show", "dev", "ens33");
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String str;
            while ((str = reader.readLine()) != null) {
                System.out.println(str);
            }
        } catch (Exception e) {
            System.out.println("=> 未知故障:shield -check_web <=");
        }
    }

}
