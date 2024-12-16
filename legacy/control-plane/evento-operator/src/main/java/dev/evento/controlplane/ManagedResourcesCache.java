package dev.evento.controlplane;

import io.fabric8.kubernetes.client.CustomResource;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@ApplicationScoped
public class ManagedResourcesCache {

    private final ConcurrentMap<String, ResourceHolder<?>> cache = new ConcurrentHashMap<>();
    private static final Executor executor = Executors.newCachedThreadPool();

    public <T extends CustomResource> void put(T resource, Consumer<T> consumer) {
        String type = "evento.dev/" + resource.getSingular();
        String name = resource.getMetadata().getName();

        Set<ResourceHolder<?>> dependants = cache.values().stream()
                .filter(h->h.dependsOn(type, name))
                .collect(Collectors.toSet());

        ResourceHolder<T> holder = (ResourceHolder<T>) cache.computeIfAbsent(
                type+name,
                (k)-> new ResourceHolder<T>(resource, consumer, dependants)
        );

        executor.execute(() -> {
            holder.lock();
            consumer.accept(resource);
            dependants.forEach(ResourceHolder::refresh);
            holder.unlock();
        });
    }

    private static class ResourceHolder<T extends CustomResource> {
        private T resource;
        private Consumer<T> consumer;

        private ReentrantLock lock = new ReentrantLock(true);
        private Set<ResourceHolder<?>> dependants = new HashSet<>();

        public ResourceHolder(T resource, Consumer<T> consumer, Set<ResourceHolder<?>> dependants){
            this.resource = resource;
            this.dependants.addAll(dependants);
        }

        public void lock () {
            this.lock.lock();
            dependants.stream().forEach(ResourceHolder::lock);
        }

        public void unlock () {
            dependants.stream().forEach(ResourceHolder::unlock);
            this.lock.unlock();
        }

        public void refresh () {
            this.consumer.accept(this.resource);
        }

        public boolean dependsOn (String type, String name) {
            if(this.resource.getMetadata().getLabels() != null) {
                if(this.resource.getMetadata().getLabels().containsKey(type)) {
                    return this.resource.getMetadata().getLabels().get(type).equals(name);
                }
            }
            return false;
        }
    }
}
