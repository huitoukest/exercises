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
object Ex030 {


  def main(args: Array[String]): Unit = {
    val s = "abababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababab"
    val words = Array("ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba")
    findSubstring(s,words).foreach(it => if(null != it) print(s"$it,"))
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
          val  maxUseLength = s.length - wordsPerlength // 主串最大右边界
          val  indexPerMap = mutable.Map[String,Int]() //单次比对的结果,每一次字符串对应的数量
          var  perSize = 0
          val  indexList = mutable.ListBuffer[Int]() //所有对比结果的保存
          var  currentIndex = 0 //主串的当前对比索引
          val  strChars = getCharArray(s)
          val  wordsCount = words.map((_,1)).groupBy(_._1).mapValues(_.size)
          val  uniqWords = wordsCount.keySet.toArray
          while( currentIndex <= maxUseLength){
               val wordsIndex = getEqIndex(strChars,currentIndex,uniqWords)
               if(wordsIndex < 0){
                 if(perSize > 0) {
                   currentIndex = currentIndex - perSize * wordsPerlength
                   perSize = 0
                   indexPerMap.clear()
                 }
                 currentIndex += 1
               }else{
                 val tmpWord = uniqWords(wordsIndex)
                 val currentWordMaxCount = wordsCount.getOrElse(tmpWord,0) //当前匹配的字符串拥有的数量
                 val currentWordCount = indexPerMap.getOrElse(tmpWord,0)
                 if(currentWordMaxCount > 0 && currentWordCount < currentWordMaxCount){
                   perSize += 1
                   indexPerMap.put(tmpWord,currentWordCount + 1)
                   currentIndex += wordsPerlength
                 }else{//如果数量用完，则表示不匹配
                   currentIndex = currentIndex - perSize * wordsPerlength
                   perSize = 0
                   indexPerMap.clear()
                   currentIndex += 1
                 }
               }
               if(perSize == words.length){//如果全部ok
                 perSize = 0
                 indexPerMap.clear()
                 currentIndex = currentIndex - totalLength
                 indexList += currentIndex
                 currentIndex += 1
               }
          }
          indexList.toList
        }
      }
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

  /**
    * 如果有子串匹配则返回索引，否则返回-1
    * @param chars
    * @param startIndex
    * @param words
    * @return
    */
  def getEqIndex(chars : Array[Char],startIndex : Int,words: Array[String]):Int = {
    var index = -1
    Breaks.breakable{
      for (i <- 0 until words.length) {
        if(isEq(chars,startIndex,words(i))) {
          index = i
          Breaks.break()
        }
      }
    }
    index
  }

  def isEq(chars : Array[Char],startIndex : Int ,subStr : String):Boolean = {
      val subChars = getCharArray(subStr)
      var isOk = true
      Breaks.breakable {
        for (i <- 0 until subChars.length if (chars(startIndex + i) != subStr(i))) {
          isOk = false
          Breaks.break()
        }
      }
      isOk
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
