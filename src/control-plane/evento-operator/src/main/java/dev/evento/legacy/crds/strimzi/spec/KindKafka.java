package dev.evento.legacy.crds.strimzi.spec;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.quarkus.runtime.annotations.RegisterForReflection;

@JsonDeserialize
@RegisterForReflection
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class KindKafka {

    private KafkaCluster kafka;
    private ZookeeperCluster zookeeper;

    public KafkaCluster getKafka() {
        return kafka;
    }

    public void setKafka(KafkaCluster kafka) {
        this.kafka = kafka;
    }

    public ZookeeperCluster getZookeeper() {
        return zookeeper;
    }

    public void setZookeeper(ZookeeperCluster zookeeper) {
        this.zookeeper = zookeeper;
    }

    //    private TopicOperatorSpec topicOperator;
//    private EntityOperatorSpec entityOperator;
//    private CertificateAuthority clusterCa;
//    private JmxTransSpec jmxTrans;
//    private KafkaExporterSpec kafkaExporter;
//    private CruiseControlSpec cruiseControl;
//
//    private CertificateAuthority clientsCa;
//    private List<String> maintenanceTimeWindows;
}