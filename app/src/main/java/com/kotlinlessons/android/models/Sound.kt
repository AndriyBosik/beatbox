package com.kotlinlessons.android.models

class Sound(var assetPath: String) {

    var name: String
        private set
    var soundId: Int? = null

    init {
        val components = assetPath.split("/")
        val filename = components.last()
        name = filename.replace(".wav", "")
    }

}