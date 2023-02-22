package com.suromo.treasurebook.view.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.suromo.network.RequestHomeViewModel
import com.suromo.treasurebook.R
import com.suromo.treasurebook.databinding.FragmentHomeBinding
import com.suromo.treasurebook.strategy.AllEvenStrategy
import com.suromo.treasurebook.strategy.ITreasureStrategy
import com.suromo.treasurebook.viewmodel.MarkSixViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: MarkSixViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val requestHomeViewModel: RequestHomeViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("wxt","viewModel.markSixList:"+viewModel.markSixList)
        requestHomeViewModel.getMarSix()

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