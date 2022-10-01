package com.example.beatbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.util.toHalf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beatbox.databinding.ActivityMainBinding
import com.example.beatbox.databinding.SoundButtonItemBinding
import com.example.beatbox.data.BeatBox
import com.example.beatbox.domain.model.Sound
import com.example.beatbox.ui.viewmodels.SoundBoardViewModel
import com.example.beatbox.ui.viewmodels.SoundViewModel
import com.example.beatbox.ui.viewmodels.factories.SoundBoardViewModelFactory
import com.example.beatbox.ui.views.FloatSeekBar
import com.google.android.material.snackbar.Snackbar
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private lateinit var beatBox: BeatBox

    private lateinit var soundBoardViewModelFactory:SoundBoardViewModelFactory

    private val soundBoardViewModel by lazy {
        ViewModelProvider(this, soundBoardViewModelFactory)[SoundBoardViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        beatBox = BeatBox(assets)

        soundBoardViewModelFactory = SoundBoardViewModelFactory(beatBox)

        val binding:ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.soundSpeedSb.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                seekBar as FloatSeekBar
                soundBoardViewModel.rate.postValue(seekBar.value)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) = Unit

            override fun onStopTrackingTouch(seekBar: SeekBar?){

                seekBar as FloatSeekBar

                val message = resources.getString(R.string.x_speed,
                    seekBar.value)

                Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT)
                    .show()
            }
        })

        soundBoardViewModel.rate.observe(this){
            soundBoardViewModel.beatBox.playParameters.rate = it
            binding.speedShowTv.text = resources.getString(R.string.x_speed, it)
        }

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

            binding.lifecycleOwner = this@MainActivity

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

                binding.apply {

                    viewmodel = SoundViewModel(beatBox)

                    viewmodel?.sound = sound

                    executePendingBindings()
                }
            }
        }
    }
}