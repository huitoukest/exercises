package com.tingfeng.exercise

/**
  * RegExp
  */
object Ex010 {

  def main(args: Array[String]): Unit = {
    val sArray = Array("aab","aa","aa","mississippi");
    val pattern = Array("c*a*b","a*","aa","mis*is*p*.")
    for(i <- 0 until(sArray.length)){
      println(isMatch(sArray(i),pattern(i)))
    }
  }

  def isMatch(s: String, p: String): Boolean = {

      def isMatchArray(chars: Array[Char] ,strPosition :Int,patterns: Array[Char],patternPosition: Int ): Boolean = {

        if(chars.length <= 0 && patterns.length <= 0 ){
            true
          }else if(chars.length > 0 && patterns.length > 0 ){
            if(strPosition >= chars.length || patternPosition >= patterns.length){
              false
            }else if(strPosition == chars.length - 1 && patternPosition == patterns.length - 1){
              true
            }else if(chars(strPosition) != patterns(patternPosition) &&
               patterns(patternPosition) != '.'  && (patternPosition < patterns.length - 1 && patterns(patternPosition + 1) != '*')){
               false
            }else{
              var ok = isMatchArray(chars,strPosition + 1,patterns,patternPosition) ;
                  ok = ok ||  isMatchArray(chars,strPosition,patterns,patternPosition + 1);
              var matchCount = 0
              //匹配.*和x*的情况，x必须存在
              while(patternPosition + matchCount < patterns.length - 1 && patterns(patternPosition + 1) == '*' &&
                    (patterns(patternPosition + matchCount) == '.' || chars(strPosition + matchCount) == patterns(patternPosition + matchCount))){
                  ok = ok ||  isMatchArray(chars,strPosition + matchCount,patterns,patternPosition + 2);
                  matchCount += 1
              }
              ok
            }
          }else{
            false
          }
      }

      isMatchArray(s.toCharArray,0,p.toCharArray,0)
  }
}
