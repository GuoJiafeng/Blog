#!/bin/bash

echo "#################################"
echo "##   @author guojiafeng        ##"
echo "##   @info   安装 Blog          ##"
echo "##			                  ##"
echo "#################################"



echo "更新yum yum update"
yum update

echo "移除旧版git yum remove git*"
yum remove git*


echo "下载新版git 2.3.0 wget -c "https://github.com/git/git/archive/v2.3.0.tar,gz" -O git.tar.gz"

wget -c "https://github.com/git/git/archive/v2.3.0.tar.gz" -O git.tar.gz


echo "解压 git 2.3.0 | tar -zxvf git.tar.gz"
tar -zxvf git.tar.gz

echo "进入 git 2.3.0 目录 cd git-2.3.0"
cd git-2.3.0

echo "安装依赖库 yum install curl-devel expat-devel gettext-devel openssl-devel zlib-devel gcc perl-ExtUtils-Embed"
yum install curl-devel expat-devel gettext-devel openssl-devel zlib-devel gcc perl-ExtUtils-Embed

echo "安装编译git make prefix=/usr/local/git install"
make prefix=/usr/local/git install

echo "打印git 版本 /usr/local/git/bin/git --version"
/usr/local/git/bin/git --version

echo "退出当前目录 cd .."
cd ..




echo "yum安装 java "

yum remove  java*

yum -y install java-1.8.0-openjdk-devel.x86_64

echo "安装下载 maven "
wget -c "http://archive.apache.org/dist/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.tar.gz" -O maven.tar.gz

echo "解压 maven tar -zxvf maven.tar.gz "
tar -zxvf maven.tar.gz




echo "下载仓库 git clone https://github.com/GuoJiafeng/Blog.git"
/usr/local/git/bin/git clone https://github.com/GuoJiafeng/Blog.git

echo "进入仓库目录 cd Blog"
cd Blog

echo "开始运行"

sh run.sh


