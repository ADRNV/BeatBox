package com.example.beatbox.ui.viewmodels

import com.example.beatbox.data.BeatBox
import com.example.beatbox.domain.model.Sound
import junit.framework.TestCase
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class SoundViewModelTest {

    private lateinit var sound:Sound
    private lateinit var subject:SoundViewModel
    private lateinit var beatBox: BeatBox

    @Before
    fun setUp() {

        beatBox = mock(BeatBox::class.java)
        sound = Sound("assetPath")
        subject = SoundViewModel(beatBox)
        subject.sound = sound
    }

    @Test
    fun exposesSoundNameAsTitle() = assertThat(subject.title, `is`(sound.name))

    @Test
    fun callBeatBoxPlayOnButtonClicked(){
        subject.onClick()

        verify(beatBox).play(sound)
    }
}