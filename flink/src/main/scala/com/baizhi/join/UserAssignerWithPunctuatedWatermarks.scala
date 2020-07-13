package com.baizhi.join

import org.apache.flink.streaming.api.functions.AssignerWithPunctuatedWatermarks
import org.apache.flink.streaming.api.watermark.Watermark

class UserAssignerWithPunctuatedWatermarks extends AssignerWithPunctuatedWatermarks[(String,String,Long)]  {
  var maxOrderness = 2000L
  var maxSeenTime = 0L
  override def checkAndGetNextWatermark(LastElament: (String, String, Long), extractedTimestamp: Long): Watermark = {
    new Watermark(maxSeenTime-maxOrderness)
  }

  override def extractTimestamp(element: (String, String, Long), previousElementTimestamp: Long): Long = {
    maxSeenTime=Math.max(element._3,maxSeenTime)
    element._3
  }
}
