package com.call.learnjapanese.ui.makanan

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.call.learnjapanese.ListMakananAdapter
import com.call.learnjapanese.R
import com.call.learnjapanese.databinding.ActivityMakananBinding
import com.call.learnjapanese.util.viewBinding
import com.king.view.supertextview.SuperTextView

/**
 * @author [Iga Noviyanti R] on 22/01/2021 at 9:05.
 */
class MakananActivity : AppCompatActivity() {

    private val binding by viewBinding<ActivityMakananBinding>()
    private var pos = 0


    private val listJapanMakanan = mutableListOf(R.string.nasi_japan, R.string.roti_japan, R.string.daging_japan, R.string.ikan_japan, R.string.telur_japan,
            R.string.sayuran_japan, R.string.buah_japan)

    private val listIndoMakanan = mutableListOf(R.string.nasi, R.string.roti, R.string.daging, R.string.ikan, R.string.telur,
            R.string.sayuran, R.string.buah)

    private val listImgMakanan = mutableListOf(R.drawable.img_rice, R.drawable.img_bread, R.drawable.img_meat, R.drawable.img_salmon, R.drawable.img_egg,
            R.drawable.img_veggie, R.drawable.imge_fruits)

    private val listImgMinuman = mutableListOf(R.drawable.img_glass_of_water, R.drawable.img_coffee, R.drawable.img_tea, R.drawable.imge_orange_juice, R.drawable.img_milk)

    private val listJapanMinuman = mutableListOf(R.string.air_japan, R.string.kopi_japan, R.string.teh_japan, R.string.jus_japan, R.string.susu_japan)

    private val listIndoMinuman = mutableListOf(R.string.air, R.string.kopi, R.string.teh, R.string.jus, R.string.susu)

    private val adapterMakanan = ListMakananAdapter(listImgMakanan)
    private val adapterMinuman = ListMakananAdapter(listImgMinuman)

    private val audioMakananJp = mutableListOf(R.raw.makanan_nasi_jp, R.raw.makanan_roti_jp, R.raw.makanan_daging_jp, R.raw.makanan_ikan_jp, R.raw.makanan_telur_jp, R.raw.makanan_sayur_jp, R.raw.makanan_buah_jp )
    private val audioMakananId = mutableListOf(R.raw.makanan_nasi_id, R.raw.makanan_roti_id, R.raw.makanan_daging_id, R.raw.makanan_ikan_id, R.raw.makanan_telur_id, R.raw.makanan_sayur_id, R.raw.makanan_buah_id )

    private val audionMinumanJp = mutableListOf(R.raw.minuman_air_jp, R.raw.minuman_kopi_jp, R.raw.minuman_teh_jp, R.raw.minuman_juice_jp, R.raw.minuman_susu_jp)
    private val audioMinumanId = mutableListOf(R.raw.minuman_air_id, R.raw.minuman_kopi_id, R.raw.minuman_teh_id, R.raw.minuman_juice_id, R.raw.minuman_susu_id )

    lateinit var sound_makanan_jp : MediaPlayer
    lateinit var sound_makanan_indo : MediaPlayer

    lateinit var sound_minuman_jp : MediaPlayer
    lateinit var sound_minuman_indo : MediaPlayer

    lateinit var audioMakanan : MediaPlayer
    lateinit var audioMinuman : MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        apply {
            window?.apply {
                clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            }
        }

        sound_makanan_indo = MediaPlayer.create(this@MakananActivity, audioMakananId[pos])
        sound_makanan_jp = MediaPlayer.create(this@MakananActivity, audioMakananJp[pos])
        sound_minuman_indo = MediaPlayer.create(this@MakananActivity, audioMinumanId[pos])
        sound_minuman_jp = MediaPlayer.create(this@MakananActivity, audionMinumanJp[pos])
        audioMakanan = MediaPlayer.create(this@MakananActivity, R.raw.makanan_judul)
        audioMinuman = MediaPlayer.create(this@MakananActivity, R.raw.minuman_judul)

        audioMakanan.start()

        binding.apply {
            rvListMakanan.adapter = adapterMakanan
            tvJapanVersion.duration = 6
            tvIndoVersion.duration = 6
            tvJapanVersion.dynamicStyle = SuperTextView.DynamicStyle.TYPEWRITING
            tvIndoVersion.dynamicStyle = SuperTextView.DynamicStyle.TYPEWRITING

            btnSelanjutnya.setOnClickListener {
                if(tvIndoTitle.text.toString() != "Minuman") {
                    rvListMakanan.adapter = adapterMinuman

                    binding.ivSoundIndo.visibility = View.GONE
                    binding.ivSoundJapan.visibility = View.GONE
                    tvJapantTitle.setText(R.string.minuman_japan)
                    tvIndoTitle.setText(R.string.minuman)


                    tvJapanVersion.setText(R.string.air_japan)
                    tvIndoVersion.setText(R.string.air)
                    stopAllAudio()
                    if (audioMakanan.isPlaying) {
                        audioMakanan.stop()
                    }
                    audioMinuman.start()
                }else{
                    if(sound_makanan_indo.isPlaying || sound_makanan_jp.isPlaying || audioMinuman.isPlaying){
                        sound_makanan_indo.stop()
                        sound_makanan_jp.stop()
                        sound_makanan_indo.reset()
                        sound_makanan_jp.reset()
                        audioMinuman.stop()
                        audioMinuman.start()
                    }
                    startActivity(Intent(this@MakananActivity, PolaKalimatMakananActivity::class.java))
                }


            }

            btnKembali.setOnClickListener {
                rvListMakanan.adapter = adapterMakanan
                binding.ivSoundIndo.visibility = View.GONE
                binding.ivSoundJapan.visibility = View.GONE

                tvJapantTitle.setText(R.string.makanan_japan)
                tvIndoTitle.setText(R.string.makanan)

                tvJapanVersion.setText(R.string.nasi_japan)
                tvIndoVersion.setText(R.string.nasi)

                stopAllAudio()
                if(audioMinuman.isPlaying)
                {
                    audioMinuman.stop()
                }
                audioMakanan.start()
            }

            btnMenu.setOnClickListener {
                finish()
            }
        }

        adapterMakanan.setOnItemClickListener {
            binding.apply {
                pos = it
                tvJapanVersion.setText(listJapanMakanan[it])
                tvIndoVersion.setText(listIndoMakanan[it])
                if(sound_makanan_indo.isPlaying || sound_makanan_jp.isPlaying){
                    sound_makanan_indo.stop()
                    sound_makanan_jp.stop()
                    sound_makanan_indo.reset()
                    sound_makanan_jp.reset()
                }
                sound_makanan_indo = MediaPlayer.create(this@MakananActivity, audioMakananId[it])
                sound_makanan_jp = MediaPlayer.create(this@MakananActivity, audioMakananJp[it])
                binding.ivSoundIndo.visibility = View.GONE
                binding.ivSoundJapan.visibility = View.GONE
                playAudioMakanan()

            }
        }

        adapterMinuman.setOnItemClickListener {
            binding.apply {
                pos = it
                tvJapanVersion.setText(listJapanMinuman[it])
                tvIndoVersion.setText(listIndoMinuman[it])
                if(sound_minuman_indo.isPlaying || sound_minuman_jp.isPlaying){
                    sound_minuman_indo.stop()
                    sound_minuman_jp.stop()
                    sound_minuman_indo.reset()
                    sound_minuman_jp.reset()
                }
                sound_minuman_indo = MediaPlayer.create(this@MakananActivity, audioMinumanId[it])
                sound_minuman_jp = MediaPlayer.create(this@MakananActivity, audionMinumanJp[it])
                binding.ivSoundIndo.visibility = View.GONE
                binding.ivSoundJapan.visibility = View.GONE
                playAudioMinuman()
            }
        }
    }

    fun playAudioMakanan(){
        binding.ivSoundIndo.visibility = View.GONE
        binding.ivSoundJapan.visibility = View.VISIBLE
        sound_makanan_jp.start()
        sound_makanan_jp.setOnCompletionListener {
            sound_makanan_jp.reset()
            binding.ivSoundJapan.visibility = View.GONE
            sound_makanan_indo.start()
            binding.ivSoundIndo.visibility = View.VISIBLE
        }

        sound_makanan_indo.setOnCompletionListener {
            sound_makanan_indo.reset()
            binding.ivSoundIndo.visibility = View.GONE
        }

    }

    fun playAudioMinuman(){
        binding.ivSoundIndo.visibility = View.GONE
        binding.ivSoundJapan.visibility = View.VISIBLE
        if(audioMinumanId[pos] == audionMinumanJp[pos])
        {
            sound_minuman_indo.start()
        }else {

            sound_minuman_jp.start()
            sound_minuman_jp.setOnCompletionListener {
                sound_minuman_jp.reset()
                binding.ivSoundJapan.visibility = View.GONE
                sound_minuman_indo.start()
                binding.ivSoundIndo.visibility = View.VISIBLE
            }
        }

        sound_minuman_indo.setOnCompletionListener {
            sound_minuman_indo.reset()
            binding.ivSoundIndo.visibility = View.GONE
        }
    }

    private fun stopAllAudio(){
        pos = 0
        if(sound_makanan_indo.isPlaying) {
            sound_makanan_indo.stop()
            sound_makanan_indo.reset()
            sound_makanan_indo = MediaPlayer.create(this@MakananActivity, audioMakananId[pos])

        }

        if(sound_makanan_jp.isPlaying){
            sound_makanan_jp.stop()
            sound_makanan_jp.reset()
            sound_makanan_jp = MediaPlayer.create(this@MakananActivity, audioMakananJp[pos])
        }

        if(sound_minuman_indo.isPlaying) {
            sound_minuman_indo.stop()
            sound_minuman_indo.reset()
            sound_minuman_indo = MediaPlayer.create(this@MakananActivity, audioMinumanId[pos])
        }

        if(sound_minuman_jp.isPlaying) {
            sound_minuman_jp.stop()
            sound_minuman_jp.reset()
            sound_minuman_jp = MediaPlayer.create(this@MakananActivity, audionMinumanJp[pos])
        }
    }
}