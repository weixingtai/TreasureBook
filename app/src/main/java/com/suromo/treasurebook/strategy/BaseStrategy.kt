package com.suromo.treasurebook.strategy

/**
 * author : Samuel
 * e-mail : weixingtai@meizu.com
 * time   : 2023/2/16 下午7:22
 * desc   :
 */

const val EVEN = 1
const val ODD = -1
const val DRAW = 0

open class BaseStrategy {


    var phase: Int = 7

    fun isEven(num: Int) : Boolean{
        return num % 2 == 0
    }

    fun isOdd(num: Int) : Boolean{
        return num % 2 == 1
    }


}