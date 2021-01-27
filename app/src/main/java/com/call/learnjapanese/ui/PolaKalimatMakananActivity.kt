package com.call.learnjapanese.ui

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.call.learnjapanese.R
import com.call.learnjapanese.databinding.ActivityPolaKalimatMakananBinding
import com.call.learnjapanese.util.viewBinding

/**
 * @author [Iga Noviyanti R] on 26/01/2021 at 22:59.
 */
class PolaKalimatMakananActivity : AppCompatActivity() {

    private val binding by viewBinding<ActivityPolaKalimatMakananBinding>()
    var pos = 0

    var bg = mutableListOf(R.drawable.img_waktu_makan_pagi, R.drawable.img_waktu_makan_siang, R.drawable.img_waktu_makan_malam, R.drawable.img_pola_kalimat_makanan, R.drawable.img_percakapan_makanan,R.drawable.img_percakapan_makanan,R.drawable.img_percakapan_makanan)
    var audiojapanVer = mutableListOf(R.raw.makan_pagi_jp, R.raw.makan_siang_jp, R.raw.makan_malam_jp)
    var audioindoVer = mutableListOf(R.raw.makan_pagi_id, R.raw.makan_siang_id, R.raw.makan_malam_id)
    var japanVer = mutableListOf(R.string.makan_pagi_japan, R.string.makan_siang_jaapan, R.string.makan_malam_japan, R.string.makan_malam_japan, R.string.bubble_1_japan_makan,R.string.bubble_2_japan_makan,R.string.bubble_3_japan_makan)
    var indoVer = mutableListOf(R.string.makan_pagi, R.string.makan_siang, R.string.makan_malam, R.string.pola_kalimat_makanan,R.string.bubble_1_makan,R.string.bubble_2_makan,R.string.bubble_3_makan )

    val titleJapan = mutableListOf(R.string.pola_kalimat,R.string.pola_kalimat,R.string.pola_kalimat,R.string.pola_kalimat,R.string.pola_kalimat,R.string.pola_kalimat,R.string.pola_kalimat)
    lateinit var audioJp : MediaPlayer
    lateinit var audioId : MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        apply {
            window?.apply {
                clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            }
        }
        
        audioId = MediaPlayer.create(this, audioindoVer[pos])
        audioJp = MediaPlayer.create(this, audiojapanVer[pos])
        playAudio()

        binding.apply {
            btnSelanjutnya.setOnClickListener {
                pos++
                if(pos<bg.size) {
                    clRoot.setBackgroundResource(bg[pos])
                    when(pos)
                    {
                        in 0..2 ->{
                            tvJapanVersion.setText(japanVer[pos])
                            tvIndoVersion.setText(indoVer[pos])
                        }
                        3 -> {
                            tvJapanVersion.setText(indoVer[pos])
                            tvIndoVersion.visibility = View.GONE
                            tvIndoTitle.visibility = View.GONE
                            dash.visibility = View.GONE
                            tvJapantTitle.setText(titleJapan[pos])
                        }
                        else-> {
                            clTitle.visibility = View.GONE
                            tvIndoVersion.visibility = View.VISIBLE
                            tvIndoTitle.visibility = View.VISIBLE
                            dash.visibility = View.VISIBLE
                            tvJapanVersion.setText(japanVer[pos])
                            tvIndoVersion.setText(indoVer[pos])
                        }
                    }

//                    playAudio()
                }
            }

            btnKembali.setOnClickListener {
                if(pos!=0) {
                    pos--
                    clRoot.setBackgroundResource(bg[pos])
//                    tvJapanVersion.setText(japanVer[pos])
//                    tvIndoVersion.setText(indoVer[pos])
                }else{
                    finish()
                }
//                playAudio()
            }

            btnMenu.setOnClickListener {
                startActivity(Intent(this@PolaKalimatMakananActivity, MenuActivity::class.java))
                finish()
            }
        }

    }

    fun playAudio(){
        binding.ivSoundIndo.visibility = View.GONE
        binding.ivSoundJapan.visibility = View.VISIBLE
        audioJp.start()
        audioJp.setOnCompletionListener {
            audioJp.reset()
            binding.ivSoundJapan.visibility = View.GONE
            audioId.start()
            binding.ivSoundIndo.visibility = View.VISIBLE
        }

        audioId.setOnCompletionListener {
            audioId.reset()
            binding.ivSoundIndo.visibility = View.GONE
        }

    }
}