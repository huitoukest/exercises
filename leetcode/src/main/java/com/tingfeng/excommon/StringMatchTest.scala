import java.util
import java.util.concurrent.ThreadLocalRandom

import com.tingfeng.excommon.TestUtil

/**
  * 字符串匹配测试
  */
object StringMatchTest {

  var letter = Array('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z')

  def main(args :Array[String]): Unit = {
    //testKMPMany()
    //testKMPOne()
    //testKMPTime()
    testStrHashMatch()
  }

  def testKMPTime():Unit = {
    TestUtil.printTime(1,10,index => {
          val str = getStr(10000,5)
          val subStr = str.substring(5000, 5010);
          println(str.indexOf(subStr))
    });
    TestUtil.printTime(1,10,index => {
      val str = getStr(10000,5)
      val subStr = str.substring(5000, 5010);
      println(kmpMath(str,subStr))
    });
  }

  def testKMPMany(): Unit ={
    for(i <- 0 until 20000) {
      val str = getStr(10000)
      val subStr = str.substring(8000, 8010);
      if (str.indexOf(subStr) != kmpMath(str, subStr)) {
        println
        println(str)
        println(subStr)
        println(str.indexOf(subStr))
        println(kmpMath(str,subStr))
      }
    }
  }

  def testKMPOne(): Unit ={
    val str = "aababaabaaaaababbbbbbbababbbbbaabbabababbaabbbaaabbaabbbaaabaabaabbaaabbabaaababaaaababbabbaaabaabbaabbbaabababbbabaabbbabbbababaab"
    val subStr = "babaaa"
    println(str)
    println(subStr)
    println(str.indexOf(subStr))
    println(kmpMath(str,subStr))
  }

  def getStr(length: Int,letterCount : Int = 2):String = {
    val sb = new StringBuilder()
    for(i <- 0 until length){
      val value = ThreadLocalRandom.current().nextInt(letterCount)
      sb.append(letter(value))
    }
    sb.toString()
  }

  /**
    * 返回第一个匹配的值
    * @param str
    * @param subString
    * @return
    */
  def kmpMath(str: String,subString:String):Int = {
    val next = getNext(subString)
    /*println
    next.foreach(it => print(it + ","))
    println*/
    val charArray = str.toCharArray
    val subArray = subString.toCharArray
    var isContinue = true
    var index = -1
    var i = 0
    var j = 0
    while(i < charArray.length && isContinue){
      while(j < subArray.length &&  i < charArray.length
        && subArray(j) == charArray(i)){
        j +=  1
        i += 1
      }
      //println(str.substring(i))
      if(j == subArray.length){//长度相等表示已经匹配成功了
        isContinue = false
        index = i - subArray.length
      }else if(j == 0){
        i += 1
      }else{
        var nextStep = next(j - 1)
        j = nextStep
        //j = nextStep - 1 //索引的值比相同个数的值小1
        /*if(j < 0){
          j = 0
          //i += 1
        }*/
      }
    }
    index
  }

  def getNext(str: String):Array[Int]={
    val subArray = str.toCharArray
    val next = new Array[Int](subArray.length)
    util.Arrays.fill(next,0)
    var k = 0
    var i = 1 //这里i = 0的时候赋值为-1，作为k = next(k)的递归的结束条件
    while(i < subArray.length){
      if(k == 0 || subArray(i) == subArray(k)) {
        if (subArray(i) == subArray(k)) {
          k += 1
        }
        next(i) = k
        i += 1
      }else{ //当结果不想等的时候就一直递归的执行k = next(k - 1)，此时i的值没有动；
        //直到k = 0,即subArray(i) = -1的时候，此时k会被赋值为-1，此时递归结束；
        if(k != next(k - 1)){//这里的k是前后缀相同的个数，如果是计算索引则需要减去1
          k = next(k - 1)//这里的next(k - 1)实际上表示的是在位置i的前一个位置的后缀的值（因为这里前后缀相等关系），所以这里也表示其前缀的索引
        }else{
          k = 0
        }
      }
    }
    //最后把所有的-1变为0，表示对比的数据不同的时候，移动到索引为0的位置
    next.map(it => if(it < 0) 0 else it)
    //next(0) = -1
    //next
  }

  /**
    * 测试hash匹配的情况
    */
  def testStrHashMatch(): Unit ={
    for(i <- 0 until 10) {
      val str = getStr(1000)
      val subStr = str.substring(401, 426);
      if (str.indexOf(subStr) != strHashMatch(str, subStr)) {
        println
        println(str)
        println(subStr)
        println(str.indexOf(subStr))
        println(strHashMatch(str,subStr))
      }
    }
  }

  /**
    * 用来取模的最大值
    */
  val modValue = Long.MaxValue / 3001

  /**
    * 字符串的Hash匹配方法
    * @param str
    * @param subString
    * @return
    */
  def strHashMatch(str: String,subString:String):Int={
    val charArray = str.toCharArray
    val subArray = subString.toCharArray
    var isContinue = true
    var rightHashValue = 0L //记录主串的当前hash的值
    var lastHashValue = 0L
    var subHashValue = 0L //记录子串的当前hash的值
    var leftHashValue = 0L //最左侧的索引的值的总和
    var leftIndex = 0
    var matchIndex = -1
    var maxWeight = 1L //只要保证所有值得和不大于mod的值即可，所以这里
    var decimalValue = 31L
    if(subArray.length <= str.length){
       for(i <- 0 until(subArray.length)){
           //这里求出最高位的权重值,由于实际上的第一位权重值是0，所以这里实际上求出的是subArray.length位的权重值
           maxWeight = maxWeight * decimalValue
       }
       for(index <- 0 until charArray.length if isContinue){
          if(index < subArray.length ){
              subHashValue = (subHashValue * decimalValue + subArray(index))
          }
          if(index <= subArray.length - 1){
            leftIndex = 0
          }else{
            leftHashValue = (leftHashValue * decimalValue + charArray(leftIndex))
            leftIndex += 1
         }
         rightHashValue = (rightHashValue * decimalValue + charArray(index))
         var subValue = leftHashValue * maxWeight //如果溢出就相当于自动取余，如果这里手动取余数的话会导致其取的值实际上是系统溢出后的余数，即取余两次的结果，是不准确的。
         /*if(subValue > 0) {
            subValue = subValue % maxWeight
         }else{
           while (subValue < 0) {
             subValue += modValue
           }
         }*/
         var currentHash = (rightHashValue -  subValue)
         /*if (rightHashValue < subValue) {
           currentHash += modValue
         }*/
         if(index >= subArray.length) {
           //如果长度相等，则每一次需要减去行一次的数值并加上此次的数值
         }
         if(index >= subArray.length - 1 && currentHash == subHashValue){
           isContinue = false
           matchIndex = index - subArray.length + 1
         }
       }
    }
    matchIndex
  }

}