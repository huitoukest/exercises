package com.tingfeng.exercise

/**
  *
  删除排序数组中的重复项
  给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
  不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
  */
object Ex026 {

  def main(args: Array[String]): Unit = {
    val array = Array(0,0,1,1,1,2,2,3,3,4)
    val length = removeDuplicates2(array)
    println(length)
    array.foreach(it => if(null != it) print(s"$it,"))
  }

  def removeDuplicates(nums: Array[Int]): Int = {
      val length =  nums.length
      if(null == nums || length == 0){
        0
      }else {
        var size = 0
        var lastIndex = -1
        var currentIndex = 0
        while(currentIndex < length){
          if(lastIndex == -1 || nums(lastIndex) != nums(currentIndex)){
            size += 1
            lastIndex += 1
            nums(lastIndex) = nums(currentIndex)
          }
          currentIndex += 1

        }
        size
      }
  }


  def removeDuplicates2(nums: Array[Int]): Int = {
    val length =  nums.length
    if(null == nums || length == 0){
      0
    }else {
      var size = 0
      for(i <- 1 until length){
         if(nums(size) != nums(i)){
           size += 1
           nums(size) = nums(i)
         }
      }
      size + 1
    }
  }
}
