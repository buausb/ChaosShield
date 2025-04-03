#!/bin/bash

#该命令将 eth0 网卡的传输设置为随机产生 1% 的重复数据包 。

`tc  qdisc  add  dev  ens33  root  netem  duplicate 1%`
