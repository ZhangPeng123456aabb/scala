package com.baizhi.quickstart.guaranteeAtMostOnce;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * 流数据的处理器
 * line-->word
 */
public class LineSplitBolt extends BaseRichBolt {

    private OutputCollector collector = null;
    /**
     * 准备方法
     * @param topoConf
     * @param context
     * @param collector
     */
    @Override
    public void prepare(Map<String, Object> topoConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
    }

    /**
     * 执行方法
     * @param input
     */
    @Override
    public void execute(Tuple input) {
        String line = input.getStringByField("line");
        String[] words = line.split(" ");
        for (String word : words) {
            // 新创建的元组  交给下游的处理器 统计和计算
            //第一个参数：锚定父tuple 维护tuple tree
            collector.emit(input,new Values(word));
        }
        //应答方法 父Tuple 表示当前的处理器将父Tuple处理完成
        collector.ack(input);
    }

    /**
     *
     * @param declarer
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word"));
    }
}
