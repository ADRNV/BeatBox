package com.example.beatbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beatbox.databinding.ActivityMainBinding
import com.example.beatbox.databinding.SoundButtonItemBinding
import com.example.beatbox.data.BeatBox
import com.example.beatbox.domain.model.Sound

class MainActivity : AppCompatActivity() {

    private lateinit var beatBox: BeatBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        beatBox = BeatBox(assets)

        val binding:ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.soundButtonsRv.apply {

            layoutManager = GridLayoutManager(context, 3)

            adapter = SoundListAdapter().apply {
                setSource(beatBox.sounds)
            }

        }
    }

    inner class SoundListAdapter : RecyclerView.Adapter<SoundListAdapter.SoundViewHolder>() {

        private lateinit var _sounds:List<Sound>

        fun setSource(sounds:List<Sound>){

            _sounds = sounds

            notifyDataSetChanged()

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundViewHolder {

            val binding = DataBindingUtil.inflate<SoundButtonItemBinding>(
                layoutInflater,
                R.layout.sound_button_item,
                parent,
                false)

            return when(viewType){

                1 -> {
                    val evenColor = resources.getColor(R.color.amethyst)
                    binding.soundButton.setBackgroundColor(evenColor)
                    SoundViewHolder(binding)
                }
                else -> {
                    SoundViewHolder(binding)
                }

            }
        }

        override fun onBindViewHolder(holder: SoundViewHolder, position: Int) {

            val sound = _sounds[position]

            holder.bind(sound)

        }

        override fun getItemViewType(position: Int): Int {

            return if(position % 2 > 0){
                0
            }else{
                1
            }
        }

        override fun getItemCount() = _sounds.size

        inner class  SoundViewHolder(private val binding: SoundButtonItemBinding):
            RecyclerView.ViewHolder(binding.root){

            fun bind(sound: Sound){
                binding.soundButton.text = sound.name
            }
        }
    }
}