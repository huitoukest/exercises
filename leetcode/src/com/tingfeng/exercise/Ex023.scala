package com.tingfeng.exercise

import java.util.concurrent.ThreadLocalRandom

import com.tingfeng.excommon.{ListNode, TestUtil}

import scala.util.Sorting

/**
  *
  * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
    *示例:
    *输入:
    *[
    *1->4->5,
    *1->3->4,
    *2->6
    *]
    *输出: 1->1->2->3->4->4->5->6
 */
object Ex023 {

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

    def printMergeTime(list :Array[ListNode],merge: (Array[ListNode]) => ListNode)={
        TestUtil.printTime(1,1,index => {
            val c = merge(list)
            if(index == 0){
                var next = c
                var i = 0
                while(next != null && i < 100){
                    print(next.x + ",")
                    next = next.next
                    i += 1
                }
                println()
            }
        })
    }

    def getNewList(length:Int , perSize :Int):Array[ListNode]={
        val list = new Array[Array[Int]](length).map(it =>{
            val tmp:Array[Int]  = new Array(perSize)
            for(i <- 0 until(tmp.length)){
                tmp(i) = ThreadLocalRandom.current().nextInt(100000)
            }
            Sorting.quickSort(tmp)
            tmp
        }).map(it => toListNode(it))
        list
    }

    def main(args: Array[String]): Unit = {
        val a = Array[Int](1,4,5)
        val b = Array(1,3,5)
        //val list:Array[ListNode] = Array(toListNode(a),toListNode(b),toListNode(b),toListNode(Array(1,2,6)))
        //val c = mergeKLists(list)
        var list = getNewList(1000,1000)
        printMergeTime(list,mergeKLists)
        list = getNewList(1000,1000)
        printMergeTime(list,mergeKLists2)
    }

    /**
      * 方法1，直接归并合并
      */
    def mergeKLists(lists: Array[ListNode]): ListNode = {
        var result:ListNode = null
        if(lists.length > 0){
            result = lists(0)
        }
        for(i <- 1 until lists.length){
            result = mergeTwoLists(result,lists(i))
        }
        result
    }

    /**
      * 方法2，全部采用归并的方式继续归并合并，归并合并的效率更高
      * @param lists
      * @return
      */
    def mergeKLists2(lists: Array[ListNode]): ListNode = {
        def merge(left:Int , right:Int): ListNode = {
            //println(s"left: $left  right: $right")
            if(right >= lists.length ){
                null
            }else if(left == right) {
               lists(left)
            }else if(right == left + 1){
                mergeTwoLists(lists(left),lists(right))
            }else{
                val mid = (right + left) >> 1
                mergeTwoLists(merge(left,mid),merge(mid + 1,right))
            }
        }
        var result:ListNode = null
        if(lists.length <= 0){
            result = null
        }else if(lists.length == 1){
            result = lists(0)
        }else{
            result = merge(0,lists.length - 1)
        }
        result
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