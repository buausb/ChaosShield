#!/bin/bash
`touch ./diskfull.drill`
`chmod +w ./diskfull.drill`
# 10G
`dd if=/dev/zero of=./diskfull.drill  bs=4K count=$((102400*256))  oflag=dsync`
