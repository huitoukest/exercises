package com.tingfeng.exercise

import scala.collection.mutable

/**
  * 20. 有效的括号
  * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
  *
  * 有效字符串需满足：
  *
  * 左括号必须用相同类型的右括号闭合。
  * 左括号必须以正确的顺序闭合。
  * 注意空字符串可被认为是有效字符串。
  *
  * 示例 1:
  *
  * 输入: "()"
  * 输出: true
  * 示例 2:
  *
  * 输入: "()[]{}"
  * 输出: true
  * 示例 3:
  *
  * 输入: "(]"
  * 输出: false
  * 示例 4:
  *
  * 输入: "([)]"
  * 输出: false
  * 示例 5:
  *
  * 输入: "{[]}"
  * 输出: true
  */
object Ex020 {


  def main(args: Array[String]): Unit = {
      val arrays = List("()[]{}","([)]","{[]}");
      arrays.foreach(it =>{
        println(isValid(it))
      })

  }

  def isValid(s: String): Boolean = {
    val map = Map('(' -> ')',
      '[' -> ']',
      '{' -> '}')
    val length = s.length
    if(length <= 0){
      true
    }else if ((length & 1) == 1) {
      false
    }else{
      val array = s.toCharArray
      var values = mutable.Stack[Char]()
      var lastChar = '_';
      for (index <- 0 until array.length) {
        val v = map.get(lastChar)
        if (v.isDefined && v.get == array(index) ){
            values.pop()
            if(!values.isEmpty){
              lastChar = values.top
            }
        }else {
          lastChar = array(index)
          values.push(lastChar)
        }
      }
      if (values.isEmpty) true else false
    }
  }

}
