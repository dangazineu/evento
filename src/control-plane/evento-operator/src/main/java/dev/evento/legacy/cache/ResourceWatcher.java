package dev.evento.legacy.cache;

import dev.evento.legacy.operations.ResourceOperations;
import io.fabric8.kubernetes.api.model.KubernetesResourceList;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.client.Watcher;
import io.fabric8.kubernetes.client.WatcherException;
import io.fabric8.kubernetes.client.dsl.Resource;
import io.quarkus.runtime.StartupEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public abstract class ResourceWatcher<
        T extends CustomResource,
        L extends KubernetesResourceList<T>,
        R extends Resource<T>
        > {

//  private static final Logger LOG = LoggerFactory.getLogger(ResourceWatcher.class);
//
//  private static final Executor executor = Executors.newFixedThreadPool(10);
//  private final Executor watcher = Executors.newSingleThreadExecutor();
//
////  @Inject
//  private ManagedResourcesCache cache;
//
//  public abstract ResourceOperations<T, L, R> getOperations();
//
//  // will leave this here for simplicity, but if we want to disentangle core framework logic from quarkus,
//  // this should move to the config package
//  public void onStartup(@Observes StartupEvent _ev) {
//    watcher.execute(this::listThenWatch);
//  }
//
//  private void listThenWatch() {
//    // list
//    getOperations()
//            .list()
//            .getItems()
//            .forEach(resource -> {
//                      executor.execute(() -> {
//                        cache.put(resource, this::refresh);
//                      });
//                    }
//            );
//
//    // watch
//    getOperations().watch(new Watcher<T>() {
//      @Override
//      public void eventReceived(Action action, T resource) {
//        //all watcher instances will share this same
//        executor.execute(() -> {
//
//          LOG.debug("received " + action + " for resource " + resource);
//          if (action == Action.ADDED || action == Action.MODIFIED) {
//            cache.put(resource, ResourceWatcher.this::refresh);
//          } else if (action == Action.DELETED) {
//            resourceDeleted(resource);
//          } else {
//            resourceError(resource);
//          }
//
//        });
//      }
//
//      @Override
//      public void onClose(WatcherException e) {
//        e.printStackTrace();
//        System.exit(-1);
//      }
//    });
//
//  }
//
//  /**
//   * Called whenever a new resource is ADDED to the cluster.
//   * @param resource
//   */
//  public void refresh (T resource) {
//    LOG.info("Resource ADDED " + resource);
//  }
//
//  /**
//   * Called whenever an existing resource is DELETED.
//   * @param resource
//   */
//  public void resourceDeleted(T resource) {
//    LOG.info("Resource DELETED " + resource);
//  }
//
//  /**
//   * Called whenever an ERROR event is received for a given resource.
//   * @param resource
//   */
//  public void resourceError(T resource) {
//    LOG.info("Resource ERROR " + resource);
//    System.exit(-1);
//  }

}