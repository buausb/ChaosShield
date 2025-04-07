#!/bin/bash
# 丢包遵循类似正态分布规律,分布为normal、 pareto、 paretonormal等
tc qdisc replace dev ens33 root netem delay 100ms 20ms distribution normal