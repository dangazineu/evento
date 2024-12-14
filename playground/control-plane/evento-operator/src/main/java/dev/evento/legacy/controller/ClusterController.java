package dev.evento.legacy.controller;

import dev.evento.legacy.cache.ResourceWatcher;
import dev.evento.legacy.crds.apicurio.ApicurioRegistryResource;
import dev.evento.legacy.crds.evento.cluster.EventoResource;
import dev.evento.legacy.crds.evento.cluster.EventoResourceList;
import dev.evento.legacy.crds.evento.cluster.spec.ExternalMessaging;
import dev.evento.legacy.crds.evento.cluster.spec.ManagedMessaging;
import dev.evento.legacy.crds.evento.cluster.spec.Messaging;
import dev.evento.legacy.operations.ClusterOperations;
import dev.evento.legacy.operations.external.ApicurioRegistryOperations;
import dev.evento.legacy.operations.external.KafkaOperations;
import dev.evento.legacy.crds.strimzi.KafkaResource;
import io.fabric8.kubernetes.client.*;
import io.fabric8.kubernetes.client.dsl.Resource;
import io.fabric8.kubernetes.client.dsl.RollableScalableResource;

import javax.inject.Inject;

import io.quarkus.logging.Log;

//@ApplicationScoped
public class ClusterController {//extends ResourceWatcher<EventoResource, EventoResourceList, Resource<EventoResource>> {
//
//  @Inject private KubernetesClient k8s;
//  @Inject private KafkaOperations kafka;
//  @Inject private ApicurioRegistryOperations apicurio;
//  @Inject private ClusterOperations operations;
//
//  @Override
//  public ClusterOperations getOperations() {
//    return this.operations;
//  }
//
//
//  private final Object LOCK = new Object();
//
//  @Override
//  public void refresh (EventoResource resource) {
//    Log.debugv("refresh called with {0}", resource.getMetadata().getName());
//    String namespace = resource.getMetadata().getNamespace();
//    String uid = resource.getMetadata().getUid();
//    Messaging messaging = resource.getSpec().getMessaging();
//    String kafkaBootstrap;
//    if(messaging instanceof ExternalMessaging) {
//      kafkaBootstrap = ((ExternalMessaging)messaging).getBootstrapServers();
//      Log.debug("No need for a Kafka, this Evento will use externally managed Cluster.");
//      kafka.deleteByOwnerUid(uid);
//    } else {
//      Log.debug("This Evento will use managed Cluster");
//      ManagedMessaging managedMessaging = (ManagedMessaging) messaging;
//
//      KafkaResource kafkaResource = kafka.findOrConstructForOwner(resource);
//      String kafkaBootstrapHost = kafkaResource.getMetadata().getName() + "-kafka-bootstrap";
//      //only customizes nbr of replicas for now, everything else comes from the template
//      kafkaResource.getSpec().getKafka().setReplicas(managedMessaging.getKafka().getReplicas());
//      kafkaResource.getSpec().getZookeeper().setReplicas(managedMessaging.getZookeeper().getReplicas());
//      kafka.get().createOrReplace(kafkaResource);
//
//      Log.debug("This will be LEGEN...");
//      while(true) {
//        Log.debug("wait for it...");
//        synchronized (LOCK) {
//          try {
//            Thread.sleep(1000 * 20);
//          } catch (InterruptedException e) {
//            e.printStackTrace();
//          }
//        }
//        try{
//          RollableScalableResource svc =  k8s.apps().statefulSets().inNamespace(namespace).withName(kafkaResource.getMetadata().getName() + "-kafka");
//          if(svc != null && svc.isReady()) {
//            Log.debug("DARY!! LEGEN-DARY!!!");
//            break;
//          } else {
//            continue;
//          }
//        } catch (Exception e) {
//          Log.debug("not yet... " + e);
//        }
//      }
//      kafkaBootstrap = kafkaBootstrapHost + ":9092";
//      Log.debug("Kafka resource was saved.");
//    }
//
//    ApicurioRegistryResource apicurioRegistryResource = apicurio.findOrConstructForOwner(resource);
//    apicurioRegistryResource.getSpec().getConfiguration().getKafkasql().setBootstrapServers(kafkaBootstrap);
//
//    apicurio.get().createOrReplace(apicurioRegistryResource);
//    Log.debug("apicurio resource was saved.");
//  }
}