## mvn编译
export JAVA_HOME=/var/jenkins_home/jdk1.8.0_271

export PATH=/var/jenkins_home/jdk1.8.0_271/bin:/var/jenkins_home/apache-maven-3.6.3/bin:$PATH

mvn clean package

wget -O opentracing-specialagent-1.7.3.jar "https://repo1.maven.org/maven2/io/opentracing/contrib/specialagent/opentracing-specialagent/1.7.3/opentracing-specialagent-1.7.3.jar"

# 创建新镜像
docker build -t thisisgjf/blog . && \

docker push thisisgjf/blog

kubectl delete svc  blog

kubectl delete deploy  blog

kubectl create -f  blog.yaml