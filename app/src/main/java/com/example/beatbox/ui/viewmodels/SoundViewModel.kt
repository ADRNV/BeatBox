package com.example.beatbox.ui.viewmodels

import android.content.Context
import android.os.*
import android.view.View
import androidx.annotation.RequiresApi
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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
        /*
        val vibrator = view.context
            .getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager

        val vibrationEffect = VibrationEffect.createOneShot(200, 10)

        vibrator.vibrate(CombinedVibration.createParallel(vibrationEffect))
         */

        sound?.let {
            beatBox.play(it)
        }
    }

    override fun onCleared() = beatBox.release()

}