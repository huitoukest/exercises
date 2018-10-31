package com.tingfeng.exercise

import java.util.concurrent.ThreadLocalRandom

/**
  * RegExp
  */
object Ex010 {

  var letter = Array('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z')

  def main(args: Array[String]): Unit = {
    val sArray = Array("aab","aa","aa","mississippi");
    val pattern = Array("c*a*b","a*","aa","mis*is*p*.")
    for(i <- 0 until(sArray.length)){
      println(isMatch(sArray(i),pattern(i)))
    }
  }

  def isMatch(s: String, p: String): Boolean = {

      def isMatchArray(chars: Array[Char] ,strPosition :Int,patterns: Array[Char],patternPosition: Int ): Boolean = {

        if(chars.length <= 0 && patterns.length <= 0 ){ //匹配空
            true
          } else if(chars.length > 0 && patterns.length > 0 ){ //匹配有正则表达式的情况
          if(patternPosition >= patterns.length && chars.length > strPosition){
            false
          }else if(patternPosition < patterns.length && chars.length <= strPosition){
            val paStr = patterns.takeRight(patterns.length - patternPosition)
            if((paStr.length & 1 ) == 1){
              false
            }else{
              var re = true
              for(i <- 1 until paStr.length by(2) ; if paStr(i) != '*'){
                   re = false
              }
              re
            }
          }else if( ( strPosition >= chars.length && patternPosition >= patterns.length )){//都匹配了前面所有的字符则表示匹配所有了
              true
          }else if(strPosition == chars.length - 1 && patternPosition == patterns.length - 1
            && (chars(strPosition) == patterns(patternPosition) || patterns(patternPosition) == '.')){ //如果只剩下最后一位匹配
            true
          }
          else if((patternPosition < patterns.length - 1 && patterns(patternPosition + 1) != '*')
                    && (chars(strPosition) == patterns(patternPosition) || patterns(patternPosition) == '.')){
                //如果是单个字符的匹配，则往后走
                isMatchArray(chars,strPosition + 1,patterns,patternPosition + 1)
            }else if(patternPosition < patterns.length - 1 && patterns(patternPosition + 1) == '*'){//匹配.*和x*的情况，x必须存在
              //这里匹配从当前位置一直到结束长度或者下一个不匹配的位置为止
              var matchCount = 0
              var ok = false
              var position = strPosition + matchCount
              //匹配.*和x*的情况，x必须存在
              if(patterns(patternPosition) == '.'){
                  while(!ok && position < chars.length){
                    var position = strPosition + matchCount
                    ok = isMatchArray(chars,position,patterns,patternPosition + 2);
                    matchCount += 1
                  }
              }else {//如果是一个普通的字符
                ok = ok || isMatchArray(chars,strPosition,patterns,patternPosition + 2) ; // 匹配0个的情况
                while(!ok && position < chars.length && chars(position) == patterns(patternPosition)){
                  position = strPosition + matchCount
                  ok = isMatchArray(chars,position,patterns,patternPosition + 2);
                  matchCount += 1
                }
              }
              ok
            }else{
              false
            }
          }else{
            false
          }
      }
      isMatchArray(s.toCharArray,0,p.toCharArray,0)
  }

    /**
      * 按照规则产生简单的正则表达式,转义符号只包含点号和星号
      * @param length
      * @return
      */
    def generatorSimpleRegExpStr(length : Int):Tuple2[String,String] = {
        val sb = new StringBuilder
        for (i <- 0 to length){
          val typeValue = ThreadLocalRandom.current().nextInt(4)
          if(typeValue == 0){
            val randomPosition = ThreadLocalRandom.current().nextInt(26)
            sb.append(letter(randomPosition))
          }else if(typeValue == 1){
            sb.append(".")
          }else if(typeValue == 2){
            sb.append("*")
          }else if(typeValue == 3){
            sb.append(".*")
          }
        }

        def makeStr():String = {

        }
    }
}
