package com.example.beatbox.domain.model

private const val SOUND_FORMAT = ".wav"

//TODO Add color transient
class Sound(val assetPath:String, var soundId:Int? = null) {

    val name = assetPath.split("/").last().removeSuffix(SOUND_FORMAT)
}