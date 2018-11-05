package com.tingfeng.exercise

/**
  * 整数转罗马数字
  */
object Ex012 {


  def main(args: Array[String]): Unit = {
      val arrays = Array(1,2,3,4,5,6,7,8,9,10,58,1994)
    arrays.foreach(it =>{
      println(s"${it} : ${intToRoman(it)}")
    })

  }

  def intToRoman(num: Int): String = {
      val map = Map(1 -> "I",2 -> "II",3 -> "III",4 -> "IV",
                      5 -> "V",6 -> "VI",7 -> "VII",8 -> "VIII",
                      9 -> "IX",10 -> "X",40 -> "XL",50 -> "L",
                      90 -> "XC",100 -> "C",400 -> "CD",
                      500 -> "D",900 -> "CM",1000 -> "M")
      val keys = map.keySet.toList.sorted.reverse
      val sb = new StringBuilder()
      var value = num
      while(value > 0){
           var isContinue = true
           for(i <- 0 until keys.size if isContinue){
              if(value >= keys(i)){
                sb.append(map(keys(i)))
                isContinue = false
                value -= keys(i)
              }
           }
      }
    sb.toString()
  }

}
