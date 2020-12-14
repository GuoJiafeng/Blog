## mvn编译

export  PATH=/root/jdk1.8.0_271/bin:/root/apache-maven-3.6.3/bin:$PATH

mvn clean package

# 创建新镜像
docker build -t thisisgjf/blog . && \

docker push thisisgjf/blog

kubectl delete svc  blog

kubectl delete deploy  blog

kubectl create -f  blog.yaml