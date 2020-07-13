package com.baizhi.quickstart.redis;

import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

/**
 * 流数据的处理器
 * line-->word
 */
public class LineSplitBolt extends BaseBasicBolt {
    /**
     * 执行方法 注意 无需锚定 无需ack应答
     * @param input
     * @param collector
     */
    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        String line = input.getStringByField("line");
        String[] words = line.split(" ");
        for(String word:words){
            collector.emit(new Values(word));
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word"));
    }
}
