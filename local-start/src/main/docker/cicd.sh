#!/bin/bash

# 当脚本中任何一条命令失败时，脚本终止执行
set -e

# 引入配置常量文件
ROOT_PATH="/onedev-build/workspace"
ENV="dev"
. ${ROOT_PATH}/local-start/src/main/docker/${ENV}/config.sh

SOURCE_PATH="./local-start/src/main/docker/"
CICD_PATH="${SOURCE_PATH}${ENV}/"
ONEDEV="10.7.103.6:6610"
MACHINE_ID=15
PUBLISH_TYPE="ServerPublish"
FILE_PATH="${SOURCE_PATH}${SERVER_NAME}-${VERSION}.jar"

# 创建call.sh脚本
cat > $ROOT_PATH/call.sh <<EOF
#!/bin/bash
# 第一步：获取submitterId
echo "执行这里1，SERVER_NAME：$SERVER_NAME"
submitterId=\$(curl -u lanjianqing:ljq_dkw123 -G http://$ONEDEV/~api/builds \
    --data-urlencode 'query="Project" is "dakewe-cloud-admin"' \
    --data-urlencode offset=0 \
    --data-urlencode count=1 | jq -r '.[0].submitterId')
echo "执行这里2，submitterId：\${submitterId}"
# 第二步：使用submitterId获取用户名
username=\$(curl -u lanjianqing:ljq_dkw123 http://$ONEDEV/~api/users/\$submitterId | jq -r '.name')
echo "执行这里3，username：\${username}"
# 第三步：使用获取到的用户名上传文件
response=\$(curl -f -k -s -X POST "http://10.7.103.6:17778/dakewe/api/machines/$MACHINE_ID/container/files/upload" \
     -F "file=@${SERVER_NAME}-${VERSION}.tar.gz" \
     -F "serverName=onedev-server" \
     -F "openKey=x1i37yMfi8V74J7By6KYBwv391x2Bwyw" \
     -F "publishType=$PUBLISH_TYPE" \
     -F "username=\${username}" | jq '.')
# 判断上传是否成功
echo "执行这里4，response：\${response}"
code=\$(echo \$response | jq -r '.code')
if [ "\$code" = "200" ]; then
  echo "发布成功"
else
  echo "发布失败，终止操作"
  exit 1
fi
EOF

echo '------------------------------------------------------------------'
mvn clean install
echo '--------------------ls----------------------------------------------'
ls
if [ ! -f "$FILE_PATH" ]; then
    echo "打包失败，文件不存在: $FILE_PATH"
    exit 1
fi
cp $FILE_PATH $CICD_PATH
cp $SOURCE_PATH/run.sh $CICD_PATH
cd $CICD_PATH
tar -czvf "${SERVER_NAME}-${VERSION}.tar.gz" *
mv "${SERVER_NAME}-${VERSION}.tar.gz" $ROOT_PATH
cd $ROOT_PATH

echo '------------------------------------------------------------------'
# 检查jq是否已安装
if ! command -v jq &> /dev/null; then
    # 根据系统使用不同的包管理器安装jq
    if command -v apt-get &> /dev/null; then
        apt-get update && apt-get install -y jq
    elif command -v yum &> /dev/null; then
        yum install -y jq
    else
        echo "未找到支持的包管理器。请手动安装jq。"
        exit 1
    fi
fi

echo '------------------------------------------------------------------'
chmod +x $ROOT_PATH/call.sh

echo '------------------------------------------------------------------'
./call.sh
echo '------------------------------------------------------------------'