package com.tingfeng.exercise

import com.tingfeng.excommon.TestUtil

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
  * 思考： 无论是用栈，还是数组标记记录，
  * 此题应该有有两个步奏，步骤1就是标记其中成对的有效括号，
  * 步奏二就是统计每个有效的长度值。
  */
object Ex032 {

  def main(args: Array[String]): Unit = {
      val str = "()()"
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
      val countArray = new Array[Int](chars.length) //将成对的括号替换其索引所在的位置为数字0，1,2,3，其余的默认为null
      for(i  <- 0 until(chars.length)){
        countArray(i) = -1
      }
      //统计时将非0数字替换为1，并将连续为1的累加，求最大值即可
      var maxLength = 0 //总的有效括号数
      var currentLength = 0 //当前有效括号数
      //val subStr = new mutable.StringBuilder()
      var index = 0
      while(index < chars.length){
        if(chars(index) == ')' && index > 0){
          val leftIndex = index - 1
          if(chars(leftIndex) == '(') {
            countArray(leftIndex) = index
            countArray(index) = leftIndex
          }else{//如果还是右括号，则需要获取其所在的上次索引值
            var tmpLeft = countArray(leftIndex)
            var hasValue = false
            Breaks.breakable {
              for (j <- Range(countArray(leftIndex),-1,-1)) {
                if (countArray(j) < 0) {
                  tmpLeft = j
                  hasValue = true
                  Breaks.break()
                }
              }
            }
            if(!hasValue){
                tmpLeft = -1
            }
            if(tmpLeft != null && tmpLeft >= 0){
              if(chars(tmpLeft) == '('){
                countArray(tmpLeft) = index
                countArray(index) = tmpLeft
              }
            }
          }
        }
        index = index + 1
      }
      //求不为0的连续长度
     for(i <- 0 until(countArray.length)){
        if(countArray(i) < 0){
          currentLength = 0
        }else{
          currentLength += 1
        }
        if(currentLength > maxLength){
         maxLength = currentLength
        }
     }
     if((maxLength & 1) == 1){//如果是奇数，即索引0位置没有统计到
       maxLength += 1
     }
      maxLength
  }

  def getCharArray(s : String):Array[Char] = {
    val strValueFiled = classOf[String].getDeclaredField("value")
    strValueFiled.setAccessible(true)
    return strValueFiled.get(s).asInstanceOf[Array[Char]]
  }

}
