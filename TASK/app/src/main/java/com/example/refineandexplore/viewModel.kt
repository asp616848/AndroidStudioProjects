package com.example.refineandexplore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.refineandexplore.data.UiState

class viewModel : ViewModel(){
    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState
}