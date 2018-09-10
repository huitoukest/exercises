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
      val nums1 = Array(1,3,5)
      val nums2 = Array(4)
      print(findMedianSortedArrays(nums1,nums2))
  }


  def findMedianSortedArrays(nums1: Array[Int], nums2: Array[Int]): Double = {
    /**
      * 传入起始索引值，查找数组的中位值的索引
      */
    def findMIndex(tuple2: Tuple2[Int,Int]): Tuple2[Int,Int] ={
        val v = tuple2._2 + tuple2._1
        val midx = v >> 1 // （和v除以2）
        if((v & 1) == 0){//差是偶数，个数是奇数
          (midx,midx)
        }else{//个数是偶数个
          (midx,midx + 1)
        }
    }

    /**
      * 查找并返回两个中位数的值,核心思想是找到短的那个数组，每次移动其长度的1/2距离
      * @param array1
      * @param array2
      * @param index1 数组1中查找前后范围的索引
      * @param index2 数组2中朝朝前后范围的索引
      * @return
      */
      def findM(array1: Array[Int],array2: Array[Int],index1: Tuple2[Int,Int],index2:Tuple2[Int,Int]):Tuple2[Int,Int]={
          val midIndex1 = findMIndex(index1)
          val minIndex2 = findMIndex(index2)

          val t = (Math.max(array1(midIndex1._1),array2(minIndex2._1)),Math.min(array1(midIndex1._2),array2(minIndex2._2)))
          if(t._1 <= t._2){//已经找到了合适的值
              t
          }else{
              var bigLength = index1._2 - index1._1 + 1
              var smallLength = index2._2 - index2._1 + 1
              var result : Tuple2[Int,Int] = null
              var midInx : Tuple2[Int,Int] = null
              //index2._1 < index2._2 &&
              if(array2(index2._2) <= array1(index1._1)){
                //短数组的最大值小于长数组的最小值
                val endLength = index1._2 - smallLength
                if(endLength >= 0) {
                  midInx = findMIndex(index1._1, endLength)
                  result = (array1(midInx._1), array1(midInx._2))
                }else{//长度相等的情况
                  result = (array1(index1._2), array2(index2._1))
                }
              }else if(array2(index2._1) >= array1(index1._2)) {//index2._1 < index2._2 &&
                //短数组的最小值大于长数组的最大值
                val startIndex = index1._1 + smallLength
                if(startIndex >= array1.length){
                  result = (array1(index1._2), array2(index2._1))
                }else{
                  midInx = findMIndex(startIndex,index1._2)
                  result = (array1(midInx._1),array1(midInx._2))
                }
              }else{
                  val moveLength = (smallLength >> 1) //移动的长度是每次变为上次的1/2
                  if(array1(index1._2) < array2(index2._2)){
                    //数组2向小，数组1向大滑动
                    result = findM(array1,array2,(index1._1 + moveLength,index1._2),(index2._1,index2._2 - moveLength))
                  }else if(array1(index1._1) > array2(index2._1)){
                    //数组2向大，数组1向小滑动
                    result = findM(array1,array2,(index1._1,index1._2 - moveLength),(index2._1 + moveLength,index2._2))
                  }
              }
              result
              /*if(smallLength == 0){//

              }
              /*if(bigLength < smallLength){//使得bigLength的长度始终大于等于smallLength
                bigLength   = bigLength ^ smallLength
                smallLength = smallLength ^ bigLength
                bigLength   = smallLength ^ bigLength
              }*/
              val allLength = array1.length + array2.length
              val halfLenth = allLength / 2
              val length1 = index1._2 - index1._1
              val length2 = index2._2 - index2._1
*/
          }
      }
    var arrayBig = nums1
    var arraySmall = nums2
    if(arrayBig.length < arraySmall.length){
      var numsTmp = arrayBig //使得arrayBig的长度始终大于等于arraySmall
      arrayBig = arraySmall
      arraySmall = arrayBig
    }
    val value = findM(nums1,nums2,(0,nums1.length - 1),(0,nums2.length - 1))
    (value._1 / 2.0 +  value._2/ 2.0) //考虑到值可能溢出，所以分别相除再相加
  }



}
