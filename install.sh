#!/bin/bash

echo "#################################"
echo "##   @author guojiafeng        ##"
echo "##   @info   安装 Blog          ##"
echo "##			                       ##"
echo "#################################"

### 开始安装 nginx

yum update

echo "开始安装 nginx...."
sleep 1

yum install nginx

### 启动 nginx

echo "启动 nginx ...."
sleep 1

nginx

### 删除 html 文件
echo "删除 html 文件..."
sleep 1

rm -rf /usr/share/nginx/html/*

### 安装 git

echo "安装 git...."
sleep 1

yum remove git*

yum install git

####  开始克隆仓库

echo "开始克隆仓库...."

msg=$1

git clone $1

echo "克隆仓库完毕...."


