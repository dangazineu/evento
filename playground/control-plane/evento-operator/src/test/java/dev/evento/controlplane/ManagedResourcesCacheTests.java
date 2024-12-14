package dev.evento.controlplane;


import dev.evento.legacy.crds.DSL;
import dev.evento.legacy.crds.evento.cluster.EventoResource;
import dev.evento.legacy.crds.evento.context.ContextResource;
import static org.junit.jupiter.api.Assertions.*;

import io.fabric8.kubernetes.client.CustomResource;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class ManagedResourcesCacheTests {

//    @Test
    public void hello_world () throws InterruptedException {
        ManagedResourcesCache cache = new ManagedResourcesCache();
        EventoResource cluster = cluster("cluster");
        ContextResource cxtFoo = context("foo", cluster);
        ContextResource ctxBar = context("bar", cluster);
        ContextResource cxtAnotherFoo = context("foo", cluster);

        final Semaphore ctxFooTest = new Semaphore(0);
        final Semaphore ctxBarTest = new Semaphore(0);
        final Semaphore clusterTest = new Semaphore(0);

        final Semaphore ctxFooConsumer = new Semaphore(0);
        final Semaphore ctxBarConsumer = new Semaphore(0);
        final Semaphore clusterConsumer = new Semaphore(0);

        final AtomicBoolean ctxFooResult = new AtomicBoolean(false);
        final AtomicBoolean ctxBarResult = new AtomicBoolean(false);
        final AtomicBoolean clusterResult = new AtomicBoolean(false);

        final AtomicInteger ctxFooCounter = new AtomicInteger(0);
        final AtomicInteger ctxBarCounter = new AtomicInteger(0);
        final AtomicInteger clusterCounter = new AtomicInteger(0);

        List<Semaphore> semaphores = Arrays.asList(
                ctxFooTest, ctxBarTest, clusterTest,
                ctxFooConsumer, ctxBarConsumer, clusterConsumer
        );

        cache.put(cxtFoo, consumer(cxtFoo, ctxFooTest, ctxFooConsumer, ctxFooCounter, ctxFooResult));
        cache.put(cluster, consumer(cluster, clusterTest, clusterConsumer, clusterCounter, clusterResult));

        //if can acquire the lock, it means the consumer started execution
        assertTrue(ctxFooTest.tryAcquire(1, TimeUnit.SECONDS));
        assertEquals(0, clusterTest.availablePermits());

        drain(semaphores);
        ctxFooConsumer.release();

        assertTrue(ctxFooTest.tryAcquire(1, TimeUnit.SECONDS));
        assertTrue(ctxFooResult.getAndSet(false));
        assertEquals(0, clusterTest.availablePermits());

        drain(semaphores);
        ctxFooConsumer.release();

        assertTrue(clusterTest.tryAcquire(1, TimeUnit.SECONDS));

        cache.put(ctxBar, consumer(ctxBar, ctxBarTest, ctxBarConsumer, ctxBarCounter, ctxBarResult));
        cache.put(cxtAnotherFoo, consumer(cxtAnotherFoo, ctxFooTest, ctxFooConsumer, ctxFooCounter, ctxFooResult));

        assertEquals(0, ctxBarTest.availablePermits());
        assertEquals(0, ctxFooTest.availablePermits());

//        assertTrue(ctxFooResult.getAndSet(false));
//        assertEquals(0, clusterTest.availablePermits());




//        assertFalse(ctxFooLock.isLocked());
//        assertTrue(ctxBarLock.isLocked());
//        assertFalse(clusterLock.isLocked());
//        assertEquals(1, ctxFooLatch.getCount());
//        assertEquals(0, ctxFooCounter.get());

    }

    private static class ResourceState<T extends CustomResource> {
        T resource;
        Semaphore test = new Semaphore(0);;
        Semaphore consumer = new Semaphore(0);;
        AtomicInteger counter = new AtomicInteger(0);
        AtomicBoolean result = new AtomicBoolean(false);

    }

    private <T extends CustomResource> Consumer<T> consumer (
            T t,
            Semaphore test,
            Semaphore consumer,
            AtomicInteger counter,
            AtomicBoolean result)
    {
        return (r) -> {
            System.out.println("Started for " + t.getMetadata().getName());
            test.release();
            System.out.println("Waiting for " + t.getMetadata().getName());
            consumer.acquireUninterruptibly();
            System.out.println("Released for " + t.getMetadata().getName());

            counter.incrementAndGet();
            result.set(r == t);

            System.out.println("Started for " + t.getMetadata().getName());
            test.release();
            System.out.println("Waiting for " + t.getMetadata().getName());
            consumer.acquireUninterruptibly();
            System.out.println("Released for " + t.getMetadata().getName());
        };
    }


    public static EventoResource cluster(String name) {
        EventoResource evento = new EventoResource();
        evento.getMetadata().setName(name);
        return evento;
    }

    public static ContextResource context(String name, EventoResource cluster) {
        ContextResource ctx = DSL.establishDependency(new ContextResource(), cluster);
        ctx.getMetadata().setName(name);
        return ctx;
    }

    private void drain (List<Semaphore> s) {
        s.stream().forEach(Semaphore::drainPermits);
    }
}