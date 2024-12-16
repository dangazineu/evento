package dev.evento.controlplane.watchers;

import dev.evento.controlplane.ManagedResourcesCache;

import javax.inject.Inject;

public abstract class AbstractResourceWatcher {

    @Inject ManagedResourcesCache cache;

    public void watch() {
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

    }
}
