package com.example.beatbox.ui.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.beatbox.data.BeatBox
import com.example.beatbox.domain.model.PlayParameters
import com.example.beatbox.ui.viewmodels.SoundBoardViewModel

class SoundBoardViewModelFactory(private val beatBox: BeatBox) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(BeatBox::class.java)
            .newInstance(beatBox)
    }
}