#!/bin/sh
#Author: Adit Ghosh
#####################

echo "\n Starting Up Hadoop \n"
#Start Up Hadoop
./sbin/start-all.sh
#Verify hadoop is runinng
echo "\n Verification: Verify Startup \n"
echo "\n Verification: Make Sure all java processes are Running \n"
jps
echo "\n \n"
#Check is datanode is running
echo "\n Verification: Check if datanode is correctly up and running \n "
./bin/hadoop dfsadmin -report



#####################