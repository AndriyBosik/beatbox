package com.kotlinlessons.android.models

import android.content.Context
import android.util.Log
import java.io.IOException

class BeatBox(context: Context) {

    companion object {
        private const val TAG = "BeatBox"

        private const val SOUNDS_FOLDER = "sample_sounds"
    }

    private val assets = context.assets
    var sounds: ArrayList<Sound>
        private set

    init {
        sounds = ArrayList()
        loadSounds()
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
            val assetPath = "$SOUNDS_FOLDER/$filename"
            val sound = Sound(assetPath)
            sounds.add(sound)
        }
    }

}