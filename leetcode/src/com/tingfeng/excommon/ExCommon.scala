package com.tingfeng

import com.tingfeng.excommon.ListNode

object ExCommon {
  implicit def toListNode(array : Array[Int]):ListNode = {
    if(array.length <= 0){
      null
    }else{
      val nodes = array.map(it => new ListNode(it))
      for(i <- 0 until array.length){
        if(i > 0){
          nodes(i - 1).next = nodes(i)
        }
      }
      nodes(0)
    }
  }
}
