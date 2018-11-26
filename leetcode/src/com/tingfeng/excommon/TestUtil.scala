package com.tingfeng.excommon

import java.util.concurrent.atomic.AtomicInteger
import java.util.function.Function

object TestUtil {
  /**
    *
    * @param thread             启动的线程数量
    * @param cycleCountInThread 线程内的循环次数
    * @param function           需要执行的业务逻辑,传入cycleCountInThread中每次循环的下标，从0到(cycleCountInThread-1)的
    */
  def printTime(thread: Int, cycleCountInThread: Int, function: (Int) => Unit): Unit = {
    val startTime = System.currentTimeMillis
    val value = new AtomicInteger(0)
    for(i <- 0 until thread){
      new Thread(() => {
            try{
              for(j <- 0  until cycleCountInThread){
                function(j)
              }
            } catch {
              case e: Exception =>
                throw new RuntimeException(e)
            }finally {
              value.incrementAndGet
            }
      }).start()
    }
    val total = thread * cycleCountInThread
    while (value.get < total ) {
      try{
        Thread.sleep(2)
      } catch {
        case e: InterruptedException =>
          e.printStackTrace()
      }
    }
    System.out.println("use time:" + (System.currentTimeMillis - startTime))
  }
}
