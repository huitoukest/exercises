package com.tingfeng.exercise

import com.tingfeng.excommon.TestUtil

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks

/**
  * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？
  * 找出所有满足条件且不重复的四元组。
  *
  * 注意：
  *
  * 答案中不可以包含重复的四元组。
  *
  * 示例：
  *
  * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
  */
object Ex018 {

  val expectArray = Array(-5,-4,-3,1)
  //[[-5,-4,-3,1]]
  def main(args: Array[String]): Unit = {
    val arrays = List( (Array(1, 0, -1, 0, -2, 2),0),
      (Array(1,-2,-5,-4,-3,3,3,5),-11),
      (Array(9,8,6,5,4,3,2,1,7,5,1,4,2,1,4,5,-6,-2,-3,-1,-8,-20,17,-13,-14,-1,-15,-21),5));
    /*arrays.foreach(it =>{
      println(fourSum(it._1,it._2))
    })*/

    arrays.foreach(it =>{
      println(fourSum2(it._1,it._2))
    })
    arrays.foreach(it =>{
      println(fourSum3(it._1,it._2))
    })

    TestUtil.printTime(1,5000,index =>{
      //fourSum(arrays(0),-11)
      arrays.foreach(it =>{
        fourSum(it._1,it._2)
      })
    });
    TestUtil.printTime(1,5000,index =>{
      arrays.foreach(it =>{
        fourSum2(it._1,it._2)
      })
    })
    TestUtil.printTime(1,5000,index =>{
      arrays.foreach(it =>{
        fourSum3(it._1,it._2)
      })
    })
  }

  /**
    * 求出从右到左的
    * @param nums
    * @param target
    * @return
    */
  def fourSum(nums: Array[Int], target: Int): List[List[Int]] = {
    val tupleSize = 4
    val sortNums = nums.sorted
    var result = ListBuffer[List[Int]]()
    val ltZeroIndex = sortNums.prefixLength( it => it <= 0) - 1 //小于0的索引，左边小于等于0 ，右边大于0
    def fourSumN(startIndex: Int,currentTotal: Int,nowSize: Int,currentList:List[Int]): Unit = {
      val isBiggerTotal = currentTotal >= target
      Breaks.breakable {
        for (index <- startIndex until nums.length) {
          if(isBiggerTotal && index > ltZeroIndex){//当前的值是否过大
            Breaks.break()
          }
          var total = currentTotal + sortNums(index)
          val needSize = tupleSize - nowSize
          needSize match {
            //距离指定的x长度的数组还差的长度
            case 1 => {
              if (total == target) {
                val re = currentList :+ sortNums(index)
                result +=re
              }
            }
            case a: Int if a > 0 => {
              //这里存在的问题是：
              //如果total >= target，而且右边的值是大于0的并且会越来越大，那么就不可取
              //而如果total <= target 但是，index却在小于ltZeroIndex的地方确实可取的，因为后面的值会越来越大，最终有可能将之和为target
              fourSumN(index + 1, total, nowSize + 1, currentList :+ sortNums(index))
            }
          }
        }
      }
    }
    fourSumN(0,0,0,List[Int]())
    //去重复
    result.toList.distinct
    //result
  }

  /** 0.1 对于target而言，求n个数之和（设置A）等于其值，那么A总是大于等于sortNums的做百年n数之和，小于等于右边n数之和
    * 0.2 从0.1可以推测，由于sortNums数组从小打到排列，那么A总是大于等于
    * 0.3 当前sortNums中还未匹配的记录的数量必须大于等于needSize
    * 1.最后剩余一个值的时候可以使用HashMap来获取，在HashMap中key是当前的值，value是当前值的数量
    * @param nums
    * @param target
    * @return
    */
  def fourSum2(nums: Array[Int], target: Int): List[List[Int]] = {
    val tupleSize = 4
    if(nums.length == 0 || nums.length < tupleSize){
      List[List[Int]]()
    }else {
      val sortNums = nums.sorted
      var result = ListBuffer[List[Int]]()
      var maxValues = new Array[Long](sortNums.length)
      var tmpMaxValue = 0L
      val stopIndex = sortNums.length - tupleSize
      for(i <- sortNums.length - 1 to 0 by -1){
        if(i >= stopIndex){
          tmpMaxValue = tmpMaxValue +  sortNums(i)
        }
        maxValues(i) = tmpMaxValue
      }
      maxValues = maxValues.reverse
      val dataMapTmp = nums.groupBy(a => a).map(it => (it._1, it._2.length))
      val dataMap = new mutable.HashMap[Int, Int]()
      dataMapTmp.foreach(it => dataMap += it)
      val ltZeroIndex = sortNums.prefixLength(it => it <= 0) - 1 //小于0的索引，左边小于等于0 ，右边大于0

      //由于每次一次调用都需要生成新的List，所以currentList这里不能使用ListBuffer
      def fourSumN(startIndex: Int, currentTotal: Int, nowSize: Int, currentList: List[Int]): Unit = {
        val needSize = tupleSize - nowSize
        if (needSize >= 1 && needSize <= sortNums.length - startIndex) {
          val needValue = target - currentTotal
          if (needValue >= sortNums(startIndex) * needSize && needValue <= maxValues(startIndex)) {
            if (needSize == 1) {
              val tmpValue = dataMap.get(needValue)
              if (tmpValue.isDefined && needValue >= sortNums(startIndex)) { //由于比needValue小的值已经使用过，所以这里排除
                val re = currentList :+ needValue
                result += re
              }
            } else {
              val isBiggerTotal = currentTotal >= target
              Breaks.breakable {
                for (index <- startIndex until nums.length) {
                  if (isBiggerTotal && index > ltZeroIndex) {
                    //当前的值是否过大
                    Breaks.break()
                  }
                  var total = currentTotal + sortNums(index)
                  if(startIndex != index && target - total > maxValues(index)){
                    Breaks.break()
                  }
                  fourSumN(index + 1, total, nowSize + 1, currentList :+ sortNums(index))
                }
              } // end breable
            }//need size
          }
        }
      }
      fourSumN(0, 0, 0, List[Int]())
      //去重复
      result.toList.distinct
      //result
    }
  }


  /**
    * 1.fourSum2 中判断的是needValue >= sortNums(startIndex) * needSize && needValue <= maxValue * needSize 作为过滤条件
    *   这个是属于一个模糊判断，但是只需要计算1步奏
    * 2. 我们可以通过当前值、数量与最大最小值计算得到当前的startIndex与endIndex，然后在其中循环来提高效率
    * @param nums
    * @param target
    * @return
    */
  def fourSum3(nums: Array[Int], target: Int): List[List[Int]] = {
    val tupleSize = 4
    if(nums.length == 0 || nums.length < tupleSize){
      List[List[Int]]()
    }else {
      val sortNums = nums.sorted
      var result = ListBuffer[List[Int]]()
      var maxValues = new Array[Long](sortNums.length)
      var tmpValue = 0L
      val stopIndex = sortNums.length - tupleSize
      for(i <- sortNums.length - 1 to 0 by -1){
        if(i >= stopIndex){
          tmpValue = tmpValue +  sortNums(i)
        }
        maxValues(i) = tmpValue
      }
      maxValues = maxValues.reverse
      val dataMapTmp = nums.groupBy(a => a).map(it => (it._1, it._2.length))
      val dataMap = new mutable.HashMap[Int, Int]()
      dataMapTmp.foreach(it => dataMap += it)
      val ltZeroIndex = sortNums.prefixLength(it => it <= 0) - 1 //小于0的索引，左边小于等于0 ，右边大于0

      //由于每次一次调用都需要生成新的List，所以currentList这里不能使用ListBuffer
      def fourSumN(startIndex: Int, currentTotal: Int, nowSize: Int, currentList: List[Int]): Unit = {
        val needSize = tupleSize - nowSize
        if (needSize >= 1 && needSize <= sortNums.length - startIndex) {
          val needValue = target - currentTotal
          if (needValue >= sortNums(startIndex) * needSize && needValue <= maxValues(startIndex)) {
            if (needSize == 1) {
              val tmpValue = dataMap.get(needValue)
              if (tmpValue.isDefined && needValue >= sortNums(startIndex)) { //由于比needValue小的值已经使用过，所以这里排除
                val re = currentList :+ needValue
                result += re
              }
            } else {
              val isBiggerTotal = currentTotal >= target
              Breaks.breakable {
                for (index <- startIndex until nums.length) {
                  if (isBiggerTotal && index > ltZeroIndex) {
                    //当前的值是否过大
                    Breaks.break()
                  }
                  var total = currentTotal + sortNums(index)
                  if(startIndex != index && target - total > maxValues(index)){
                    Breaks.break()
                  }
                  fourSumN(index + 1, total, nowSize + 1, currentList :+ sortNums(index))
                }
              } // end breable
            }//need size
          }
        }
      }
      fourSumN(0, 0, 0, List[Int]())
      //去重复
      result.toList.distinct
      //result
    }
  }
}
