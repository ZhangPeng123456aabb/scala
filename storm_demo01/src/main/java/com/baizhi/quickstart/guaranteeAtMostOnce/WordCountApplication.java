package com.baizhi.quickstart.guaranteeAtMostOnce;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

/**
 * 编织拓扑应用程序
 */
public class WordCountApplication {
    public static void main(String[] args)throws Exception {
        //构建topology的DAG（有向无环图）
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("s1",new LineSpout());
        //参数三：设置计算单元并行度 默认1
        builder.setBolt("b1",new LineSplitBolt(),2)
        //将spout生产的元组tuple均等分配给b1元组件多个并行的任务
        .shuffleGrouping("s1");
        builder.setBolt("b2",new wordCountBolt(),3)
                //将指定的field相同的的数据归为一组
        .fieldsGrouping("b1",new Fields("word"));
        //2.将topology提交运行
        //参数1：拓扑任务名
        Config config = new Config();
        //当前拓扑运行时 需要占用两个槽位(2个worker进程)
        config.setNumWorkers(2);
        //Storm可靠性处理相关
        config.setNumAckers(0);
        LocalCluster localCluster = new LocalCluster();
        //localCluster.submitTopology("storm-wordcount",config,builder.createTopology());
        StormSubmitter.submitTopology("storm-wordcount",config,builder.createTopology());
    }
}
