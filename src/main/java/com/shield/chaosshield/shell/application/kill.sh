#!/bin/bash
pid=$1
output=$(./sandbox/bin/sandbox.sh -p "$pid" -S 2>&1)
echo "$output"