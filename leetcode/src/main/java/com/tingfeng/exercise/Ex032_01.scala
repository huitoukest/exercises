package com.tingfeng.exercise

import com.tingfeng.excommon.TestUtil

import scala.collection.mutable
import scala.util.control.Breaks

/**
  * 32. 最长有效括号
  * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
  *
  * 示例 1:
  *
  * 输入: "(()"
  * 输出: 2
  * 解释: 最长有效括号子串为 "()"
  * 示例 2:
  *
  * 输入: ")()())"
  * 输出: 4
  * 解释: 最长有效括号子串为 "()()"
  *
  * 思考：1.利用栈来记录索引，并将匹配的索引弹出，留下不匹配的索引。
  * 2.记录上一次的不匹配的位置，两次不陪配的位置之差就是匹配的最大长度
  */
object Ex032_01 {

  def main(args: Array[String]): Unit = {
      val str = "))(()()))"
      println(longestValidParentheses(str))
     test()
  }

  def test(): Unit ={
    val exceptValue = Array[Tuple2[String,Int]](Tuple2("",0),Tuple2("(((",0),Tuple2(")(((",0),Tuple2("()()",4),
      Tuple2("())",2),Tuple2("))(()()))",6),Tuple2("))((((((((()()()()()())))))))))((((((((((())))((",28))
    TestUtil.printTime(1,1000000,index => { //测试100w的效率
      exceptValue.foreach(it => {
        val count = longestValidParentheses(it._1)
        if(count != it._2){
          System.out.print(it)
        }
      })
    })
  }

  def longestValidParentheses(s: String): Int = {
      val chars = getCharArray(s)
      var maxLength = 0
      var lastNotMatchIndex = -1 //记录上一次的不匹配的位置，两次不陪配的位置之差就是匹配的最大长度
      val stack = mutable.Stack[Int](64)
      for(i <- 0 until(chars.length)){
         if(chars(i) == '('){ //只push左括号的索引
           stack.push(i)
         }else{
           if(stack.isEmpty){//如果一直是右括号，同时栈中不为空，则当前位置一定不匹配
             lastNotMatchIndex = i
           }else{//如果栈里面有左括号，同时当前是右括号
             stack.pop()
             //如果有多余的右括号，会导致pop后栈为空
             //所以上一步的stack.isEmpty中记录有不匹配的位置
             if(stack.isEmpty){
               maxLength = Math.max(maxLength, i - lastNotMatchIndex)
             }else{//栈中还留有左括号，那当前情况一定是不匹配的
               maxLength = Math.max(maxLength,i - stack.top)
             }
           }
         }
      }
    maxLength
  }

  val strValueFiled = classOf[String].getDeclaredField("value")
  strValueFiled.setAccessible(true)
  def getCharArray(s : String):Array[Char] = {
    return strValueFiled.get(s).asInstanceOf[Array[Char]]
  }

}
