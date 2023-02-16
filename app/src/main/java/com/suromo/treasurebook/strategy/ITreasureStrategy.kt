package com.suromo.treasurebook.strategy

/**
 * author : Samuel
 * e-mail : weixingtai@meizu.com
 * time   : 2023/2/16 下午7:27
 * desc   :
 */
interface ITreasureStrategy {

    fun initOpenHistory(historyList: List<Int>)

    fun initPeriod(period: Int)

    fun getPeriodList(): List<Int>

    fun runStrategy()

    fun getComparedResultList(): List<Boolean>

    fun getMissPhaseNum():Int

    fun getNextPrediction():Int

}