#!/bin/bash

echo "#################################"
echo "##   @author guojiafeng        ##"
echo "##   @info   安装 Blog          ##"
echo "##			                  ##"
echo "#################################"

echo "下载仓库 git clone https://github.com/GuoJiafeng/Blog.git"
git clone https://github.com/GuoJiafeng/Blog.git

echo "进入仓库目录 cd Blog"
cd Blog

echo "更新yum yum update"
yum update

echo "移除旧版git yum remove git*"
yum remove git*


echo "下载新版git 2.3.0 wget -c "https://github.com/git/git/archive/v2.3.0.tar,gz" -O git.tar.gz"

wget -c "https://github.com/git/git/archive/v2.3.0.tar,gz" -O git.tar.gz


echo "解压 git 2.3.0 tar -zxvf git-2.3.0.gz"
tar -zxvf git-2.3.0.gz

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


echo "开始运行"

sh run.sh


