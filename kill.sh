#!/bin/bash

#################################
##   @author guojiafeng        ##
#################################

pid=`ps -ef | grep run.sh | grep -v "grep" | awk '{print $2}'`
ishave=`ps -ef | grep run.sh | grep -v "grep" |wc -l`
echo $pid

if [ $ishave -le 0 ];then
    echo "没有"

    else
   "有"
    for id in $pid
do
    kill -9 $id
    echo "killed $id"
done

fi
