package com.tingfeng.exercise

import com.tingfeng.excommon.TestUtil

import scala.collection.mutable

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
      val arrays = List( Array(1, 0, -1, 0, -2, 2),
                        Array(1,-2,-5,-4,-3,3,3,5));
      /*arrays.foreach(it =>{
        println(fourSum(it,-11))
      })*/
     /* TestUtil.printTime(1,500000,index =>{
        fourSum(arrays(0),-11)
      });*/
    println(fourSum2(arrays(0),0))
    println(fourSum2(arrays(1),-11))
    TestUtil.printTime(1,500000,index =>{
      fourSum2(arrays(0),-11)
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
      var result = List[List[Int]]()
      val ltZeroIndex = sortNums.prefixLength( it => it <= 0) - 1 //小于0的索引，左边小于等于0 ，右边大于0
      def fourSumN(startIndex: Int,currentTotal: Int,nowSize: Int,currentList:List[Int]): Unit = {
        var isContinue = true
        for(index <- startIndex until nums.length if isContinue){
            var total = currentTotal + sortNums(index)
            val bigger  =  total >= target && index >= ltZeroIndex
            isContinue = !bigger
            val needSize = tupleSize - nowSize
            needSize match {//距离指定的x长度的数组还差的长度
                case 1 => {
                  if(total == target){
                    val re = currentList :+ sortNums(index)
                    result = result :+ re
                  }
                }
                case a:Int if a > 0 => {
                     //这里存在的问题是：
                       //如果total >= target，而且右边的值是大于0的并且会越来越大，那么就不可取
                       //而如果total <= target 但是，index却在小于ltZeroIndex的地方确实可取的，因为后面的值会越来越大，最终有可能将之和为target
                       fourSumN(index + 1,total,nowSize + 1,currentList :+ sortNums(index))
                }
              }
        }
      }
    fourSumN(0,0,0,List[Int]())
    //去重复
    result.distinct.toList
  }

  /**
    * 1.从总数一次减去当前值，剩下的值必须是大于等于最小值，小于等于最大值时才进行下一步
    * 2.最后剩余一个值的时候可以使用HashMap来获取，在HashMap中key是当前的值，value是当前值的数量
    * @param nums
    * @param target
    * @return
    */
  def fourSum2(nums: Array[Int], target: Int): List[List[Int]] = {
    val tupleSize = 4
    val sortNums = nums.sorted
    var result = List[List[Int]]()
    val ltZeroIndex = sortNums.prefixLength( it => it <= 0) - 1 //小于0的索引，左边小于等于0 ，右边大于0
    val maxValue = sortNums(nums.length - 1)
    val dataMapTmp = nums.groupBy(a => a).map(it => (it._1,it._2.length))
    val dataMap = new mutable.HashMap[Int,Int]()
    dataMapTmp.foreach(it => dataMap += it)
       /// dataMap +=   nums.groupBy(a => a).map(it => (it._1,it._2.length))
    def fourSumN(startIndex: Int,currentTotal: Int,nowSize: Int,currentList:List[Int]): Unit = {
      if(nowSize < tupleSize){
        val needValue = currentTotal - sortNums(startIndex)
        if(nowSize == tupleSize - 1){
          val tmpValue = dataMap.get(needValue)
          if(tmpValue.isDefined && tmpValue.get > 0){
            val re = currentList :+ tmpValue.get
            result = result :+ re
            //dataMap(needValue) = needValue - 1
          }
        }else{
          //val bigger  =  currentTotal <=0 && startIndex >= ltZeroIndex
          //for(index <- startIndex until nums.length){
          //if(!bigger){
            fourSumN(startIndex + 1,needValue,nowSize + 1,currentList :+ sortNums(startIndex))
          //}
          //}
        }
      }
    }
    fourSumN(0,target,0,List[Int]())
    //去重复
    result.distinct.toList
  }

}
