kubectl get pods

kubectl apply -f  kafka-client.yml

kubectl exec -it kafka-client -- /bin/bash ( use pod's shell command )

helm uninstall local-confluent-kafka ( name of cluster )

kubectl delete -f kafka-client.yml

=== create customer topics inside pod

kafka-topics --zookeeper local-confluent-kafka-cp-zookeeper-headless:2181 --topic "TOPIC_NAME" --create

--partitions 3 --replication-factor 3 --if-not-exists

kafka-topics --zookeeper local-confluent-kafka-cp-zookeeper-headless:2181 --list

---

### Create docker images for each microservices

1. create images

mvn clean install

2. get docker images (grep(?))

docker images | grep food.ordering.system

3. create kubernetes deployment files

   create application-deployment-loca.yml into food-or....-infra directory
