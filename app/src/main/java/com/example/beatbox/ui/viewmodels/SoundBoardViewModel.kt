package com.example.beatbox.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.beatbox.data.BeatBox
import com.example.beatbox.domain.model.PlayParameters

class SoundBoardViewModel(val beatBox: BeatBox)
    : ViewModel() {

    private val _playParameters:PlayParameters = PlayParameters()

    val rate: MutableLiveData<Float> by lazy {
        MutableLiveData()
    }
}