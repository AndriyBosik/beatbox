package com.kotlinlessons.android

import com.kotlinlessons.android.fragments.BeatBoxFragment

class BeatBoxActivity : SingleFragmentActivity() {

    override fun createFragment() = BeatBoxFragment.newInstance()

}