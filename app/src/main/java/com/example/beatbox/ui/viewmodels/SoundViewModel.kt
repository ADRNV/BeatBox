package com.example.beatbox.ui.viewmodels

import android.os.*
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.beatbox.data.BeatBox
import com.example.beatbox.domain.model.Sound

class SoundViewModel(private val beatBox: BeatBox) : ViewModel() {

    var sound:Sound? = null
    set(value) {
        field = value
        title.postValue(sound?.name)
    }

    val title:MutableLiveData<String?> = MutableLiveData()

    @RequiresApi(Build.VERSION_CODES.S)
    fun onClick(){
        sound?.let {
            beatBox.play(it)
        }
    }

    override fun onCleared() = beatBox.release()

}