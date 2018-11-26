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


  def main(args: Array[String]): Unit = {
      val arrays = List(Array(1, 0, -1, 0, -2, 2),
                        Array(1, 0, 0, 0,5,3,-4,-7,9,-2,5, -2, 2));
      arrays.foreach(it =>{
        println(fourSum(it,0))
      })

  }

  def fourSum(nums: Array[Int], target: Int): List[List[Int]] = {
      val tupleSize = 4
      val sortNums = nums.sorted
      var result = List[List[Int]]()

      def fourSumN(startIndex: Int,currentTotal: Int,nowSize: Int,currentList:List[Int]): Unit = {
        for(index <- startIndex until nums.length){
            var total = currentTotal + sortNums(index)
            val needSize = tupleSize - nowSize
            needSize match {
                case 1 => {
                  if(total == target){
                    val re = currentList :+ sortNums(index)
                    result = result :+ re
                  }
                }
                case a:Int if a > 0 => {
                     if(total <= target){
                       fourSumN(index + 1,total,nowSize + 1,currentList :+ sortNums(index))
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
