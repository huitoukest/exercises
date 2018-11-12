package com.tingfeng.exercise

import java.util.concurrent.ThreadLocalRandom

import com.tingfeng.ExCommon
import com.tingfeng.excommon.ListNode
/**
  *
  *  k个一组翻转链表
  *  给出一个链表，每 k 个节点一组进行翻转，并返回翻转后的链表。
  *  k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么将最后剩余节点保持原有顺序。
  *  示例 :
  *  给定这个链表：1->2->3->4->5
  *  当 k = 2 时，应当返回: 2->1->4->3->5
  *  当 k = 3 时，应当返回: 3->2->1->4->5
  *  说明 :
  *  你的算法只能使用常数的额外空间。
  *  你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
  */
object Ex025 {

  def main(args: Array[String]): Unit = {
    val tmp:Array[Int]  = new Array(10)
    for(i <- 0 until(tmp.length)){
      tmp(i) = ThreadLocalRandom.current().nextInt(1000)
    }
    ExCommon.printListNode(tmp)
    //ExCommon.printListNode(reverseOne(tmp)._1)
    val result = reverseKGroup(tmp,4)
    ExCommon.printListNode(result)
  }

  def reverseKGroup(head: ListNode, k: Int): ListNode = {
    def reverseK(tmpHead: ListNode, maxCount: Int): ListNode = {
      if (maxCount <= 0 || tmpHead == null) {
        tmpHead
      } else {
        var tmpNode = tmpHead
        var count = 1
        while (count < maxCount && tmpNode.next != null) {
          tmpNode = tmpNode.next
          count += 1
        }
        if(tmpNode == null || tmpNode.next == null && count < maxCount) {
          tmpHead
        } else {
          val next = tmpNode.next
          tmpNode.next = null
          val re = reverseOne(tmpHead)
          re._2.next = reverseK(next, maxCount)
          re._1
        }
      }
    }
    reverseK(head, k)
  }

  /**
    * @param node
    * @return (头节点，尾节点)
    */
  def reverseOne(node:ListNode): Tuple2[ListNode,ListNode] ={
    node match {
      case null => null
      case _ if node.next == null => (node,node)
      case _  => {
        val re = reverseOne(node.next)
        re._2.next = node
        node.next = null
        (re._1,node)
      }
    }
  }
}
