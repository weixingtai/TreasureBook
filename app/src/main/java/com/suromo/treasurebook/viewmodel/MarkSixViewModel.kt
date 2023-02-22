package com.suromo.treasurebook.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.suromo.treasurebook.repository.MarkSixRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/2/23
 * desc   :
 */
@HiltViewModel
class MarkSixViewModel @Inject internal constructor(
    savedStateHandle: SavedStateHandle,
    markSixRepository: MarkSixRepository
) : ViewModel() {
    private var queryString: String? = savedStateHandle["plantName"]

    val markSixList = markSixRepository.getMarkSixList().asLiveData()

    init {

    }
}