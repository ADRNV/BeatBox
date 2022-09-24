package com.example.beatbox.data

import android.content.res.AssetManager
import android.media.SoundPool
import android.util.Log
import com.example.beatbox.domain.model.Sound
import java.io.IOException

private const val TAG = "BeatBox"

private const val SOUNDS_FOLDER = "sample_sounds"

private const val MAX_SOUNDS = 10

class BeatBox(private val assets:AssetManager) {

    private val soundPool = SoundPool.Builder()
        .setMaxStreams(MAX_SOUNDS)
        .build()

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

            try {

                load(sound)

                sounds.add(sound)

            }catch (ioException:IOException){
                Log.d(TAG, ioException.toString(), ioException)
            }
        }

        return sounds
    }

    private fun load(sound: Sound){

        val afd = assets.openFd(sound.assetPath)

        val soundId = soundPool.load(afd, 1)

        sound.soundId = soundId
    }

    fun play(sound: Sound){

        sound.soundId?.let {

            soundPool.play(it, 1.0f, 1.0f, 1, 0, 1.0f)

        }

    }

    fun release(){

        soundPool.release()

    }
}
