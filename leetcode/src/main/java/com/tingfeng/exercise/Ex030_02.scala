package com.tingfeng.exercise

import cn.hutool.core.util.RandomUtil
import com.tingfeng.excommon.TestUtil

import scala.collection.mutable
import scala.util.control.Breaks

/**
  *
  * 串联所有单词的子串
  *给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
  *注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 **
  * 示例 1：
  *输入：
    *s = "barfoothefoobarman",
    *words = ["foo","bar"]
  *输出：[0,9]
  *解释：
  *从索引 0 和 9 开始的子串分别是 "barfoor" 和 "foobar" 。
  *输出的顺序不重要, [9,0] 也是有效答案。
 **
  * 示例 2：
  *输入：
    *s = "wordgoodgoodgoodbestword",
    *words = ["word","good","best","word"]
  *输出：[]
  * "wordgoodgoodgoodbestword"
  * ["word","good","best","good"]
  * 输出：[8]
  */
/**
  * 思路
  * 1.从0-n切割主串，返回定长上的Map的单词与数量的映射并排序
  * 2.子串数组映射为单词与数量的Map并排序
  * 3.依次循环，两个Map做对比
  * 4. 在3中循环时实际上可以以单词长度作为每次滑块的长度，如此遍历等于长度 * 以内度为滑块的内容次数（但是滑块中判断时只需要减去上次的结果同时加上新的结果，     不用判断中间的结果）
  * 5. 小优化思路：compareMap 时在大于时，实际上可以直接返会其中eq的个数，通过个数可以跳过非eq的个数而不用再次判断
  */
object Ex030_02 {

  def main(args: Array[String]): Unit = {
    findSubstring("wordgoodgoodgoodbestword",Array("word","good","best","good")).foreach(it => if(null != it) print(s"$it,"))
    //不用第3步奏优化时：18221毫秒
    TestUtil.printTime(1,100,index => {
      testSpeed()
    })
    println()
    val data = makeStr(5,5,3)
    printData(data)
    findSubstring(data._1,data._2).foreach(it => if(null != it) print(s"$it,"))

    TestUtil.printTime(1,10,index => {
        val data = makeStr(10,50,2)
        val re = findSubstring(data._1,data._2)
        if(data._3 != re){
          printData(data)
          re.foreach(it => if(null != it) print(s"$it,"))
          println()
        }
    })
  }

  def testSpeed(): Unit ={
    val s = "abababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababab"
    val words = Array("ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba")
    findSubstring(s,words).foreach(it => if(null != it) print(s"$it,"))
  }

  def printData(data : Tuple3[String,Array[String],List[Int]]): Unit ={
    println()
    println(data._1)
    data._2.foreach(it => if(null != it) print(s"$it,"))
    println()
    data._3.foreach(it => if(null != it) print(s"$it,"))
    println()
  }

  /** 1.words判断空
    * 2.判断words中单词长度是否一致
    * 3.
    * @param s
    * @param words
    * @return
    */
  def findSubstring(s: String, words: Array[String]): List[Int] = {

      if(words == null || words.length == 0 || getAbleLength(words) < 0){
         List[Int]()
      }else {
        var  wordsPerlength = words(0).length
        val  totalLength = wordsPerlength * words.length
        if(s.length < totalLength){
          List[Int]()
        }else{
          var  wordsPerlength = words(0).length
          val  indexPerMap = mutable.Map[String,Int]() //单次比对的结果,每一次字符串对应的数量
          var  perSize = 0
          val  indexList = mutable.ListBuffer[Int]() //所有对比结果的保存
          val  wordsCount = mutable.HashMap((words.map((_,1)).groupBy(_._1).mapValues(_.size)).toSeq:_*)
          val  perMap = new mutable.HashMap[String,Int]()
          val  leftOffset = (words.length - 1) * wordsPerlength
          for(i <- 0 until wordsPerlength){//滑块内部位移
              val  maxSlength = (s.length - i) / wordsPerlength
              for(j <- 0 until maxSlength){//移动的滑块步数
                var startIndex = j * wordsPerlength + i
                val perStr = s.substring(startIndex,startIndex + wordsPerlength)
                perMap.put(perStr, perMap.getOrElse(perStr, 0) + 1)
                if(j >= words.length - 1){//当数量等于单词数量时开始判断和操作
                  val re = compareMap(perMap,wordsCount)
                  val leftRemoveIndex = startIndex - leftOffset
                  if(re == 0){
                    indexList += leftRemoveIndex
                  }
                  val leftRemoveStr = s.substring(leftRemoveIndex,leftRemoveIndex + wordsPerlength)
                  val countPerValue = perMap.getOrElse(leftRemoveStr, 1) - 1
                  if(countPerValue == 0){
                    perMap.remove(leftRemoveStr)
                  }else{
                    perMap.put(leftRemoveStr, countPerValue)
                  }
                }
              }
             perMap.clear();
          }
          indexList.toList
        }
      }
  }

  /**
    * 比较value值，如果a > b 返回 1;
    * a = b 返回0
    * a < b 返回 -1；
    * @param a
    * @param b
    * @return
    */
  def compareMap(a : mutable.HashMap[String,Int],b:mutable.HashMap[String,Int]):Int = {
      val keys = a.keySet
      var  re = 0
      Breaks.breakable {
        for (k <- keys) {
          val valueA = a.getOrElse(k, 0)
          val valueB = b.getOrElse(k, 0)
          if(valueA > valueB){
            re = 1
            Breaks.break()
          }else if(re < 1 && valueA < valueB){
            re = -1
          }
        }
      }
      re
  }

  /**
    * 正常返回长度，否则返回-1
    * @param words
    * @return
    */
  def getAbleLength(words: Array[String]):Int = {
      var  length = words(0).length
      Breaks.breakable{
       for(i <- 1 until words.length ){
         if(length != words(i).length){
          length = -1
          Breaks.break()
         }
       }
      }
    length
  }

  def getCharArray(s : String):Array[Char] = {
      val strValueFiled = classOf[String].getDeclaredField("value")
      strValueFiled.setAccessible(true)
      return strValueFiled.get(s).asInstanceOf[Array[Char]]
  }

  /**
    * 制作一个测试用例
    * @param wordsLength
    * @param wordCount
    * @param indexCount 主串中的子串indexCount 数量
    * @return
    */
  def makeStr(wordsLength :Int,wordCount : Int,indexCount : Int):Tuple3[String,Array[String],List[Int]] = {
      val strBuffer = new mutable.StringBuilder()
      val words = mutable.ArrayBuffer[String]()
      val wordsRandom = mutable.ArrayBuffer[String]()
      val indexList = mutable.ListBuffer[Int]()
      for(i <- 0 until wordCount){
        words += RandomUtil.randomString(wordsLength)
        wordsRandom += words(i)
      }


      for(i <- 0 until indexCount){
        val tmpLength = RandomUtil.randomInt(wordsLength)
        var tmpWord = ""
        if(tmpLength > 0) {
          tmpWord = RandomUtil.randomString(tmpLength)
        }
        strBuffer.append(tmpWord)
        indexList += strBuffer.length

        for(j <- 0 until wordCount / 2) {//打乱顺序
        val tmpLength = RandomUtil.randomInt(wordCount)
          val tmp = wordsRandom(j)
          wordsRandom(j) = wordsRandom(tmpLength)
          wordsRandom(tmpLength) =  tmp
        }
        for(j <- 0 until(wordCount)) {
          strBuffer.append(wordsRandom(j))
        }
      }
     (strBuffer.toString(),words.toArray,indexList.toList)
  }

}
