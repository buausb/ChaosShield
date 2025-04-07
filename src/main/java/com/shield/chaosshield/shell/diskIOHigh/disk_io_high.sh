#!/bin/bash
`touch ./diskfull.drill`
`chmod +w ./diskfull.drill`
# 10G
`dd if=/dev/zero of=./diskfull.drill  bs=1K count=$((102400*256))  oflag=dsync`
