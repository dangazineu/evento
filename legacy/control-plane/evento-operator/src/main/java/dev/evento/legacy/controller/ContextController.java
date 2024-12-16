package dev.evento.legacy.controller;

import dev.evento.legacy.cache.ResourceWatcher;
import dev.evento.legacy.crds.evento.context.ContextResource;
import dev.evento.legacy.crds.evento.context.ContextResourceList;
import dev.evento.legacy.operations.ContextOperations;
import io.fabric8.kubernetes.client.dsl.Resource;
import io.quarkus.logging.Log;

import javax.inject.Inject;

//@ApplicationScoped
public class ContextController {//extends ResourceWatcher<ContextResource, ContextResourceList, Resource<ContextResource>> {

//  @Inject private ContextOperations operations;
//
//  @Override
//  public ContextOperations getOperations() {
//    return this.operations;
//  }
//
//  @Override
//  public void refresh (ContextResource resource) {
//    Log.warnv("Something happened to {0}", resource.getMetadata().getName());
//  }
}