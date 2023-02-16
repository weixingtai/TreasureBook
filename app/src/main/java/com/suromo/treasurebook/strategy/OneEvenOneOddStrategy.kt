package com.suromo.treasurebook.strategy

import kotlin.math.E

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/2/17
 * desc   :
 */
class OneEvenOneOddStrategy : BaseStrategy(),ITreasureStrategy {

    private lateinit var historyList: List<Int>
    private lateinit var periodList: List<Int>
    private var comparedList: MutableList<Boolean> = mutableListOf()

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
        for(index in periodList.indices){
            if (index % 2 == 0){
                comparedList.add(isEven(periodList[index]))
            } else {
                comparedList.add(isOdd(periodList[index]))
            }
        }
    }

    override fun getComparedResultList(): List<Boolean> {
        return comparedList
    }

    override fun getMissPhaseNum(): Int {
        return comparedList.indexOf(false)+1
    }

    override fun getNextPrediction(): Int {
        return EVEN
    }

}