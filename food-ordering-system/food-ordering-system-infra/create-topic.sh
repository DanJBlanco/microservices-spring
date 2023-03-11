# $1: local-confluent-kafka-cp-zookeeper-headless
kafka-topics --zookeeper $1:2181 --topic "TOPIC_NAME" --delete --if-exists
kafka-topics --zookeeper $1:2181 --topic "TOPIC_NAME" --create --partitions 3 --replication-factor 3 --if-not-exists