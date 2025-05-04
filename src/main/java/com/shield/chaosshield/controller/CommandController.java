package com.shield.chaosshield.controller;

import com.shield.chaosshield.common.ChaosType;
import com.shield.chaosshield.common.Response;
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
import com.shield.chaosshield.schedule.Scheduler;
import com.shield.chaosshield.weave.Weaver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shield")
public class CommandController {

    ExperimentTestDao experimentTestDao = new ExperimentTestDaoImpl();
    ExperimentDetailDao experimentDetailDao = new ExperimentDetailDaoImpl();
    ChaosShellDao chaosShellDao = new ChaosShellDaoImpl();
    
    // shield -at 展示所有实验 all test
    @GetMapping("/at")
    public Response showAllTest() {
        StringBuilder res = new StringBuilder();
        res.append("+————————————————————————————————————————————————————————\n");
        List<ExperimentTest> experimentTests = experimentTestDao.selectAll();
        for (ExperimentTest test : experimentTests) {
            res.append(test).append("\n");
        }
        res.append("+————————————————————————————————————————————————————————\n");
        return new Response(Response.SUCCEED_NUM, res.toString());
    }
    // shield -d [testID] detail 展示实验编排细节
    @GetMapping("/d/{testid}")
    public Response showDetail(@PathVariable("testid") Integer testId) {
        StringBuilder res = new StringBuilder();
        res.append("+————————————————————————————————————————————————————————\n");
        List<ExperimentDetail> experimentDetails = experimentDetailDao.selectByTestId(testId);
        for (ExperimentDetail detail : experimentDetails) {
            res.append(detail).append("\n");
        }
        res.append("+————————————————————————————————————————————————————————\n");
        return new Response(Response.SUCCEED_NUM, res.toString());
    }
    // shield -ac 展示所有故障 all chaos
    @GetMapping("/ac")
    public Response showAllChaos() {
        StringBuilder res = new StringBuilder();
        res.append("+————————————————————————————————————————————————————————\n");
        List<ChaosShell> chaosShells = chaosShellDao.selectAll();
        for (ChaosShell shell : chaosShells) {
            res.append(shell).append("\n");
        }
        res.append("+————————————————————————————————————————————————————————\n");
        return new Response(Response.SUCCEED_NUM, res.toString());
    }
    // shield -act 展示故障类别 all chaos type
    @GetMapping("/act")
    public Response showAllChaosType() {
        StringBuilder res = new StringBuilder();
        res.append("+————————————————————————————————————————————————————————\n");
        for (ChaosType type : ChaosType.values()) {
            res.append("| ")
                    .append(type.getType())
                    .append(" => ")
                    .append(type.getName())
                    .append("\n");
        }
        res.append("+————————————————————————————————————————————————————————\n");
        return new Response(Response.SUCCEED_NUM, res.toString());
    }
    // shield -nt [testName] [目标JAVA项目的ｐｉｄ（没有写0）]新建实验 new test
    @GetMapping("/nt/{testname}/{javapid}")
    public Response showNewTest(@PathVariable("testname") String testName,
                                @PathVariable("javapid")Integer javaPid) {
        experimentTestDao.insert(ExperimentTest.builder()
                .name(testName)
                .javaPid(javaPid)
                .state(0)
                .isDeleted(0)
                .build()
        );
        return Response.SUCCEED;
    }
    // shield -nd [testID] [chaosID,chaosID...] 编排实验内容 new details
    @GetMapping("/nd/{testid}/{chaosid}")
    public Response showNewDetail(@PathVariable("testid") Integer testId,
                                  @PathVariable("chaosid")String chaosId) {
        try {
            List<Integer> chaosIdList = Arrays.stream(chaosId.split(","))
                    .map(Integer::parseInt).collect(Collectors.toList());
            Weaver.weave(testId, chaosIdList);
        } catch (Exception e) {
            return Response.FAILED;
        }
        return Response.SUCCEED;
    }

    // shield -e [testID] 执行实验 execute
    @GetMapping("/e/{testid}")
    public Response executeTest(@PathVariable("testid")Integer testId) {
        StringBuilder res = new StringBuilder();
        ExperimentTest experimentTest = experimentTestDao.selectById(testId);
        if (experimentTest == null) {
            res.append("=> Test ID 不存在 <=").append("\n");
            return new Response(Response.FAILED_NUM, res.toString());
        }
        Scheduler.startTest(testId, experimentTest.getJavaPid());
        return Response.SUCCEED;
    }
    // shield -s [testID] 终止实验 shut down
    @GetMapping("/s/{testid}")
    public Response stopTest(@PathVariable("testid")Integer testId) {
        StringBuilder res = new StringBuilder();
        ExperimentTest experimentTest = experimentTestDao.selectById(testId);
        if (experimentTest == null) {
            res.append("=> Test ID 不存在 <=").append("\n");
            return new Response(Response.FAILED_NUM, res.toString());
        }
        Scheduler.shutdownTest(testId, experimentTest.getJavaPid());
        return Response.SUCCEED;
    }
    // shield -dt [testID] 删除实验，连带实验编排 delete test
    @GetMapping("/dt/{testid}")
    public Response showDeleteTest(@PathVariable("testid")Integer testId) {
        StringBuilder res = new StringBuilder();
        ExperimentTest experimentTest = experimentTestDao.selectById(testId);
        if (experimentTest == null || State.RUNNING.getState() == experimentTest.getState()) {
            res.append("=> 删除实验失败，实验不存在或正在运行<=\n");
            return new Response(Response.FAILED_NUM, res.toString());
        }
        experimentTestDao.deleteById(testId);
        experimentDetailDao.deleteByTestId(testId);
        return Response.SUCCEED;
    }

    // shield -check_java 显示当前机器运行的Java项目pid
    @GetMapping("/check/java")
    public Response checkJava() {
        StringBuilder res = new StringBuilder();
        try {
            ProcessBuilder pb = new ProcessBuilder("jps");
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String str;
            while ((str = reader.readLine()) != null) {
                res.append(str).append("\n");
            }
        } catch (Exception e) {
            res.append("=> 未知故障:shield -check_java [java_pid]  <=\n");
        }
        return new Response(Response.SUCCEED_NUM, res.toString());
    }
    // shield -check_web 查看本机网卡规则
    @GetMapping("/check/web")
    public Response checkWeb() {
        StringBuilder res = new StringBuilder();
        try {
            // tc qdisc show dev ens33
            ProcessBuilder pb = new ProcessBuilder("tc", "qdisc", "show", "dev", "ens33");
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String str;
            while ((str = reader.readLine()) != null) {
                res.append(str).append("\n");
            }
        } catch (Exception e) {
            res.append("=> 未知故障:shield -check_web <=\n");
        }
        return new Response(Response.SUCCEED_NUM, res.toString());
    }

}
