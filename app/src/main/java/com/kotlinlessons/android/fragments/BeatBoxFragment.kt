package com.kotlinlessons.android.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kotlinlessons.android.R
import com.kotlinlessons.android.models.BeatBox
import com.kotlinlessons.android.databinding.FragmentBeatBoxBinding
import com.kotlinlessons.android.databinding.ListItemSoundBinding
import com.kotlinlessons.android.models.Sound
import com.kotlinlessons.android.viewmodels.SoundViewModel

class BeatBoxFragment: Fragment() {

    companion object {
        fun newInstance(): BeatBoxFragment = BeatBoxFragment()
    }

    private lateinit var beatBox: BeatBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        beatBox = BeatBox(activity as Context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_beat_box, container, false) as FragmentBeatBoxBinding
        binding.recyclerView.layoutManager = GridLayoutManager(activity, 3)
        binding.recyclerView.adapter = SoundAdapter(beatBox.sounds)
        return binding.root
    }

    private inner class SoundHolder(private var binding: ListItemSoundBinding):
        RecyclerView.ViewHolder(binding.root)
    {
        init {
            binding.viewModel = SoundViewModel(beatBox)
        }

        fun bind(sound: Sound) {
            binding.viewModel!!.sound = sound
            binding.executePendingBindings()
        }
    }

    private inner class SoundAdapter(private var sounds: ArrayList<Sound>): RecyclerView.Adapter<SoundHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundHolder {
            val inflater = LayoutInflater.from(activity as Context)
            val binding: ListItemSoundBinding =
                DataBindingUtil.inflate(inflater, R.layout.list_item_sound, parent, false)
            return SoundHolder(binding)
        }

        override fun getItemCount(): Int = sounds.size

        override fun onBindViewHolder(holder: SoundHolder, position: Int) {
            val sound = sounds[position]
            holder.bind(sound)
        }

    }

}