#!/bin/bash

echo "#################################"
echo "##   @author guojiafeng        ##"
echo "##   @info   运行 Blog          ##"
echo "##                             ##"
echo "#################################"


echo "查看是否需要关闭残留进程"




echo "删除无用文件"
rm -rf ./*md
rm -rf ./push_github.sh

echo "更新 git "
/usr/local/git/bin/git pull

echo "开始 编译 "
../apache-maven-3.3.9/bin/mvn clean package

echo "开始运行"
java -jar ./target/Blog.jar
