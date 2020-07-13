package com.baizhi.eventtime

import java.text.SimpleDateFormat

import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks
import org.apache.flink.streaming.api.watermark.Watermark

class UserDefineAssignerWithPeriodcWatermarks extends AssignerWithPeriodicWatermarks[(String,Long)] {
  var maxOrderness = 2000L
  var maxSeenTime = 0L
  var sdf = new SimpleDateFormat("HH:mm:ss")
  override def getCurrentWatermark: Watermark = {
    new Watermark(maxSeenTime-maxOrderness)
  }

  override def extractTimestamp(element: (String, Long), previousElementTimestamp: Long): Long = {
    maxSeenTime=Math.max(element._2,maxSeenTime)
    element._2
  }
}
