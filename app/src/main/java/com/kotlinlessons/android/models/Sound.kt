package com.kotlinlessons.android.models

class Sound(var assetPath: String) {

    var name: String
        private set

    init {
        val components = assetPath.split("/")
        val filename = components.last()
        name = filename.replace(".wav", "")
    }

}