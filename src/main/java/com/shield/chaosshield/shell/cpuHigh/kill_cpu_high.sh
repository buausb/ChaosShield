#!/bin/bash
attr=$!
echo $attr
pids=$(pstree -p $attr | awk 'BEGIN{ FS = "(" ; RS = ")" } NF > 1 { print $NF }')

for pid in $pids
do
  `kill -2 $pid`
done