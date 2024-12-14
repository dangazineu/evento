package dev.evento.legacy.config;

public class CustomResourceDefinitionLoader {

//    @Named("eventos.evento.dev")
//    @Singleton
//    public CustomResourceDefinition eventos (KubernetesClient client) {
//        KubernetesDeserializer.registerCustomKind("evento.dev/v1alpha1", "Evento", EventoResource.class);
//        return findByName(client, "eventos.evento.dev");
//    }

//    @Named("contexts.evento.dev")
//    @Singleton
//    public CustomResourceDefinition contexts (KubernetesClient client) {
//        KubernetesDeserializer.registerCustomKind("evento.dev/v1alpha1", "Context", ContextResource.class);
//        return findByName(client, "contexts.evento.dev");
//    }
//
//    @Named("kafkas.kafka.strimzi.io")
//    @Singleton
//    public CustomResourceDefinition kafkas (KubernetesClient client) {
//        KubernetesDeserializer.registerCustomKind("kafka.strimzi.io/v1beta2", "Kafka", KafkaResource.class);
//        return findByName(client, "kafkas.kafka.strimzi.io");
//    }
//
//
//    @Named("apicurioregistries.registry.apicur.io")
//    @Singleton
//    public CustomResourceDefinition apicurioregistries (KubernetesClient client) {
//        KubernetesDeserializer.registerCustomKind("apicur.io/v1", "ApicurioRegistry", ApicurioRegistryResource.class);
//        return findByName(client, "apicurioregistries.registry.apicur.io");
//    }
//    private CustomResourceDefinition findByName (KubernetesClient client, String name) {
//        return client
//                .apiextensions().v1()
//                .customResourceDefinitions()
//                .list()
//                .getItems()
//                .stream()
////                .peek(d-> LOG.debug(""))
//                .filter(d -> name.equals(d.getMetadata().getName()))
//                .findAny()
//                .orElseThrow(
//                        () -> new RuntimeException("Deployment error: Could not find required CRD: " + name)
//                );
//    }
}
