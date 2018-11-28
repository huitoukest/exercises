package com.tingfeng.exercise

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
      val arrays = List( //Array(1, 0, -1, 0, -2, 2),
                        Array(1,-2,-5,-4,-3,3,3,5));
      arrays.foreach(it =>{
        println(fourSum(it,-11))
      })

  }

  def fourSum(nums: Array[Int], target: Int): List[List[Int]] = {
      val tupleSize = 4
      val sortNums = nums.sorted
      var result = List[List[Int]]()
      val ltZeroIndex = sortNums.prefixLength( it => it < 0) - 1 //小于0的索引，左边小于0 ，右边大于等于0
      def fourSumN(startIndex: Int,currentTotal: Int,nowSize: Int,currentList:List[Int]): Unit = {
        for(index <- startIndex until nums.length){
            var total = currentTotal + sortNums(index)
            val needSize = tupleSize - nowSize
            needSize match {//距离指定的x长度的数组还差的长度
                case 1 => {
                  if(total == target){
                    val re = currentList :+ sortNums(index)
                    result = result :+ re
                  }
                }
                case a:Int if a > 0 => {
                     if(total <= target && index >= ltZeroIndex){
                       fourSumN(index + 1,total,nowSize + 1,currentList :+ sortNums(index))
                     }else if(total >= target && index <= ltZeroIndex){
                       fourSumN(index + 1,total,nowSize + 1,currentList :+ sortNums(index))
                     }else if(expectArray(nowSize) == sortNums(index)){
                       print()
                     }
                }
              }
        }
      }
    fourSumN(0,0,0,List[Int]())
    //去重复
    result.distinct
  }

}
