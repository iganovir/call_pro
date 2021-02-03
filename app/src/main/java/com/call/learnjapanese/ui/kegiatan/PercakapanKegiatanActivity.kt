package com.call.learnjapanese.ui.kegiatan

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.call.learnjapanese.R
import com.call.learnjapanese.databinding.ActivityPercakapanKegiatanBinding
import com.call.learnjapanese.ui.MenuActivity
import com.call.learnjapanese.util.viewBinding

/**
 * @author [Iga Noviyanti R] on 28/01/2021 at 7:27.
 */
class PercakapanKegiatanActivity : AppCompatActivity() {
    var pos_bg = 0
    var pos_percakapan = -1

    private val bg = mutableListOf(R.drawable.img_pola_kalimat_kegiatan, R.drawable.img_percakapan_kegiatan)

    private val audiojp = mutableListOf(R.raw.kegiatan_percakapan_1, R.raw.kegiatan_percakapan_2, R.raw.kegiatan_percakapan_3, R.raw.kegiatan_percakapan_4)

    private val percakapanjp = mutableListOf(R.string.bubble_1_japan_kegiatan, R.string.bubble_2_japan_kegiatan,R.string.bubble_3_japan_kegiatan, R.string.bubble_4_japan_kegiatan)
    private val percakapanid = mutableListOf(R.string.bubble_1_kegiatan, R.string.bubble_2_kegiatan,R.string.bubble_3_kegiatan, R.string.bubble_4_kegiatan)

    private val audioKegiatanPercakapan = mutableListOf(R.raw.kegiatan_percakapan_1, R.raw.kegiatan_percakapan_3, R.raw.kegiatan_percakapan_2, R.raw.kegiatan_percakapan_4)

    lateinit var audio : MediaPlayer

    private val binding by viewBinding<ActivityPercakapanKegiatanBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        apply {
            window?.apply {
                clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            }
        }
        audio = MediaPlayer.create(this, audioKegiatanPercakapan[0])

        binding.apply {
            tvJapanVersion.setText(R.string.pola_kalimat_kegiatan)
            dash.visibility = View.GONE
            tvIndoVersion.visibility = View.GONE
        }


        binding.apply {
            btnSelanjutnya.setOnClickListener {
                pos_bg++
                if(pos_bg<1)
                {
                    clRoot.setBackgroundResource(bg[pos_bg])
                    dash.visibility = View.VISIBLE
                    tvIndoVersion.visibility = View.VISIBLE
                    tvJapanVersion.setText(R.string.pola_kalimat_kegiatan)
                }else{
                    pos_bg = 1
                    pos_percakapan++
                    if(pos_percakapan<4) {
                        clRoot.setBackgroundResource(bg[1])
                        dash.visibility = View.VISIBLE
                        tvIndoVersion.visibility = View.VISIBLE
                        tvJapanVersion.setText(percakapanjp[pos_percakapan])
                        tvIndoVersion.setText(percakapanid[pos_percakapan])

                        if(audio.isPlaying){
                            audio.stop()
                            audio.reset()
                        }
                        audio = MediaPlayer.create(this@PercakapanKegiatanActivity, audioKegiatanPercakapan[pos_percakapan])
                        playAudioKegiatann()
                    }else{
                        pos_percakapan = 4
                    }

                }
            }

            btnKembali.setOnClickListener {
                if(pos_percakapan>=0 && pos_bg==1)
                {
                    pos_percakapan--
                    if(pos_percakapan in 0..3) {
                        pos_bg=1
                        dash.visibility = View.VISIBLE
                        clRoot.setBackgroundResource(bg[pos_bg])
                        tvIndoVersion.visibility = View.VISIBLE
                        tvJapanVersion.setText(percakapanjp[pos_percakapan])
                        tvIndoVersion.setText(percakapanid[pos_percakapan])

                        if(audio.isPlaying){
                            audio.stop()
                            audio.reset()
                        }
                        audio = MediaPlayer.create(this@PercakapanKegiatanActivity, audioKegiatanPercakapan[pos_percakapan])
                        playAudioKegiatann()
                    }
                }

                if(pos_percakapan<0)
                {
                    pos_bg--
                    if(pos_bg==0)
                    {
                        clRoot.setBackgroundResource(bg[pos_bg])
                        tvJapanVersion.setText(R.string.pola_kalimat_kegiatan)
                        dash.visibility = View.GONE
                        tvIndoVersion.visibility = View.GONE
                    }
                    else if(pos_bg<0)
                    {
                        finish()
                    }
                }
            }

            btnMenu.setOnClickListener {
                startActivity(Intent(this@PercakapanKegiatanActivity, MenuActivity::class.java))
                finishAffinity()
            }
        }

    }

    fun playAudioKegiatann(){
        audio.start()
        audio.setOnCompletionListener {
            audio.reset()
        }
    }
}