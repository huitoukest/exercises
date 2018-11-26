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
      val nums1:Array[Int] = Array(1,5,6,7)
      val nums2:Array[Int] = Array(2,3,4,8,9)
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
      val midIndex2 = findMIndex(index2)
      var bigLength = index1._2 - index1._1 + 1
      var smallLength = index2._2 - index2._1 + 1
      if(bigLength < smallLength){
        return findM(array2,array1,index2,index1)
      }
      var result : Tuple2[Int,Int] = null
      var midInx : Tuple2[Int,Int] = null
      val isOdd = ((bigLength & 1 ) ^ (smallLength & 1)) == 1 //
      val halfLength = (smallLength + bigLength) >> 1
      val moveLength = (smallLength >> 1) //移动的长度是每次变为上次的1/2
      if(array1.isEmpty || bigLength <= 0){
        result = (array2(midIndex2._1),array2(midIndex2._2))
      }else if(array2.isEmpty || smallLength <= 0) {
        result = (array1(midIndex1._1),array1(midIndex1._2))
      }else if(halfLength == 0) {
         if(bigLength <= 0){
           result = (array2(0),array2(0))
         }else{
           result = (array1(0),array1(0))
         }
      }else if(halfLength == 1) {
         if(isOdd){//如果是奇数
             if(array1(midIndex1._1) <= array2(index2._2) && array1(midIndex1._2) >= array2(index2._2)) {
               result = (array2(index2._2),array2(index2._2))
             }else if(array2(index2._2) <= array1(midIndex1._1)){
               result =  (array1(midIndex1._1),array1(midIndex1._1))
             }else if(array1(index1._2) <= array2(midIndex2._1)) {
               result = (array1(midIndex1._2), array1(midIndex1._2))
             }
         }else {//如果是偶数
               result = (Math.max(array1(index1._1),array2(index2._1)),Math.min(array1(index1._2),array2(index2._2)))
         }
      }else if(moveLength == 0){//短数组移动到头
            if(isOdd){
               if(array1(midIndex1._1) <= array2(index2._2) && array1(midIndex1._2) >= array2(index2._1)) {
                  result = (array2(index2._2),array2(index2._2))
               }else if(array2(index2._2) <= array1(midIndex1._1)){
                 result =  (array1(midIndex1._1),array1(midIndex1._1))
               }else if(array1(midIndex1._2) <= array2(midIndex2._1)) {
                 result = (array1(midIndex1._2), array1(midIndex1._2))
               }
            }else{
                 if(array2(midIndex2._1) <= array1(midIndex1._1) && array2(midIndex2._1) >= array1(midIndex1._1 - 1)){
                    result = (array2(midIndex2._1),array1(midIndex1._1))
                 }else if(array2(midIndex2._1) > array1(midIndex1._1) && array2(midIndex2._1) < array1(midIndex1._2 + 1)){
                   result = (array1(midIndex1._1),array2(midIndex2._1))
                 }else if(array2(midIndex2._1) > array1(midIndex1._2)){
                   result = (array1(midIndex1._1),array1(midIndex1._1  + 1))
                 }else{
                   result = (array1(midIndex1._1 - 1),array1(midIndex1._1))
                 }
               }
      }else if(moveLength > 0){
                var t = (Math.max(array1(midIndex1._1),array2(midIndex2._1)),Math.min(array1(midIndex1._2),array2(midIndex2._2)))
                if(t._1 <= t._2){
                  result = t
                }else if(array1(index1._2) <= array2(index2._1)){
                  //数组2向小，数组1向大滑动
                  result = findM(array1,array2,(index1._1 + moveLength,index1._2),(index2._1,index2._2 - moveLength))
                }else if(array1(index1._1) >= array2(index2._2)){
                  //数组2向大，数组1向小滑动
                  result = findM(array1,array2,(index1._1,index1._2 - moveLength),(index2._1 + moveLength,index2._2))
                }else if(array1(index1._1) >= array2(index2._1) && array1(index1._2) <= array2(index2._2)){//短的范围更大
                  result = findM(array1,array2,index1,(index2._1 + 1,index2._2 - 1 ))
                }else if(array1(index1._1) <= array2(index2._1) && array1(index1._2) >= array2(index2._2)){
                  result = findM(array1,array2,(index1._1 + 1,index1._2 -1),index2)
                }else if(array1(index1._1) <= array2(index2._1)){
                  result = findM(array1,array2,(index1._1 + 1,index1._2),(index2._1,index2._2 - 1))
                }else if(array1(index1._2) >= array2(index2._1)) {
                  result = findM(array1,array2,(index1._1,index1._2 - 1),(index2._1 + 1,index2._2))
                }
      }
      result
    }

    var arrayBig = nums1
    var arraySmall = nums2
    if(arrayBig.length < arraySmall.length){
      var numsTmp = arrayBig //使得arrayBig的长度始终大于等于arraySmall
      arrayBig = arraySmall
      arraySmall = numsTmp
    }
    val value = findM(arrayBig,arraySmall,(0,arrayBig.length - 1),(0,arraySmall.length - 1))
    (value._1 / 2.0 +  value._2/ 2.0) //考虑到值可能溢出，所以分别相除再相加
  }

}
