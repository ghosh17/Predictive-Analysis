#!/bin/sh
#Author: Adit Ghosh
#####################
#File to shut down Hadoop process and verify shut down
####################

#Shut Down Hadoop
echo "\nShutting Down Hadoop\n"
./sbin/stop-all.sh
###################
#Verify Shutdown 
echo "\nVerify Java Process Shutdown\n"
jps
