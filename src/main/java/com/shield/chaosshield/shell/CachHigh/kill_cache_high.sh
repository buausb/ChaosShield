#!/bin/bash
pids=`pstree -p $1 | awk 'BEGIN { FS = "(" ; RS = ")" } NF > 1 { print $NF }'`

for pid in $pids
do
  `kill -2 $pid`
done