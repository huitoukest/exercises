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
      print(findMedianSortedArrays2(nums1,nums2))
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
          var result : Tuple2[Int,Int] = null
          var midInx : Tuple2[Int,Int] = null
          val moveLength = (smallLength >> 1) //移动的长度是每次变为上次的1/2
          if(index2._1 == 0)


          if(moveLength <= 0){//如果只有一个数据了，即移动到头了
            if(bigLength == 1){
              result = (Math.min(array1(index1._1),array2(index2._1)),Math.max(array1(index1._1),array2(index2._1)))
            }else if((bigLength & 1) == 1){//如果长数组是奇数，则整体是偶数
              result = findM(array1,array2,(index1._1,index1._2 - 1),(index2._1,index2._2))
            }else {//如果长数组是偶数，则整体是奇数
              if(array2(index1._1) >= array1(midIndex1._1) && array2(index1._1) <= array1(midIndex1._2)){
                result = (array2(index1._1),array1(index1._1))
              }else{
                result=  (array1(midIndex1._1),array1(midIndex1._2))
              }
            }
          }else{
            val t = (Math.max(array1(midIndex1._1),array2(midIndex2._1)),Math.min(array1(midIndex1._2),array2(midIndex2._2)))
            if(t._1 <= t._2){//如果中位值符合条件，则表示已经找到了合适的值
                result = t
            }else{
              //index2._1 < index2._2 &&
              if(array2(index2._2) <= array1(index1._1)){
                //短数组的最大值小于长数组的最小值，短数组的全体值更小
                val endLength = index1._2 - smallLength
                if(endLength >= 0) {
                  midInx = findMIndex(index1._1, endLength)
                  result = (array1(midInx._1), array1(midInx._2))
                }else{//长度相等的情况
                  result = (array1(index1._2), array2(index2._1))
                }
              }else if(array2(index2._1) >= array1(index1._2)) {//index2._1 < index2._2 &&
                //短数组的最小值大于长数组的最大值，短数组的值更大
                val startIndex = index1._1 + smallLength
                if(startIndex >= array1.length){
                  result = (array1(index1._2), array2(index2._1))
                }else{
                  midInx = findMIndex(startIndex,index1._2)
                  result = (array1(midInx._1),array1(midInx._2))
                }
              }else{//如果有交集
                    if(array1(midIndex1._2) < array2(midIndex2._2)){
                      //数组2向小，数组1向大滑动
                      result = findM(array1,array2,(index1._1 + moveLength,index1._2),(index2._1,index2._2 - moveLength))
                    }else if(array1(midIndex1._1) > array2(midIndex2._1)){
                      //数组2向大，数组1向小滑动
                      result = findM(array1,array2,(index1._1,index1._2 - moveLength),(index2._1 + moveLength,index2._2))
                    }else{
                      //如果是其余的情况，则长数组向中间滑动
                      result = findM(array1,array2,(index1._1 + 1,index1._2 - 1),index2)
                    }
                  }
              }
          }
        result
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



  def findMedianSortedArrays2(nums1: Array[Int], nums2: Array[Int]): Double = {
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
