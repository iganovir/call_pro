package com.call.learnjapanese.ui.makanan

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.call.learnjapanese.R
import com.call.learnjapanese.databinding.ActivityPolaKalimatMakananBinding
import com.call.learnjapanese.ui.MenuActivity
import com.call.learnjapanese.util.viewBinding

/**
 * @author [Iga Noviyanti R] on 26/01/2021 at 22:59.
 */
class PolaKalimatMakananActivity : AppCompatActivity() {

    private val binding by viewBinding<ActivityPolaKalimatMakananBinding>()
    var pos = 0
    var posJamMakan = 0
    var posPercakapan = 0

    var bg = mutableListOf(R.drawable.img_makan_pagi, R.drawable.img_makan_siang, R.drawable.img_makan_malam, R.drawable.img_pola_kalimat_makanan, R.drawable.img_percakapan_makanan,R.drawable.img_percakapan_makanan,R.drawable.img_percakapan_makanan)
    var audiojapanVer = mutableListOf(R.raw.makan_pagi_jp, R.raw.makan_siang_jp, R.raw.makan_malam_jp)
    private val audioMakananPercakapan = mutableListOf(R.raw.makanan_percakapan_1, R.raw.makanan_percakapan_2, R.raw.makanan_percakapan_3)
    var japanVer = mutableListOf(R.string.makan_pagi_japan, R.string.makan_siang_jaapan, R.string.makan_malam_japan, R.string.makan_malam_japan, R.string.bubble_1_japan_makan,R.string.bubble_2_japan_makan,R.string.bubble_3_japan_makan)
    var indoVer = mutableListOf(R.string.makan_pagi, R.string.makan_siang, R.string.makan_malam, R.string.pola_kalimat_makanan,R.string.bubble_1_makan,R.string.bubble_2_makan,R.string.bubble_3_makan )

    val titleJapan = mutableListOf(R.string.pola_kalimat,R.string.pola_kalimat,R.string.pola_kalimat,R.string.pola_kalimat,R.string.pola_kalimat,R.string.pola_kalimat,R.string.pola_kalimat)
    lateinit var audioJp : MediaPlayer
    lateinit var audioId : MediaPlayer
    lateinit var audioConvo : MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        apply {
            window?.apply {
                clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            }
        }

        audioJp = MediaPlayer.create(this, audiojapanVer[posJamMakan])
        audioConvo = MediaPlayer.create(this, audioMakananPercakapan[posPercakapan])
        playAudio()

        binding.apply {
            btnSelanjutnya.setOnClickListener {
                pos++
                if(pos<bg.size) {
                    clRoot.setBackgroundResource(bg[pos])
                    when(pos)
                    {
                        in 0..2 ->{
                            posJamMakan++
                            audioJp = MediaPlayer.create(this@PolaKalimatMakananActivity, audiojapanVer[posJamMakan])
                            playAudio()
                            tvJapanVersion.setText(japanVer[pos])
                            tvIndoVersion.setText(indoVer[pos])
                        }
                        3 -> {
                            tvJapanVersion.setText(indoVer[pos])
                            tvIndoVersion.visibility = View.GONE
                            tvIndoTitle.visibility = View.GONE
                            dash.visibility = View.GONE
                            tvJapantTitle.text = "Waktu dalam makan"
                        }
                        else-> {
                            audioConvo.start()
                            if(posPercakapan<3) {
                                if (audioConvo.isPlaying) {
                                    audioConvo.stop()
                                    audioConvo.release()
                                }
                                audioConvo = MediaPlayer.create(this@PolaKalimatMakananActivity, audioMakananPercakapan[posPercakapan])
                                audioConvo.start()
                                clTitle.visibility = View.GONE
                                tvIndoVersion.visibility = View.VISIBLE
                                tvIndoTitle.visibility = View.VISIBLE
                                dash.visibility = View.VISIBLE
                                tvJapanVersion.setText(japanVer[pos])
                                tvIndoVersion.setText(indoVer[pos])
                                posPercakapan++
                            }else{
                                posPercakapan=3
                            }
                        }
                    }
                }
            }

            btnKembali.setOnClickListener {
                if(pos!=0) {
                    pos = if(pos==bg.size){
                        bg.size.minus(1)
                    }else {
                        0
                    }
                    clRoot.setBackgroundResource(bg[pos])
                    when(pos)
                    {
                        in 0..2 ->{
                            posJamMakan--
                            audioJp = MediaPlayer.create(this@PolaKalimatMakananActivity, audiojapanVer[posJamMakan])
                            playAudio()
                            tvJapanVersion.setText(japanVer[pos])
                            tvIndoVersion.setText(indoVer[pos])
                            clTitle.visibility = View.VISIBLE
                            tvIndoVersion.visibility = View.VISIBLE
                            tvIndoTitle.visibility = View.VISIBLE
                            dash.visibility = View.VISIBLE
                        }
                        3 -> {
                            tvJapanVersion.setText(indoVer[pos])
                            tvIndoVersion.visibility = View.GONE
                            tvIndoTitle.visibility = View.GONE
                            dash.visibility = View.GONE
                            tvJapantTitle.setText(titleJapan[pos])
                        }
                        else-> {
                            pos = 4
                        }
                    }
                }else{
                    finish()
                }
            }

            btnMenu.setOnClickListener {
                startActivity(Intent(this@PolaKalimatMakananActivity, MenuActivity::class.java))
                finish()
            }
        }

    }

    fun playAudio(){
        binding.ivSoundJapan.visibility = View.VISIBLE
        audioJp.start()
        audioJp.setOnCompletionListener {
            audioJp.reset()
            binding.ivSoundJapan.visibility = View.GONE
        }

    }
}