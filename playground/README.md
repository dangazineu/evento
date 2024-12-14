# playground

Dataplane development is currently halted, as testing it without an operator requires too much wiring by hand. 
Current status of the operator development is:
 - operator reacts to changes to Evento clusters. Creates a Kafka cluster and Apicurio Registry for new/modified clusters.
 - currently adding a watcher that reacts to changes to Context resources. This will introduce the need for a hierarchy 
   model between our custom resources, as changes to a cluster affect all contexts within it. This hierarchy should be 
   reflected in the locking/synchronization used to propagate / reconcile resources.
   

## Testing
 - `dev/setup` applies dependencies to a kubernetes cluster (Strimzi, Apicurio), creates a namespace and installs Evento CRDs. 
   Will delete and recreate the namespace if it already exists.
 - `dev/deploy` compiles the operator, uploads it to Dockerhub and applies it to the cluster
 - `dev/watch` tails the logs in the operator. Won't work while the operator pod is coming up. 
 - `dev/install` applies example resources by name to the cluster
 - `dev/clean` removes the operator deployment and all evento.dev custom resources from the cluster. 
   Does not delete the stuff configured by dev/setup. 


