package com.example.beatbox.data

import android.content.res.AssetManager
import android.graphics.Color
import android.util.Log
import com.example.beatbox.domain.model.Sound

private const val TAG = "BeatBox"

private const val SOUNDS_FOLDER = "sample_sounds"

class BeatBox(private val assets:AssetManager) {

    val sounds:List<Sound> = loadSounds()

    private fun loadSounds():List<Sound>{

        val soundNames: Array<String>

        try {

            soundNames = assets.list(SOUNDS_FOLDER)!!

        } catch (e:Exception){

            Log.d(TAG, "Could not list assets", e)

            return emptyList()
        }

        val sounds = mutableListOf<Sound>()

        soundNames.forEach {

            val soundAssetPath = "$SOUNDS_FOLDER/${it}"

            val sound = Sound(soundAssetPath)

            sounds.add(sound)
        }

        return sounds
    }
}
