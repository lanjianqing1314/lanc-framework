#!/bin/bash

# 服务名称
SERVER_NAME=lanc-framework

# 当前版本
VERSION="1.0.0"

# 容器端口
SERVER_PORT=9818

# 宿主机映射的端口
SERVER_PORT_OUT=9818

# 配置环境
ACTIVE="sit"

# 健康校验时间间隔
INTERVAL=30s

# 监控检测接口调用过时时间
TIMEOUT=30s

# CMS收集器在老年代达到70%时触发
CMS_INITIATING_OCCUPANCY_FRACTION=70

# 禁用SoftReference对象的LRU回收策略
SOFT_REF_LRU_POLICY_MS_PER_MB=0

# Eden区与Survivor区的比例为8:1
SURVIVOR_RATIO=8

# 初始堆内存大小为1GB
XMS=1g

# 最大堆内存大小为1GB
XMX=1g

# 新生代堆内存大小为512MB
XMN=512m

# 最多保留10个GC日志文件
NUMBER_OF_GC_LOG_FILES=10

# 每个GC日志文件的最大大小为100MB
METASPACE_SIZE=128m

# 最大的元空间大小为256MB
MAX_METASPACE_SIZE=256m
