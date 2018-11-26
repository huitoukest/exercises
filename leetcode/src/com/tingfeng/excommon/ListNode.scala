package com.tingfeng.excommon

object ListNode{
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

class ListNode(var _x: Int = 0) {
  var next: ListNode = null
  var x: Int = _x
}
