package com.tingfeng.exercise

import com.tingfeng.excommon.ListNode
import com.tingfeng.exercise.Ex020.isValid

/**
  * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
  * 归并的合并方法
  * 示例：
  *
  * 输入：1->2->4, 1->3->4
  * 输出：1->1->2->3->4->4
  */
object Ex021 {

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

    def main(args: Array[String]): Unit = {
      val a = Array[Int]()
      val b = Array(0)
      val c = mergeTwoLists(a,b)
      var next = c
      while(next != null){
         print(next.x + ",")
         next = next.next
      }
      println()
    }

    def mergeTwoLists(l1: ListNode, l2: ListNode): ListNode = {
        var head:ListNode = new ListNode() //虚拟一个null头
        if(l1 == null){
           head.next = l2
        }else if(l2 == null){
           head.next = l1
        }else{
            var a = l1
            var b = l2
            var result = head
            while(a != null && b != null){
                 if(a.x > b.x){
                   result.next = b
                   b = b.next
                 }else{
                   result.next = a
                   a = a.next
                 }
                result = result.next
            }
            if(a == null && b != null){
                  result.next = b
            }else if(b == null && a != null){
                  result.next = a
            }
        }
      if(head != null) head.next else null
    }
}
