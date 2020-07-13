package com.baizhi.quickstart.hbase;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;

import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * 负责生产一行行的英文短语
 */
public class LineSpout extends BaseRichSpout {
    // 模拟数据 英文短语
    private String[] lines = null;

    private SpoutOutputCollector collector = null;
    /**
     * 初始化方法
     * @param conf
     * @param context
     * @param collector
     */
    @Override
    public void open(Map<String, Object> conf, TopologyContext context, SpoutOutputCollector collector) {
        lines = new String[]{"Hello Storm","Hello Kafka","Hello Spark"};
        this.collector = collector;
    }

    /**
     * 创建元组方法
     */
    @Override
    public void nextTuple() {
        // 每隔5秒 新产生一个tuple 并且将它发送给下游的处理器
        Utils.sleep(5000);
        // 随机数：0 - 2
        int num = new Random().nextInt(lines.length);
        // 随机获取一行英文短语
        String line = lines[num];
        // 将随机产生的数据封装为1个Tuple元组
        // 将封装好的tuple发送给下游的处理器bolt
        //指定Tuple的msgId
        String msgId = UUID.randomUUID().toString();
        System.out.println("send tuple magId"+msgId);
        collector.emit(new Values(line), msgId);
    }

    /**
     * tuple完整处理的回调方法
     * @param msgId 正确处理的tuple msgID
     */
    @Override
    public void ack(Object msgId) {
        System.out.println("正确处理成功"+msgId);
    }

    @Override
    public void fail(Object msgId) {
        System.out.println("处理失败"+msgId);
    }

    /**
     * 说明输出元组信息
     * @param declarer
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("line"));
    }
}
