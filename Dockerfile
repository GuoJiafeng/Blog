FROM arm64v8/openjdk:8-jdk
# 作者
MAINTAINER gjf <iamgjf@qq.com>
# VOLUME 指定了临时文件目录为/tmp。
# 其效果是在主机 /var/lib/docker 目录下创建了一个临时文件，并链接到容器的/tmp
VOLUME /tmp
# 将jar包添加到容器中并更名为app.jar
ADD ./target/*.jar app.jar
ADD opentracing-specialagent-1.7.1.jar agent.jar
# 运行jar包
RUN bash -c 'touch /app.jar'
RUN bash -c 'touch /agent.jar'

ENTRYPOINT ["java",
              " -javaagent:agent.jar",
             "-Dsa.exporter=jaeger ",
             "-Dsa.instrumentation.plugin.*.disable ",
             "-Dsa.instrumentation.plugin.servlet.enable=true ",
             "-Dsa.instrumentation.plugin.thread.enable=false ",
             "-Dsa.instrumentation.plugin.apache:httpclient.enable=false ",
             "-Dsa.instrumentation.plugin.apache:httpclient:3.enable=false ",
             "-Dsa.instrumentation.plugin.spring:boot.enable=false ",
             "-DJAEGER_PROPAGATION=b3 ",
             "-DJAEGER_SERVICE_NAME=blog ",
             "-DJAEGER_ENDPOINT=http://192.168.123.156:14268/api/traces ",
             "-DJAEGER_SAMPLER_TYPE=const ",
             "-DJAEGER_SAMPLER_PARAM=1 ",
             "-Dsa.httpHeaderTags1=processId=processId,taskId=taskId",
             "-Dsa.log.level=INFO","-jar","/app.jar"]
