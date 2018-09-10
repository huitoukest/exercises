package com.tingfeng.exercise

/**
  *
  * 两个排序数组的中位数
  * 参考：https://blog.csdn.net/hk2291976/article/details/51107778
  * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2 。
  *
  * 请找出这两个有序数组的中位数。要求算法的时间复杂度为 O(log (m+n)) 。
  *
  * 你可以假设 nums1 和 nums2 不同时为空。
  *
  * 示例 1:
  *
  * nums1 = [1, 3]
  * nums2 = [2]
  *
  * 中位数是 2.0
  * 示例 2:
  *
  * nums1 = [1, 2]
  * nums2 = [3, 4]
  *
  * 中位数是 (2 + 3)/2 = 2.5
  */
object Ex004 {

  def main(args: Array[String]): Unit = {
      val nums1 = Array(1,3)
      val nums2 = Array(4)
      print(findMedianSortedArrays(nums1,nums2))
  }


  def findMedianSortedArrays(nums1: Array[Int], nums2: Array[Int]): Double = {

  }



}
