package dev.evento.legacy.config;

// TODO extract operation classes to their own config initializer
public class ClientProvider {

//  @Singleton
//  @Named("namespace")
//  String findNamespace() throws IOException {
//    return new String(Files.readAllBytes(Paths.get("/var/run/secrets/kubernetes.io/serviceaccount/namespace")));
//  }
//
//  @Produces
//  @Singleton
//  KubernetesClient kubernetesClient(@Named("namespace") String namespace) {
////    System.out.println("Building default K8S client in namespace " + namespace);
//    System.out.println("YAY!!!!");
//    return new DefaultKubernetesClient().inNamespace(namespace);
//  }

//  @Produces
//  @Singleton
//  ClusterOperations eventoOperation(
//          KubernetesClient defaultClient,
//          @Named("namespace") String namespace,
//          @Named("eventos.evento.dev") CustomResourceDefinition crd
//  ) {
//    return new ClusterOperations(
//            defaultClient
//                    .customResources(
//                            CustomResourceDefinitionContext.fromCrd(crd),
//                            EventoResource.class,
//                            EventoResourceList.class
//                    )
//                    .inNamespace(namespace)
//    );
//  }

//  @Produces
//  @Singleton
//  ContextOperations contextOperation(
//          KubernetesClient defaultClient,
//          @Named("namespace") String namespace,
//          @Named("contexts.evento.dev") CustomResourceDefinition crd
//  ) {
//    return new ContextOperations(
//            defaultClient
//                    .customResources(
//                            CustomResourceDefinitionContext.fromCrd(crd),
//                            ContextResource.class,
//                            ContextResourceList.class
//                    )
//                    .inNamespace(namespace)
//    );
//  }
//
//  @Produces
//  @Singleton
//  KafkaOperations kafkaOperation(
//          KubernetesClient defaultClient,
//          @Named("namespace") String namespace,
//          @Named("kafkas.kafka.strimzi.io") CustomResourceDefinition crd
//  ) {
//    return new KafkaOperations(
//            defaultClient
//                    .customResources(
//                            CustomResourceDefinitionContext.fromCrd(crd),
//                            KafkaResource.class,
//                            KafkaResourceList.class
//                    )
//                    .inNamespace(namespace)
//    );
//  }
//
//  @Produces
//  @Singleton
//  ApicurioRegistryOperations apicurioRegistryOperation(
//          KubernetesClient defaultClient,
//          @Named("namespace") String namespace,
//          @Named("apicurioregistries.registry.apicur.io") CustomResourceDefinition crd
//  ) {
//    return new ApicurioRegistryOperations(
//            defaultClient
//                    .customResources(
//                            CustomResourceDefinitionContext.fromCrd(crd),
//                            ApicurioRegistryResource.class,
//                            ApicurioRegistryResourceList.class
//                    )
//                    .inNamespace(namespace)
//    );
//  }

}
