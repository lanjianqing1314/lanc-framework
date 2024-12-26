```shell
docker-compose down
docker volume prune
docker volume rm root_mongo1-data root_mongo2-data root_mongo3-data
mkdir mongo
cd mongo/
vim docker-compose.yml
```

docker-compose.yml
```shell
version: '3.8'

services:
  mongo1:
    image: mongo:6.0
    container_name: mongo1
    hostname: mongo1
    restart: always
    ports:
      - 27017:27017 # 主机端口映射到 mongo1 的 27017
    command: ["mongod", "--replSet", "rs0", "--bind_ip_all"]
    volumes:
      - mongo1-data:/data/db
      - mongo1-logs:/var/log/mongodb
    healthcheck:
      test: ["CMD", "mongo", "--eval", "db.adminCommand('ping')"]
      interval: 10s
      timeout: 10s
      retries: 5

  mongo2:
    image: mongo:6.0
    container_name: mongo2
    hostname: mongo2
    restart: always
    ports:
      - 27018:27017 # 主机端口 27018 映射到 mongo2 的 27017
    command: ["mongod", "--replSet", "rs0", "--bind_ip_all"]
    volumes:
      - mongo2-data:/data/db
      - mongo2-logs:/var/log/mongodb
    healthcheck:
      test: ["CMD", "mongo", "--eval", "db.adminCommand('ping')"]
      interval: 10s
      timeout: 10s
      retries: 5

  mongo3:
    image: mongo:6.0
    container_name: mongo3
    hostname: mongo3
    restart: always
    ports:
      - 27019:27017 # 主机端口 27019 映射到 mongo3 的 27017
    command: ["mongod", "--replSet", "rs0", "--bind_ip_all"]
    volumes:
      - mongo3-data:/data/db
      - mongo3-logs:/var/log/mongodb
    healthcheck:
      test: ["CMD", "mongo", "--eval", "db.adminCommand('ping')"]
      interval: 10s
      timeout: 10s
      retries: 5

volumes:
  mongo1-data:
  mongo1-logs:
  mongo2-data:
  mongo2-logs:
  mongo3-data:
  mongo3-logs:


```

```shell
docker-compose up -d

docker exec -it mongo1 mongosh

rs.initiate({
    _id: "rs0",
    members: [
        { _id: 0, host: "119.3.237.172:27017" },
        { _id: 1, host: "119.3.237.172:27018" },
        { _id: 2, host: "119.3.237.172:27019" }
    ]
})

rs.status()
```

测试办法：
![img.png](img.png)
![img_1.png](img_1.png)
注意，同一个clientId同时连接会被覆盖
![img_2.png](img_2.png)
![img_3.png](img_3.png)
