## Steps

Google Cloud platform

* Dashboard
  * create new project
  * set name "food ordering system"
* Kubernetes Engine
  * enable Kubernetes Engine API
  * connect terminal and use gcloud command line interface (click shell icon)
    * set authoritations`gcloud auth login`
    * authorizate `n`
    * config location`gcloud config set compute/zone europe-west4-a`
    * create cluster `gcloud container clusters create food-ordering-system-cluster`
    * `kubectl get nodes`
    * Click option menu, copy and run command to connecto to kubernete cluster
* Artifactory Registery
  * Artifact Registry API
    * enable
  * Create repository
    * name
      * food-ordering-system-repository
    * Format
      * docker
    * Location Type
      * Region
    * Region
      * europe-west4
    * Create
  * Push local imagen to google repository
    * Install gcloud CLI
    * gcloud auth login
    * docker images | grep food.ordering.system
    * config local docker location `gcloud auth configure-docker europe-west4-docker.pkg.dev`
    * docker tag images
    * docker push images
  * Google Console
    * Open shell
    * git clone cp-helm-charts
    * helm version
    * helm install gke-confluent-kafka cp-helm-charts/ --version 0.6.0
    * approve authoritation
    * kubectl get pods
    * git clone infra projectcd
    * cd food-ordering-system-infra
    * kubectl apply -f kafka-client.yml
    * kubectl get pods
    * kubectl cp create-topics.sh kafka-client/kafka-client-storage
    * kubectl exec -it kafka-client -- /bin/bash
    * kubectl cp create-topics.sh kafka-client:/kafka-client-storage
    * kubectl exec -it kafka-client -- /bin/bash
    * ls
    * cd ../..
    * ls
    * cd kafka-client-storage
    * sh create-topics.sh gke-confluent-kafka-cp-zookeeper-headless
    * exit
    * kubectl apply -f postgres-deployment.yml
    * kubectl get pods | grep postgres
    * (External IP)
      * kubectl get all
      * WEB: kubernetes engine>Services & Ingress
    * kubectl apply -f application-deployment.yml 
    * kubectl get pods | grep deployment
    * kubectl logs POD_NAME
    * kubectl get all
    * 
