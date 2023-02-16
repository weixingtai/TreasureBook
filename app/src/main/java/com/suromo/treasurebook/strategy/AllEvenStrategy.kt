package com.suromo.treasurebook.strategy

/**
 * author : Samuel
 * e-mail : weixingtai@meizu.com
 * time   : 2023/2/16 下午7:24
 * desc   :
 */
class AllEvenStrategy : BaseStrategy(),ITreasureStrategy {

    private lateinit var historyList: List<Int>
    private lateinit var periodList: List<Int>
    private var comparedList: List<Int> = listOf(1,1,1,1,1)

    override fun initOpenHistory(historyList: List<Int>) {
        this.historyList = historyList
    }

    override fun initPeriod(period: Int) {
        periodList = historyList.takeLast(period)
    }

    override fun runStrategy() {

    }

    override fun getComparedResultList(): List<Int> {
        return comparedList
    }

    override fun getMissPhaseNum(): Int {
        return 2
    }

    override fun getNextPrediction(): Int {
        return 1
    }

}