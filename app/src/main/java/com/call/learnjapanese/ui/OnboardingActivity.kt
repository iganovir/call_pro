package com.call.learnjapanese.ui

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.call.learnjapanese.R
import com.call.learnjapanese.databinding.ActivityOnboardBinding
import com.call.learnjapanese.util.viewBinding
import com.king.view.supertextview.SuperTextView

/**
 * @author [Iga Noviyanti R] on 22/01/2021 at 8:27.
 */
class OnboardingActivity : AppCompatActivity() {

    private val binding by viewBinding<ActivityOnboardBinding>()
    private var pos = 0
    private val talkJapan = mutableListOf(R.string.talk_japan_1, R.string.talk_japan_2, R.string.talk_japan_3)
    private val talkIndo = mutableListOf(R.string.talk_indo_1, R.string.talk_indo_2, R.string.talk_indo_3)
    private val imgBg = mutableListOf(R.drawable.img_onboard, R.drawable.img_onboard2, R.drawable.img_onboard3)

    private val audio = mutableListOf(R.raw.on_board_1, R.raw.on_board_2, R.raw.on_board_3)
    lateinit var sound : MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboard)

        apply {
            window?.apply {
                clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            }
        }

        sound = MediaPlayer.create(this, audio[pos])
        sound.start()

        binding.clFooter.setOnClickListener {
            pos++
            if(pos!=talkJapan.size){
                binding.clRoot.setBackgroundResource(imgBg[pos])
                if(sound.isPlaying)
                {
                    sound.stop()
                    sound.release()
                }
                sound = MediaPlayer.create(this, audio[pos])
                sound.start()
                typeWriter()
            }
            else startActivity(Intent(this, MenuActivity::class.java))

        }

    }

    private fun typeWriter(){
        binding.apply {
            tvJapanVersion.duration = 3
            tvIndoVersion.duration = 3
            tvJapanVersion.dynamicStyle = SuperTextView.DynamicStyle.TYPEWRITING
            tvJapanVersion.dynamicText = getString(talkJapan[pos])
            tvIndoVersion.dynamicStyle = SuperTextView.DynamicStyle.TYPEWRITING
            tvIndoVersion.dynamicText = getString(talkIndo[pos])
            tvIndoVersion.start()
            tvJapanVersion.start()
        }
    }
}