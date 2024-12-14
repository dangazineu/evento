package dev.evento.controlplane.crds.v1alpha1;

import dev.evento.controlplane.crds.Evento;
import io.fabric8.kubernetes.api.model.Namespaced;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Version;

@Group(Evento.GROUP)
@Version(Evento.VERSIONS.v1alpha1)
public class Cluster extends CustomResource<ClusterSpec, ClusterStatus> implements Namespaced { }
