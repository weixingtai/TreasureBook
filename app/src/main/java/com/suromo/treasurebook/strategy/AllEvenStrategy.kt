package com.suromo.treasurebook.strategy

import com.suromo.treasurebook.R

/**
 * author : Samuel
 * e-mail : weixingtai@meizu.com
 * time   : 2023/2/16 下午7:24
 * desc   :
 */
class AllEvenStrategy : BaseStrategy(),ITreasureStrategy {

    private lateinit var historyList: List<Int>
    private lateinit var periodList: List<Int>
    private var comparedList: MutableList<Boolean> = mutableListOf()
    override fun getStrategyName(): Int {
        return R.string.strategy_all_even
    }

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
            comparedList.add(isEven(item))
        }
    }

    override fun getComparedResultList(): List<Boolean> {
        return comparedList
    }

    override fun getMissPhaseNum(): Int {
        return comparedList.indexOf(false)+1
    }

    override fun getNextRecommend(): Int {
        return EVEN
    }

}