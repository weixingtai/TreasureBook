package com.suromo.treasurebook.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.suromo.logger.MLog
import com.suromo.network.impl.NetworkManager
import com.suromo.network.impl.networkRequest
import com.suromo.treasurebook.R
import com.suromo.treasurebook.databinding.FragmentHomeBinding
import com.suromo.treasurebook.strategy.AllEvenStrategy
import com.suromo.treasurebook.strategy.ITreasureStrategy
import kotlinx.coroutines.runBlocking

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val json = runBlocking {
            NetworkManager.INSTANCE.getMarkSixHistoryByYear()
        }

        MLog.d(json)


        var historyList = listOf(1,2,3,4,5,6,7,8,8,9,87,7)
        var strategy: ITreasureStrategy = AllEvenStrategy()
        strategy.initOpenHistory(historyList)
        strategy.initPeriod(7)
        binding.tvName.text = getString(R.string.strategy_one_title,getString(strategy.getStrategyName()))
        var periodList =  strategy.getPeriodList()
        binding.tvPeriod.text = "上期开奖结果："
        for (item in periodList){

        }

        binding.tvMiss.text = strategy.getMissPhaseNum().toString()

        binding.tvRecommend.text = strategy.getNextRecommend().toString()


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}