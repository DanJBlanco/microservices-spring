# Run

### 1. init zookeeper as the cluster

`docker-compose -f common.yml -f zookeeper.yml up`

### 2. health of ZooKeeper

> echo ruok | nc localhost 2181

### 3. init Kafka clusters

`docker-compose -f common.yml -f kafka_cluster.yml up`

### 4. init kafka compose

`docker-compose -f common.yml -f init_kafka.yml up`
