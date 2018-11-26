package com.tingfeng.exercise

import com.tingfeng.exercise.Ex010_01.{generatorSimpleRegExpStr, isMatch}

/**
  * 盛最多水的容器
  */
object Ex011 {


  def main(args: Array[String]): Unit = {
      val arrays = Array(1,8,6,2,5,4,8,3,7)
      print(maxArea(arrays))
  }

  def maxArea(height: Array[Int]): Int = {
      var i = 0
      var j = height.length - 1
      var maxValue = 0

      def getArea(startIndex : Int, endIndex : Int):Int = {
        val min = Math.min(height(startIndex),height(endIndex))
        (endIndex - startIndex) * min
      }

      while(i < j){
          maxValue = Math.max(maxValue,getArea(i,j))
          if((height(i + 1) > height(i))){
            i += 1
          }else{
            j -= 1
          }
      }
     maxValue
  }

}
