# ChaosShield
运行在Ubuntu系统的简易混沌工程平台
## 配置
自行配置Prometheus和Node Exporter充当监控模块
## 运行：
运行需要提供root权限（模拟网络故障指令需要root权限）
## 使用
*  shield -h 展示所有命令
*  shield -at 展示所有实验 all test
*  shield -d [testID] detail 展示实验编排细节
*  shield -ac 展示所有故障 all chaos
*  shield -act 展示故障类别 all chaos type
*  shield -nt [testName] [目标JAVA项目的ｐｉｄ（没有写0）]新建实验 new test
*  shield -nd [testID] [chaosID,chaosID...] 编排实验内容 new details
*  shield -ns [name] [type] [startPath] [endPath] [JAVA_HOME] [params]　新增chaos shell new shell
*  shield -e [testID] 执行实验 execute
*  shield -s [testID] 终止实验 execute
*  shield -dt [testID] 删除实验，连带实验编排 delete test
*  shield -dc [chaosID] 删除故障 delete chaos
*  shield -check_java 显示当前机器运行的Java项目pid
*  shield -check_web 查看本机网卡规则

## 故障类别
* 机器类：其中网卡类故障针对Ubuntu系统默认网卡：ens33
>CPU使用率高
>
>磁盘IO高
>
>磁盘满
>
>内存占用高
>
>网络丢包
>
>包延迟
>
>包乱序
>
>包损坏
>
>包重复
* JAVA应用类
>1、开发好的sandbox-moudle执行mvn clean package，将with-all-dependencies JAR包移动到sandbox/sandbox-moudle
>
>2、使用shield指令将moudle注册到库中
>
>3、JAVA项目的故障注入参数是固定的，目前有两个针对APP类getStr()方法的demo，分别为修改返回值和响应延迟


