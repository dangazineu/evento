# process-manager project

## Running the application in dev mode
```
mvn quarkus:dev -pl data-plane/process-manager
```

## Pushing container to Docker Hub
First you need `DOCKER_USER` and `DOCKER_PWD` environment variables configured. 

```
mvn clean package -Dquarkus.container-image.push=true
``` 

## Deploying container to Kubernetes
Replace `imagePullPolicy: IfNotPresent` with `imagePullPolicy: Always` in 
`data-plane/process-manager/target/kubernetes/kubernetes.yml`. Then run the following commands:
```
kubectl apply -f data-plane/process-manager/target/kubernetes/kubernetes.json -n evento
kubectl apply -f data-plane/process-manager/target/kubernetes/kubernetes.yml -n evento
```

