package com.kotlinlessons.android.viewmodels

import com.kotlinlessons.android.models.BeatBox
import com.kotlinlessons.android.models.Sound
import org.junit.Before

import org.hamcrest.CoreMatchers.*

import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*

class SoundViewModelTest {

    @Mock
    private lateinit var beatBox: BeatBox

    private lateinit var sound: Sound
    private lateinit var subject: SoundViewModel

    @Before
    fun setUp() {
        beatBox = mock(BeatBox::class.java)
        sound = Sound("assetPath")
        subject = SoundViewModel(beatBox)
        subject.sound = sound
    }

    @Test
    fun exposesSoundNameAsTitle() {
        assertThat(subject.title, `is`(sound.name))
    }

    @Test
    fun callsBeatBoxPlayOnButtonClicked() {
        subject.onButtonClicked()
        verify(beatBox).play(sound)
    }

}