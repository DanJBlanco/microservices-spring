# Run

### 1. init zookeeper as the cluster

docker-compose -f common.yml -f zookeeper.yml up

### 2. health of ZooKeeper

echo ruok | nc localhost 2181

### 3. init Kafka clusters

docker-compose -f common.yml -f kafka_cluster.yml up

### 4. init kafka compose

docker-compose -f common.yml -f init_kafka.yml up

### 5. Open web browser

http://localhost:9000/

### 6. Register cluster

assignt a name: food-ordering-system-cluster

enter zookeeper: zookeeper:2181

save

### 7. Check if data is on the topic

kafkacat -C -b localhost19092 -t payment-request

### 8. FIX error schema kafka

curl -X PUT -H "Content-Type: application/json"  --data '{"compatibility": "NONE"}' http://localhost:8081/config/{topic-name}-value
