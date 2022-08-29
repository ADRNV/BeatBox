package com.example.beatbox.domain.model

import android.graphics.Color

private const val SOUND_FORMAT = ".wav"

//TODO Add color transient
class Sound(private val assetPath:String) {

    val name = assetPath.split("/").last().removeSuffix(SOUND_FORMAT)
}