package dev.evento.controlplane.operator.example.controller;

import dev.evento.controlplane.operator.example.model.ExampleResource;
import io.fabric8.kubernetes.api.model.apps.DaemonSet;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.Watcher;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.InputStream;
import java.util.function.Predicate;

@ApplicationScoped
public class DaemonSetInstaller {

  @Inject
  private KubernetesClient client;

  @Inject
  private ExampleResourceCache cache;

  void onStartup(@Observes StartupEvent _ev) {
    new Thread(this::runWatch).start();
  }

  private void runWatch() {
    cache.listThenWatch(this::handleEvent);
  }

  private void handleEvent(Watcher.Action action, String uid) {
    try {
      ExampleResource resource = cache.get(uid);
      if (resource == null) {
        return;
      }

      Predicate<DaemonSet> ownerRefMatches = daemonSet -> daemonSet.getMetadata().getOwnerReferences().stream()
          .anyMatch(ownerReference -> ownerReference.getUid().equals(uid));

      if (client
          .apps()
          .daemonSets()
          .list()
          .getItems()
          .stream()
          .noneMatch(ownerRefMatches)) {

        client
            .apps()
            .daemonSets()
            .create(newDaemonSet(resource));
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(-1);
    }
  }

  private DaemonSet newDaemonSet(ExampleResource resource) {
    System.out.println("----- Constructing a new DaemonSet -----");
    InputStream is = getClass().getResourceAsStream("/example.daemonset.yaml");
    System.out.println("Is the resource null? " + (is == null));
    DaemonSet daemonSet = client.apps().daemonSets().load(is).get();
    System.out.println("DaemonSet constructed! " + daemonSet);
    daemonSet.getMetadata().setNamespace(resource.getMetadata().getNamespace());
    daemonSet.getMetadata().getOwnerReferences().get(0).setUid(resource.getMetadata().getUid());
    daemonSet.getMetadata().getOwnerReferences().get(0).setName(resource.getMetadata().getName());
    return daemonSet;
  }
}