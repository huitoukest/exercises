package com.tingfeng.exercise

import java.util.concurrent.ThreadLocalRandom

/**
  * RegExp 原010方法超时，需要对010中代码进行优化,01表示第一次优化
  */
object Ex010_01 {

  var letter = Array('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z')

  def main(args: Array[String]): Unit = {
    /*val testLength = 2
    val pairStr = generatorSimpleRegExpStr(testLength,20)
    var isBreak = false
    for(i <- 0 until testLength if !isBreak){
      val pa = pairStr(i)
      print(i + "\t")
      println(pa)
      if(!isMatch(pa._2,pa._1)){
        isBreak = true
      }
    }*/
    println("random reg test over")
    val sArray = Array("abccb","aabb");
    val pattern = Array(".*a*ab.*c.",".*ab*")
    for(i <- 0 until(sArray.length)){
      println(isMatch(sArray(i),pattern(i)))
    }
  }

  def isMatch(s: String, p: String): Boolean = {
      //1.将多个连接在一起的.*替换为一个.*，将p*后面的p*和ppp替换掉
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

      def isMatchArray(chars: Array[Char] ,strPosition :Int,patterns: Array[Char],patternPosition: Int ): Boolean = {
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
                            val maxLength = contentRestLength - patterns.takeRight(regRestLength).filter(it => it == '*').length * 2
                            val nextRegPosition = patternPosition + 2
                            while (!ok && matchCount <= maxLength) {
                              var position = strPosition + matchCount
                              ok = isMatchArray(chars, position, patterns, nextRegPosition);
                              matchCount += 1
                            }
                            ok
                          }
                          case letter: Any => { //匹配单个字符
                            var matchCount = 0
                            var ok = false
                            var position = strPosition + matchCount
                            //计算一下最大能够模糊匹配的长度
                            val maxLength = patterns.takeRight(regRestLength).prefixLength(it => it != letter)
                            val nextRegPosition = patternPosition + 2
                            ok = ok || isMatchArray(chars, strPosition, patterns, patternPosition + 2); // 匹配0个相对的情况
                            while (!ok && matchCount <= maxLength && chars(position) == patterns(patternPosition)) { //匹配1个或者多个相等的情况
                              position = strPosition + matchCount
                              ok = isMatchArray(chars, position, patterns, nextRegPosition);
                              matchCount += 1
                            }
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
                }else{//如果是最后一位
                    currentReg match {
                    case '.' => true
                    case _ if currentReg == chars(strPosition) => true
                    case _ => false
                  }
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
}
