#!/bin/bash

`tc qdisc add dev ens33 root netem delay 100ms 10ms`

#该命令将 eth0 网卡的传输设置为延迟 100ms ± 10ms （90 ~ 110 ms 之间的任意值）发送。