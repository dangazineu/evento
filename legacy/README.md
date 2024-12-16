# Legacy Implementation
This folder contains the legacy implementation of Evento, based on Java with Quarkus. My goal is to migrate the operator 
and CLI logic to Go, to reduce verbosity. The Java implementation will be kept as a reference for the time being.

## Structure
 - `control-plane` contains the operator, which reacts to resources within the evento.dev namespace in the K8S cluster
 - `data-plane` contains the components that are defined by the operator, to handle commands and events for each specific entity type  
 - `dev` contains scripts to simplify deployment of the operator and example resources to a K8S cluster  

## Testing
 - `dev/setup` applies dependencies to a kubernetes cluster (Strimzi, Apicurio), creates a namespace and installs Evento CRDs. 
   Will delete and recreate the namespace if it already exists.
 - `dev/deploy` compiles the operator, uploads it to Dockerhub and applies it to the cluster
 - `dev/watch` tails the logs in the operator. Won't work while the operator pod is coming up. 
 - `dev/install` applies example resources by name to the cluster
 - `dev/clean` removes the operator deployment and all evento.dev custom resources from the cluster. 
   Does not delete the stuff configured by dev/setup. 


