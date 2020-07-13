package com.baizhi.quickstart.guaranteeAtMostOnce;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.util.HashMap;
import java.util.Map;

/**
 * 统计单词出现的次数
 * word--->word count
 */
public class wordCountBolt extends BaseRichBolt {
    private HashMap<String, Long> wordCount = null;
    private OutputCollector collector=null;
    @Override
    public void prepare(Map<String, Object> topoConf, TopologyContext context, OutputCollector collector) {
        wordCount = new HashMap<String, Long>();
        this.collector=collector;
    }

    /**
     * 单词计数案例：
     *      wordcountBolt最终的处理器
     * @param input
     */
    @Override
    public void execute(Tuple input) {
        String word = input.getStringByField("word");
        // 累计单词出现次数（计算状态）
        Long num = wordCount.getOrDefault(word, 0L);
        num++;
        wordCount.put(word, num);
        System.out.println(word + "\t" + num);
        //应答父Tuple 无需锚定 原因：当前的Tuple没有产生新的tuple
        //collector.ack(input);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }
}
