#!/bin/bash

# 乱序随机

`tc qdisc replace dev ens33 root netem delay 10ms reorder 25% 50%`