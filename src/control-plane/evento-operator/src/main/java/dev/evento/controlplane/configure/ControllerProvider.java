package dev.evento.controlplane.configure;

import dev.evento.controlplane.watchers.AbstractResourceWatcher;
import dev.evento.controlplane.watchers.ClusterWatcher;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ControllerProvider {

    @Inject Instance<AbstractResourceWatcher> resourceWatchers;

    public void onStartup(@Observes StartupEvent startupEvent) {
        ExecutorService watcherService = Executors.newFixedThreadPool((int) resourceWatchers.stream().count());
        resourceWatchers.stream().forEach(watcher -> watcherService.submit(watcher::watch));
        watcherService.shutdown();
    }
}
