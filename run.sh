#!/bin/bash
echo "删除无用文件"
rm -rf ./*md
rm -rf ./push_github.sh
echo "更新 git "
git pull
echo "开始 编译 "
mvn clean package
echo "开始运行"
java -jar ./target/Blog.jar