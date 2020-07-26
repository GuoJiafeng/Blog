#! /bin/bash

#################################
##   @author guojiafeng        ##
#################################

pid=`ps -ef | grep run.sh | grep -v "grep" | awk '{print $2}'`
echo $pid

if [ -n $pid ];
    then
    for id in $pid
do
    kill -9 $id
    echo "killed $id"
done

fi
