package com.kotlinlessons.android.models

import android.content.Context
import android.media.AudioManager
import android.media.SoundPool
import android.util.Log
import java.io.IOException

open class BeatBox(context: Context) {

    companion object {
        private const val TAG = "BeatBox"
        private const val MAX_SOUNDS = 5
        private const val SOUNDS_FOLDER = "sample_sounds"
    }

    private val assets = context.assets
    var sounds: ArrayList<Sound>
        private set
    private var soundPool: SoundPool

    init {
        sounds = ArrayList()
        soundPool = SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0)
        loadSounds()
    }

    fun play(sound: Sound) {
        val soundId = sound.soundId ?: return
        soundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f)
    }

    fun release() {
        soundPool.release()
    }

    private fun loadSounds() {
        var soundNames: Array<String>?
        try {
            soundNames = assets.list(SOUNDS_FOLDER)
            Log.i(TAG, "Found ${soundNames?.size} sounds")
        } catch (ioe: IOException) {
            Log.e(TAG, "Could not list assets", ioe)
            return
        }
        for (filename in soundNames!!) {
            try {
                val assetPath = "$SOUNDS_FOLDER/$filename"
                val sound = Sound(assetPath)
                load(sound)
                sounds.add(sound)
            } catch (ioe: IOException) {
                Log.e(TAG, "Could not load sound $filename", ioe)
            }
        }
    }

    @Throws(IOException::class)
    private fun load(sound: Sound) {
        val afd = assets.openFd(sound.assetPath)
        val soundId = soundPool.load(afd, 1)
        sound.soundId = soundId
    }

}