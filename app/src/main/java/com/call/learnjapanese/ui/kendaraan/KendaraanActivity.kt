package com.call.learnjapanese.ui.kendaraan

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.call.learnjapanese.ListKendaraanAdapter
import com.call.learnjapanese.R
import com.call.learnjapanese.databinding.ActivityKendaraanBinding
import com.call.learnjapanese.ui.kegiatan.PercakapanKegiatanActivity
import com.call.learnjapanese.util.viewBinding
import com.king.view.supertextview.SuperTextView

/**
 * @author [Iga Noviyanti R] on 24/01/2021 at 8:34.
 */
class KendaraanActivity : AppCompatActivity() {
    var pos= 0

    private val binding by viewBinding<ActivityKendaraanBinding>()
    private val adapter = ListKendaraanAdapter()

    private val listJapanKendaraan = mutableListOf(R.string.bis_japan, R.string.motor_japan, R.string.sepeda_japan, R.string.mobil_japan)
    private val listIndoKendaraan = mutableListOf(R.string.bis, R.string.motor, R.string.sepeda, R.string.mobil)

    private val audioKendaraanJp = mutableListOf(R.raw.kendaraan_judul,R.raw.kendaraan_bus_jp,  R.raw.kendaraan_motor_jp, R.raw.kendaraan_sepeda_jp,R.raw.kendaraan_mobil_jp)
    private val audioKendaraanId = mutableListOf(R.raw.kendaraan_bus_id, R.raw.kendaraan_motor_id, R.raw.kendaraan_sepeda_id, R.raw.kendaraan_mobil_id)

    private var soundjp = MediaPlayer()
    private var soundid = MediaPlayer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        apply {
            window?.apply {
                clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            }
        }

        soundjp = MediaPlayer.create(this, audioKendaraanJp[pos])
        soundid = MediaPlayer.create(this, audioKendaraanJp[pos])
        soundjp.start()

        binding.apply {
            rvListKegiatan.adapter = adapter
            tvJapanVersion.duration = 6
            tvIndoVersion.duration = 6
            tvJapanVersion.dynamicStyle = SuperTextView.DynamicStyle.TYPEWRITING
            tvIndoVersion.dynamicStyle = SuperTextView.DynamicStyle.TYPEWRITING
        }

        adapter.setOnItemClickListener {
            binding.apply {
                tvJapanVersion.setText(listJapanKendaraan[it])
                tvIndoVersion.setText(listIndoKendaraan[it])

                if(soundjp.isPlaying || soundid.isPlaying){
                    soundjp.stop()
                    soundjp.reset()
                    soundid.stop()
                    soundid.reset()
                }

                soundjp = MediaPlayer.create(this@KendaraanActivity, audioKendaraanJp[it+1])
                soundid = MediaPlayer.create(this@KendaraanActivity, audioKendaraanId[it])
                playAudioMakanan()
            }
        }

        binding.apply {
            btnSelanjutnya.setOnClickListener {
                startActivity(Intent(this@KendaraanActivity, PercakapanKendaraanActivity::class.java))
            }

            btnMenu.setOnClickListener { finish() }
            btnKembali.setOnClickListener { finish() }
        }

    }

    fun playAudioMakanan(){
        binding.ivSoundIndo.visibility = View.GONE
        binding.ivSoundJapan.visibility = View.VISIBLE
        soundjp.start()
        soundjp.setOnCompletionListener {
            soundjp.reset()
            binding.ivSoundJapan.visibility = View.GONE
            soundid.start()
            binding.ivSoundIndo.visibility = View.VISIBLE
        }

        soundid.setOnCompletionListener {
            soundid.reset()
            binding.ivSoundIndo.visibility = View.GONE
        }

    }
}