#!/bin/bash
echo "更新 git "
git pull
echo "开始 编译 "
mvn clean package
echo "开始运行"
java -jar target/Blog.jar