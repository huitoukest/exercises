package com.tingfeng

import com.tingfeng.excommon.ListNode

object ExCommon {

  def printListNode(c :ListNode)={
    var next = c
    var i = 0
    while(next != null && i < 100){
      print(next.x + ",")
      next = next.next
      i += 1
    }
    println()
  }
}
