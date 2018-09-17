package com.tingfeng.exercise;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba"也是一个有效答案。
 * 示例 2：
 *
 * 输入: "cbbd"
 * 输出: "bb"
 */
object Ex005 {

    def main(args: Array[String]): Unit = {
        val sArray = Array("babad","cbbd","a","aa");
        sArray.foreach(it => {
            println(longestPalindrome(it))
        })
    }

    def longestPalindrome(s: String): String = {
        def find(strs: Array[Char],position: Int):Tuple2[Int,Int]={
            var result:Tuple2[Int,Int] = null
            if(position == 0 || position == strs.length - 1){
                result = (position,position)
            }else{
                var maxLength = Math.min(position + 1,strs.length - position) * 2
                var length = 1
                var isContinue = true
                var paType = 0 // 0 = 偶数方式abb格式，1 = 奇数方式，2=偶数方式bbc
                if(strs(position - 1) == strs(position)){
                    paType = 0
                }else if(strs(position + 1) == strs(position)){
                    paType = 2
                }else{
                    paType = 1
                }
                if(paType == 1) {
                    //以奇数方式统计
                    for (i <- 1 until maxLength / 2 if isContinue) {
                        if (strs(position - i) == strs(position + i)) {
                            length += 2
                        } else {
                            isContinue = false
                        }
                    }
                }else if(paType == 2){
                    result = find(strs,position - 1)
                }else{
                    length = 2
                    maxLength = maxLength - 1
                    //以奇数方式统计
                    for (i <- 1 until maxLength / 2  if isContinue) {
                        if (strs(position - i) == strs(position + i + 1)) {
                            length += 2
                        } else {
                            isContinue = false
                        }
                    }
                }
                if(paType !=2 ){
                    if(paType == 0){
                        result = (position - length / 2 - 1,position + length / 2 + 1)
                    }else if(paType == 1){
                        result = (position - length / 2,position + length / 2)
                    }
                    if(length < maxLength){
                        var leftLength = 0
                        var rightRight = 0
                        var resultR = (position,position)
                        var resultL = (position,position)
                        if(position >= strs.length / 2){
                            resultR = find(strs,position + 1)
                            rightRight = resultR._2 - resultR._1 + 1
                        }else if(position <= strs.length / 2){
                            resultL = find(strs,position - 1)
                            leftLength = resultL._2 - resultL._1 + 1
                        }
                        if(leftLength > length && leftLength >= rightRight){
                            result = resultL
                        }else if(rightRight > length && rightRight > leftLength){
                            result = resultR
                        }
                    }
                }
            }
            result
        }
        val re = find(s.toCharArray,s.length / 2 )
        s.substring(re._1,re._2 + 1)
    }
}
