#!/bin/bash

set -e
set -u

echo ""

rm -f ./Deploy/PXXX.jar
7z a -tzip Deploy/PXXX.jar ../PXXX/bin/ch

echo ""

