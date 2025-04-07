package com.shield.chaosshield;

import java.util.Scanner;

public class Server {

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    private void start() {
        Scanner s = new Scanner(System.in);
        String cmd;
        while (true) {
            cmd = s.nextLine();
            /**
             * TODO shield -h 展示所有命令
             * TODO shield -at 展示所有实验 all test
             * TODO shield -d [testID] detail 展示实验编排细节
             * TODO shield -ac 展示所有故障 all chaos
             * TODO shield -act 展示故障类别 all chaos type
             *
             * TODO shield -nt [testName] [目标JAVA项目的ｐｉｄ（如有）]新建实验 new test
             * TODO shield -nd [testID] [chaosID,chaosID...] 编排实验内容 new details
             * TODO shield -ns [name] [type] [startPath] [endPath] [JAVA_HOME(如果针对JAVA项目)] [params]　新增chaos shell new shell
             *
             * TODO shield -e [testID] 执行实验 execute
             *
             * TODO shield -dt [testID] 删除实验，连带实验编排 delete test
             * TODO shield -dc [chaosID] 删除故障 delete chaos
             * */
        }
    }


}
