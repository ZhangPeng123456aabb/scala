package com.baizhi.quickstart.integeration;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.kafka.spout.KafkaSpout;
import org.apache.storm.kafka.spout.KafkaSpoutConfig;
import org.apache.storm.topology.TopologyBuilder;
/**
 * 编织拓扑应用程序
 */
public class WordCountApplication {
    public static void main(String[] args)throws Exception {
        //1. 构建拓扑图
        TopologyBuilder topologyBuilder = new TopologyBuilder();
        //---------------------------------------------------------------------------------------------------------------------
        // 流数据来源于kafka
        KafkaSpoutConfig<String, String> kafkaSpoutConfig = KafkaSpoutConfig.builder("HadoopNode01:9092,HadoopNode02:9092,HadoopNode03:9092", "t11")
                .setEmitNullTuples(false) // 不发送为null的空元组
                .setMaxUncommittedOffsets(20)  // 设置kafka消费者最大允许未提交的偏移量
                .setProcessingGuarantee(KafkaSpoutConfig.ProcessingGuarantee.AT_LEAST_ONCE)
                .setProp(ConsumerConfig.GROUP_ID_CONFIG, "g1")
                .setProp(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class)
                .setProp(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class).build();
        // Tuple("topic", "partition", "offset", "key", "value".)

        topologyBuilder.setSpout("kafkaSpout", new KafkaSpout<String, String>(kafkaSpoutConfig));
        //---------------------------------------------------------------------------------------------------------------------
        // kafkaSpout --> b1
        topologyBuilder.setBolt("b1",new LineSplitBolt(),2).shuffleGrouping("kafkaSpout");

        LocalCluster localCluster = new LocalCluster();
        Config config = new Config();
        config.setNumWorkers(2);
        config.setDebug(false);
        localCluster.submitTopology("wordcount",config,topologyBuilder.createTopology());
    }
}
