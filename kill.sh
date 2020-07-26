#!/bin/bash

#################################
##   @author guojiafeng        ##
#################################

pid=`ps -ef | grep run.sh | grep -v "grep" | awk '{print $2}'`
ishave=`ps -ef | grep run.sh | grep -v "grep" |wc -l`

if [ $ishave -le 0 ];then
    echo "没有残留进程"

    else
   echo "有残留进程"
    for id in $pid
do
    kill -9 $id
    echo "killed $id"
done

fi
