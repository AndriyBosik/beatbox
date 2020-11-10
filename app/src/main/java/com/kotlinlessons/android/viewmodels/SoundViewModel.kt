package com.kotlinlessons.android.viewmodels

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.kotlinlessons.android.models.BeatBox
import com.kotlinlessons.android.models.Sound

class SoundViewModel(private var beatBox: BeatBox): BaseObservable() {

    var sound: Sound? = null
        @Bindable set(value) {
            field = value
            notifyChange()
        }

    val title: String
        @Bindable get() = sound!!.name

}