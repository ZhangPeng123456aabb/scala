package com.baizhi.evictor;

import org.apache.flink.streaming.api.windowing.evictors.Evictor;
import org.apache.flink.streaming.api.windowing.windows.Window;
import org.apache.flink.streaming.runtime.operators.windowing.TimestampedValue;

import java.util.Iterator;

public class UserDefineErrorEvictor<W extends  Window> implements Evictor<String, W> {
    private  boolean isEvictorBefore;
    private  String  content;

    public UserDefineErrorEvictor(boolean isEvictorBefore, String content) {
        this.isEvictorBefore = isEvictorBefore;
        this.content=content;
    }

    public void evictBefore(Iterable<TimestampedValue<String>> elements, int size, W window, EvictorContext evictorContext) {
        if(isEvictorBefore){
            evict(elements,  size,  window,  evictorContext);
        }
    }

    public void evictAfter(Iterable<TimestampedValue<String>> elements, int size, W window, EvictorContext evictorContext) {
        if(!isEvictorBefore){
            evict(elements,  size,  window,  evictorContext);
        }
    }
    private  void evict(Iterable<TimestampedValue<String>> elements, int size, W window, EvictorContext evictorContext) {
        Iterator<TimestampedValue<String>> iterator = elements.iterator();
        while(iterator.hasNext()){
            TimestampedValue<String> next = iterator.next();
            String value = next.getValue();
            if(value.contains(content)){
                iterator.remove();
            }
        }
    }
}
