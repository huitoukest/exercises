import java.util
import java.util.concurrent.ThreadLocalRandom

/**
  * 字符串匹配测试
  */
object StringMatchTest {

  var letter = Array('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z')

  def main(args :Array[String]): Unit = {
    testMany()
    testOne()
  }

  def testMany(): Unit ={
    for(i <- 0 until 20000) {
      val str = getStr(10000)
      val subStr = str.substring(100, 106);
      if (str.indexOf(subStr) != kmpMath(str, subStr)) {
        println
        println(str)
        println(subStr)
        println(str.indexOf(subStr))
        println(kmpMath(str,subStr))
      }
    }
  }

  def testOne(): Unit ={
    val str = "aababaabaaaaababbbbbbbababbbbbaabbabababbaabbbaaabbaabbbaaabaabaabbaaabbabaaababaaaababbabbaaabaabbaabbbaabababbbabaabbbabbbababaab"
    val subStr = "babaaa"
    println(str)
    println(subStr)
    println(str.indexOf(subStr))
    println(kmpMath(str,subStr))
  }

  def getStr(length: Int):String = {
    val sb = new StringBuilder()
    for(i <- 0 until length){
      val value = ThreadLocalRandom.current().nextInt(2)
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


}