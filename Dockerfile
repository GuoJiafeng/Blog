FROM arm64v8/openjdk:8-jdk
# 作者
MAINTAINER gjf <iamgjf@qq.com>
# VOLUME 指定了临时文件目录为/tmp。
# 其效果是在主机 /var/lib/docker 目录下创建了一个临时文件，并链接到容器的/tmp
VOLUME /tmp
# 将jar包添加到容器中并更名为app.jar
ADD ./target/*.jar app.jar

ADD opentracing-specialagent-1.7.3.jar agent.jar

# 运行jar包
RUN bash -c 'touch /app.jar'
RUN bash -c 'touch /agent.jar'


ENTRYPOINT ["java","-javaagent:/agent.jar","-Dsa.init.defer=false","-Dsa.exporter=jaeger","-Dsa.instrumentation.plugin.*.disable","-Dsa.httpHeaderTags1=processId=processId,taskId=taskId","-Dsa.log.level=INFO","-jar","/app.jar"]
