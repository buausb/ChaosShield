## 运行：

以JAR包形式命令行运行，运行需要以root权限运行（模拟网络故障时需要root权限）
对于Java应用程序的注入依赖于JVM-Sandbox，
？？？需要注入Java应用类故障时需确保机器正确安装了JVM-Sandbox？？？


确认网络故障注入是否成功，"sudo tc qdisc show dev ens33" （Ubuntu系统默认ens33）

qdisc 全称queueing discipline 网卡的队列规则

由于网络故障注入都是基于覆盖网卡规则实现的，所以同一时刻只能有一个网络故障被注入运行
