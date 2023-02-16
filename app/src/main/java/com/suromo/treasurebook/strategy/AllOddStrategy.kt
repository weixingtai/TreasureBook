package com.suromo.treasurebook.strategy

/**
 * author : Samuel
 * e-mail : weixingtai@meizu.com
 * time   : 2023/2/16 下午7:24
 * desc   :
 */
class AllOddStrategy : BaseStrategy(),ITreasureStrategy {

    private lateinit var historyList: List<Int>
    private lateinit var periodList: List<Int>
    private lateinit var comparedList: MutableList<Boolean>

    override fun initOpenHistory(historyList: List<Int>) {
        this.historyList = historyList
    }

    override fun initPeriod(period: Int) {
        periodList = historyList.takeLast(period)
    }

    override fun getPeriodList(): List<Int> {
        return periodList
    }


    override fun runStrategy() {
        for(item in periodList){
            comparedList.add(isOdd(item))
        }
    }

    override fun getComparedResultList(): List<Boolean> {
        return comparedList
    }

    override fun getMissPhaseNum(): Int {
        return comparedList.indexOf(true)+1
    }

    override fun getNextPrediction(): Int {
        return EVEN
    }

}