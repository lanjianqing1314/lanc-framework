#!/bin/sh

# 引入配置常量文件
source "$(dirname "$0")/config.sh"

SERVER_DOCKER_IMAGE=${SERVER_NAME}:${VERSION}

OPERATE="build"

generate_dockerfile() {
  cat <<EOF > Dockerfile
# Use a lighter base image
FROM openjdk:8

MAINTAINER jianqing.lan

# Set environment variables
ENV TZ=Asia/Shanghai

# Set the time zone
RUN ln -snf /usr/share/zoneinfo/\$TZ /etc/localtime && echo \$TZ > /etc/timezone

# Install curl for health checks
RUN apt-get update && apt-get install -y curl && apt-get clean

# Add the application jar file
ADD ./${SERVER_NAME}-${VERSION}.jar app.jar

# Expose the application port
EXPOSE ${SERVER_PORT}

# Set the working directory
WORKDIR /

# Declare volume
VOLUME /data

# Set the entry point
ENTRYPOINT ["java", "-XX:+UseConcMarkSweepGC", "-XX:+UseCMSCompactAtFullCollection", "-XX:CMSInitiatingOccupancyFraction=${CMS_INITIATING_OCCUPANCY_FRACTION}", "-XX:+CMSParallelRemarkEnabled", "-XX:SoftRefLRUPolicyMSPerMB=${SOFT_REF_LRU_POLICY_MS_PER_MB}", "-XX:+CMSClassUnloadingEnabled", "-XX:SurvivorRatio=${SURVIVOR_RATIO}", "-Xloggc:/logs/gc.log", "-Xms${XMS}", "-Xmx${XMX}", "-Xmn${XMN}", "-verbose:gc", "-XX:+PrintGCDetails", "-XX:+PrintGCDateStamps", "-XX:+PrintGCTimeStamps", "-XX:+UseGCLogFileRotation", "-XX:NumberOfGCLogFiles=${NUMBER_OF_GC_LOG_FILES}", "-XX:GCLogFileSize=100M",  "-XX:MetaspaceSize=${METASPACE_SIZE}", "-XX:MaxMetaspaceSize=${MAX_METASPACE_SIZE}", "-jar", "app.jar", "--spring.profiles.active=${ACTIVE}"]

# Health check to verify if the application is up
HEALTHCHECK --interval=${INTERVAL} --timeout=30s --retries=3 CMD curl -f http://localhost:${SERVER_PORT}/actuator/health || exit 1
EOF
}

echo "======停止${SERVER_NAME} ..."
docker stop "${SERVER_NAME}"

echo "======移除${SERVER_NAME} ..."
docker rm "${SERVER_NAME}"

echo "======生成 Dockerfile ..."
generate_dockerfile

echo "======build ${SERVER_DOCKER_IMAGE} image start ..."
docker build -t "${SERVER_DOCKER_IMAGE}" .
echo "======build ${SERVER_DOCKER_IMAGE} image end"

echo "======run ${SERVER_DOCKER_IMAGE} start ..."
docker run --tty -d --name "$SERVER_NAME" \
  -v /var/run/docker.sock:/var/run/docker.sock \
  --log-driver json-file \
  --log-opt max-file=3 \
  --log-opt max-size=50m \
  --restart=unless-stopped \
  --net=host \
  --health-cmd="curl -f http://localhost:${SERVER_PORT}/actuator/health || exit 1" \
  --health-interval=${INTERVAL} \
  --health-timeout=30s \
  --health-retries=3 \
  "${SERVER_DOCKER_IMAGE}"
echo "======run ${SERVER_DOCKER_IMAGE} end"

HEALTH=$(docker inspect --format='{{.State.Health.Status}}' "$SERVER_NAME")
if [ "$HEALTH" == "unhealthy" ]; then
  echo "应用启动失败，正在停止容器..."
  docker stop "$SERVER_NAME"
  docker rm "$SERVER_NAME"
fi
