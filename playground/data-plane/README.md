## Starting Kafka cluster
Before deploying this application to a Kubernetes cluster, start a Kafka cluster following these steps:
```shell script
kubectl create namespace evento
kubectl apply -f 'https://strimzi.io/install/latest?namespace=evento' -n evento
#kubectl apply -f https://strimzi.io/examples/latest/kafka/kafka-persistent-single.yaml -n evento
kubectl apply -f kafka.yaml -n evento
kubectl wait kafka/my-cluster --for=condition=Ready --timeout=300s -n evento
# --broker-list my-cluster-kafka-bootstrap:9092
```
These instructions were copied from https://strimzi.io/quickstarts/. Refer to that page for updated material.
### Testing Kafka Config
This command will bring up the example Kafka producer script within a pod inside Kubernetes.
```shell script
kubectl -n evento run kafka-producer -ti --image=strimzi/kafka:0.19.0-kafka-2.5.0 --rm=true --restart=Never -- bin/kafka-console-producer.sh --broker-list my-cluster-kafka-bootstrap:9092 --topic my-topic
```
This command will bring up the example Kafka consumer script within a pod inside Kubernetes.
```shell script
kubectl -n evento run kafka-consumer -ti --image=strimzi/kafka:0.19.0-kafka-2.5.0 --rm=true --restart=Never -- bin/kafka-console-consumer.sh --bootstrap-server my-cluster-kafka-bootstrap:9092 --topic my-topic --from-beginning
```
### Accessing Kafka from outside of Kubernetes
These are instructions for how to expose Kafka brokers outside of Kubernetes https://strimzi.io/blog/2019/04/23/accessing-kafka-part-2/
#### Minikube
If kubectl is pointing to a minikube instance, use the following command to get the IP and port of the bootstrap broker:
```shell script
# if running on minikube, this command will print the IP and port for the bootstrap service
# ignore the http portion
minikube service my-cluster-kafka-external-bootstrap --url -n evento
```
#### Kubernetes
If kubectl is pointing to a real Kubernetes cluster, use the following commands instead:
```shell script
kubectl get node
# substitute node-name with one of the nodes printed in the previous command
kubectl get node node-name -o=jsonpath='{range .status.addresses[*]}{.type}{"\t"}{.address}{"\n"}'
# prints the external port of the bootstrap service
kubectl get service my-cluster-kafka-external-bootstrap -o=jsonpath='{.spec.ports[0].nodePort}{"\n"}' -n evento
```
### Testing from outside of Kubernetes
Run this from a Kafka distro folder. (get latest from https://kafka.apache.org/downloads)
```shell script
bin/kafka-console-producer.sh --broker-list <node-address>:<node-port> --topic my-topic
```
### Export KAFKA_HOST and KAFKA_PORT
Applications within this module rely on environment variables KAFKA_HOST and KAFKA_PORT to connect to kafka.
