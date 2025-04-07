#!/bin/bash
`touch ./diskfull.drill`
`chmod +w ./diskfull.drill`
# 10G
`dd if=/dev/zero of=./diskfull.drill  bs=1M count=10240  oflag=dsync`