package com.example.beatbox.domain.model

import android.graphics.Color

private const val SOUND_FORMAT = ".wav"

//TODO Add color transient
class Sound(val assetPath:String, var soundId:Int? = null) {

    val name = assetPath.split("/").last().removeSuffix(SOUND_FORMAT)
}