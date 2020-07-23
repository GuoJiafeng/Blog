#!/bin/bash

### 开始安装 nginx

yum install nginx

### 启动 nginx

nginx

### 删除 html 文件
rm -rf /usr/share/nginx/html/*

### 安装 git

yum remove git*

yum install git

####  开始克隆仓库

git clone git@github.com:GuoJiafeng/ProblemRepository.git


### 开始运行

sh run.sh

