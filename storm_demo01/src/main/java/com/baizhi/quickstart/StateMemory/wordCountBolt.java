package com.baizhi.quickstart.StateMemory;

import org.apache.storm.state.KeyValueState;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import java.util.HashMap;
import java.util.Map;
import org.apache.storm.topology.base.BaseStatefulBolt;
import org.apache.storm.tuple.Tuple;

/**
 * 统计单词出现的次数
 * word--->word count
 */
public class wordCountBolt extends BaseStatefulBolt<KeyValueState<String,Long>> {
    private KeyValueState<String, Long> state = null;
    private OutputCollector collector = null;

    /**
     * 执行方法
     *
     * @param input
     */
    @Override
    public void execute(Tuple input) {
        String word = input.getStringByField("word");
        Long num = state.get(word);
        if (num == null) {
            state.put(word, 1L);
        } else {
            state.put(word, num + 1L);
        }
        System.out.println(word+ "\t" + state.get(word) );
        // 应答  标记当前的处理器已经处理完成
        collector.ack(input);
    }

    @Override
    public void prepare(Map<String, Object> topoConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
    }

    /**
     * 初始化状态方法
     *
     * @param state
     */
    @Override
    public void initState(KeyValueState<String, Long> state) {
        this.state = state;
    }
}
