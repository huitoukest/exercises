package com.tingfeng.exercise

import java.util.concurrent.ThreadLocalRandom

/**
  * RegExp 原010方法超时，需要对010中代码进行优化,01表示第一次优化
  */
object Ex010_01 {

  var letter = Array('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z')

  def main(args: Array[String]): Unit = {
    val testLength = 1
    val pairStr = generatorSimpleRegExpStr(testLength,1000)
    var isBreak = false
    for(i <- 0 until testLength if !isBreak){
      val pa = pairStr(i)
      print(i + "\t")
      println(pa)
      if(!isMatch(pa._2,pa._1)){
        isBreak = true
      }
    }
    println("random reg test over")
    val sArray = Array("aa");
    val pattern = Array("a")
    for(i <- 0 until(sArray.length)){
      println(isMatch(sArray(i),pattern(i)))
    }
  }

  def printDetail(chars: Array[Char] ,strPosition :Int,patterns: Array[Char],patternPosition: Int)={
    val contentLength = chars.length
    val regLength = patterns.length
    println("______________")
    println(chars.takeRight(contentLength - strPosition).toList)
    println(patterns.takeRight(regLength - patternPosition).toList)
  }

  def isMatch(s: String, p: String): Boolean = {
      def isMatchArray(chars: Array[Char] ,strPosition :Int,patterns: Array[Char],patternPosition: Int ): Boolean = {
        //printDetail(chars,strPosition,patterns,patternPosition)
        val contentLength = chars.length
        val regLength = patterns.length
        val contentRestLength = if(contentLength > 0 ) contentLength - strPosition else  0
        val regRestLength = if(regLength > 0) regLength - patternPosition else 0
        if(contentRestLength <= 0 && regRestLength <= 0 ){ //匹配空
            true
          }else if(contentRestLength <= 0 && regRestLength > 0 ){
            if(regLength > 1 && patterns(patternPosition + 1) == '*'){
              true
            }else {
              false
            }
          }else if(contentRestLength > 0 && regRestLength > 0 ){ //匹配有正则表达式的情况
                val currentReg = patterns(patternPosition)
                if(regRestLength > 1) {
                    //如果还剩余两个以上意思
                    val nextReg = patterns(patternPosition + 1)
                    nextReg match { //如果下一正则字符是*
                      case '*' => {
                        currentReg match {
                          case '.' => {
                            var matchCount = 0
                            var ok = false
                            var position = strPosition + matchCount
                            //计算一下最大能够模糊匹配的长度
                            val restReg = patterns.takeRight(regRestLength)
                            val maxLength = contentRestLength - (restReg.filter(it => it != '*').length - restReg.filter(it => it == '*').length)
                            val nextRegPosition = patternPosition + 2
                            do{
                              var position = strPosition + matchCount
                              ok = ok || isMatchArray(chars, position, patterns, nextRegPosition);
                              matchCount += 1
                            }while (!ok && matchCount <= maxLength)
                            ok
                          }
                          case letter: Any => { //匹配单个字符
                            var matchCount = 0
                            var ok = false
                            var position = strPosition + matchCount
                            //计算一下最大能够模糊匹配的长度
                            val maxLength = chars.takeRight(contentRestLength).prefixLength(it => it == letter)
                            val nextRegPosition = patternPosition + 2
                            do{ //匹配0个或者多个相等的情况
                              position = strPosition + matchCount
                              ok = ok || isMatchArray(chars, position, patterns, nextRegPosition);
                              matchCount += 1
                            }while (!ok && matchCount <= maxLength && chars(position) == patterns(patternPosition))
                            ok
                          }
                        }
                      }
                    case _ => {
                      if (currentReg == chars(strPosition) || currentReg == '.') {
                        isMatchArray(chars, strPosition + 1, patterns, patternPosition + 1)
                      } else {
                        false
                      }
                    }
                  }
                }else if(contentRestLength == 1){//如果内容也是最后一位
                  currentReg match {
                    case '.' => true
                    case _ if currentReg == chars(strPosition) => true
                    case _ => false
                  }
                }else{
                  false
                }
          }else{
            false
          }
      }
      //1.将多个连接在一起的.*替换为一个.*
      isMatchArray(s.toCharArray,0,p.toCharArray,0)
  }

    /**
      * 按照规则产生简单的正则表达式,转义符号只包含点号和星号
      * @param length 产生length条记录
      * @param probablyStrSize 一个大概的字符串的长度（波动一般在0.5-4）之间
      * @return  Tuple2(正则表达式，字符串内容)
      */
    def generatorSimpleRegExpStr(length : Int,probablyStrSize :Int):IndexedSeq[Tuple2[String,String]] = {
        val sb = new StringBuilder
        val content = new StringBuilder
        val charSb = new StringBuilder

        def makeOneStr(len: Int):String={
            charSb.setLength(0)
            for(i <- 0 until len){
              val randomPosition = ThreadLocalRandom.current().nextInt(26)
              charSb.append(letter(randomPosition))
            }
          charSb.toString()
        }

        def makeStr():Tuple2[String,String] = {
          sb.setLength(0)
          content.setLength(0)
          for (i <- 0 to probablyStrSize){
            val typeValue = ThreadLocalRandom.current().nextInt(4)
            typeValue match {
              case 0 => {
                val tmp = ThreadLocalRandom.current().nextInt(probablyStrSize) + 1
                val str = makeOneStr(tmp)
                sb.append(str)
                content.append(str)
              }
              case 1 => {
                sb.append(".")
                content.append(makeOneStr(1))
              }
              case 2 => {
                val str = makeOneStr(1)
                sb.append(s"$str*")
                for(j <- 0 until ThreadLocalRandom.current().nextInt(10)){
                  content.append(str)
                }
              }
              case 3 => {
                sb.append(".*")
                for(j <- 0 until ThreadLocalRandom.current().nextInt(probablyStrSize)){
                  content.append(makeOneStr(1))
                }
              }
            }
          }
          (sb.toString(),content.toString())
        }
      for(i <- 0 until length) yield makeStr()
    }

  /**
    * 1.将多个连接在一起的.*替换为一个.*，将p*后面的p*和ppp替换掉,对正则表达式本身进行一定的优化
    */
  def replaceRepeatReg(originalReg: String):List[String] = {
    var strList = List[String]()
    val originChars = originalReg.toCharArray()
    var lastRegPar:String = null
    var i =   originalReg.length - 1
    while(i >= 0){
      if(originChars(i) == '*'){
        val lastChar = originChars(i - 1)
        strList = strList :+ (lastChar.toString + originChars(i))
        i -= 1
      }else{
        strList = strList :+ originChars(i).toString
      }
      i -= 1
    } //倒序
    strList = strList.reverse
    //简单的去重重复
    var reList = List[String]()
    lastRegPar = null
    for(i <- 0 until strList.length){
      if(lastRegPar != null && (strList(i).size > 1) && lastRegPar != strList(i) && lastRegPar != ".*"){
        reList = reList :+ strList(i)
      }else if(lastRegPar != null && lastRegPar.size > 1 && !lastRegPar.endsWith(strList(i))){
        reList = reList :+ strList(i)
      }else{
        reList = reList :+ strList(i)
      }
      lastRegPar = strList(i)
    }
    reList
  }
}
