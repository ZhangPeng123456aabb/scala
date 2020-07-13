package com.baizhi.quickstart.guaranteeAtMostOnce.Auto;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.util.HashMap;
import java.util.Map;

/**
 * 统计单词出现的次数
 * word--->word count
 */
public class wordCountBolt extends BaseBasicBolt {
    private HashMap<String,Long> wordCount = new HashMap();
    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        String word = input.getStringByField("word");
        Long num = wordCount.getOrDefault(word, 0L);
        num++;
        System.out.println(word+"\t"+num);
        wordCount.put(word,num);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}
