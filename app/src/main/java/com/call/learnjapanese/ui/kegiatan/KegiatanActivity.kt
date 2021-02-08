package com.call.learnjapanese.ui.kegiatan

import android.content.DialogInterface
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.call.learnjapanese.ListKegiatanAdapter
import com.call.learnjapanese.R
import com.call.learnjapanese.databinding.ActivityKegiatanBinding
import com.call.learnjapanese.util.viewBinding
import com.king.view.supertextview.SuperTextView


/**
 * @author [Iga Noviyanti R] on 24/01/2021 at 7:59.
 */
class KegiatanActivity : AppCompatActivity() {

    var pos_adapter = 0
    var pos_audio = 0
    private val binding by viewBinding<ActivityKegiatanBinding>()

    private val listImgKegiatan1 = mutableListOf(R.drawable.img_facewash, R.drawable.img_toothbrush, R.drawable.img_bath, R.drawable.img_breakfast, R.drawable.img_tea,
            R.drawable.img_newspaper)

    private val listImgKegiatan2 = mutableListOf(R.drawable.img_listeningmusic, R.drawable.img_letter, R.drawable.img_watchtv, R.drawable.img_homework, R.drawable.img_praying, R.drawable.img_mencuci)


    private val listImgKegiatan3 = mutableListOf(R.drawable.img_cleaning, R.drawable.img_sleeping, R.drawable.img_wakeup,
            R.drawable.img_travel, R.drawable.img_student, R.drawable.img_walkingschool)

    private val adapter1 = ListKegiatanAdapter(listImgKegiatan1)
    private val adapter2 = ListKegiatanAdapter(listImgKegiatan2)
    private val adapter3 = ListKegiatanAdapter(listImgKegiatan3)

    private val audiojp1 = mutableListOf(R.raw.kegiatan_mencuci_muka_jp, R.raw.kegiatan_sikat_gigi_jp, R.raw.kegiatan_mandi_jp, R.raw.kegiatan_sarapan_jp, R.raw.kegiatan_minum_teh_jp, R.raw.kegiatan_membaca_koran_jp)
    private val audiojp2 = mutableListOf(R.raw.kegiatan_mendengar_lagu_jp, R.raw.kegiatan_menulis_surat_jp, R.raw.kegiatan_nonton_tv_jp, R.raw.kegiatan_mengerjakan_pr_jp, R.raw.kegiatan_berdoa_jp, R.raw.kegiatan_mencuci_jp)
    private val audiojp3 = mutableListOf(R.raw.kegiatan_bersih_bersih_jp, R.raw.kegiatan_tidur_jp, R.raw.kegiatan_bangun_jp, R.raw.kegiatan_pergi_kejepan_jp    , R.raw.kegiatan_pulang_ke_rumah_jp, R.raw.kegiatan_datang_ke_sekolah_jp)
    //audio 3 (kegiatan_informasi_makan) harusnya diganti sama pergi ke jepang
    private val audioid1 = mutableListOf(R.raw.kegiatan_mencuci_muka_id, R.raw.kegiatan_sikat_gigi_id, R.raw.kegiatan_mandi_id, R.raw.kegiatan_sarapan_id, R.raw.kegiatan_minum_teh_id, R.raw.kegiatan_membaca_koran_id)
    private val audioid2 = mutableListOf(R.raw.kegiatan_mendengar_lagu_id, R.raw.kegiatan_menulis_surat_id, R.raw.kegiatan_nonton_tv_id, R.raw.kegiatan_mengerjakan_pr_id, R.raw.kegiatan_berdoa_id, R.raw.kegiatan_mencuci_id)
    private val audioid3 = mutableListOf(
            R.raw.kegiatan_bersih_bersih_id, R.raw.kegiatan_tidur_id,
            R.raw.kegiatan_bangun_id, R.raw.kegiatan_pergi_ke_jepang_id,
            R.raw.kegiatan_pualng_ke_rumah_id, R.raw.kegiatan_datang_ke_sekolah_id)


    lateinit var soundjp : MediaPlayer
    lateinit var soundid : MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        apply {
            window?.apply {
                clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            }
        }

        val listJapanKegiatan1 = resources.getStringArray(R.array.kegiatanjapan1)
        val listJapanKegiatan2 = resources.getStringArray(R.array.kegiatanjapan2)
        val listJapanKegiatan3 = resources.getStringArray(R.array.kegiatanjapan3)
        val listIndoKegiatan = resources.getStringArray(R.array.kegiatanindo)
        val listIndoKegiatan2 = resources.getStringArray(R.array.kegiatanindo2)
        val listIndoKegiatan3 = resources.getStringArray(R.array.kegiatanindo3)


        soundid = MediaPlayer.create(this@KegiatanActivity, audiojp1[0])
        soundjp = MediaPlayer.create(this@KegiatanActivity, audioid1[0])

        binding.apply {
            rvListKegiatan.adapter = adapter1
            tvJapanVersion.duration = 6
            tvIndoVersion.duration = 6
            tvJapanVersion.dynamicStyle = SuperTextView.DynamicStyle.TYPEWRITING
            tvIndoVersion.dynamicStyle = SuperTextView.DynamicStyle.TYPEWRITING

            btnSelanjutnya.setOnClickListener {
                pos_adapter++
                if(pos_adapter==1){
                    rvListKegiatan.adapter = adapter2
                }
                else if(pos_adapter==2)
                {
                    rvListKegiatan.adapter = adapter3
                }else{
                    pos_adapter=2
                    startActivity(Intent(this@KegiatanActivity, PercakapanKegiatanActivity::class.java))
                }
            }

            btnKembali.setOnClickListener {
                pos_adapter--
                if(pos_adapter==0){
                    rvListKegiatan.adapter = adapter1
                }else if(pos_adapter==1)
                {
                    rvListKegiatan.adapter = adapter2
                }
                else{
                    pos_adapter = 0
                }
            }

            btnMenu.setOnClickListener {
                finish()
            }

            ivHelp.setOnClickListener{
                dialog()
            }
        }

        adapter1.setOnItemClickListener {
            binding.apply {
                tvJapanVersion.setText(listJapanKegiatan1[it])
                tvIndoVersion.setText(listIndoKegiatan[it])
                if(soundid.isPlaying || soundjp.isPlaying){
                    soundid.stop()
                    soundjp.stop()
                    soundid.reset()
                    soundjp.reset()
                }
                soundid = MediaPlayer.create(this@KegiatanActivity, audioid1[it])
                soundjp = MediaPlayer.create(this@KegiatanActivity, audiojp1[it])
                binding.ivSoundIndo.visibility = View.GONE
                binding.ivSoundJapan.visibility = View.GONE
                playAudioKegiatan()
            }
        }

        adapter2.setOnItemClickListener {
            binding.apply {
                tvJapanVersion.setText(listJapanKegiatan2[it])
                tvIndoVersion.setText(listIndoKegiatan2[it])
                if(soundid.isPlaying || soundjp.isPlaying){
                    soundid.stop()
                    soundjp.stop()
                    soundid.reset()
                    soundjp.reset()
                }
                soundid = MediaPlayer.create(this@KegiatanActivity, audioid2[it])
                soundjp = MediaPlayer.create(this@KegiatanActivity, audiojp2[it])
                binding.ivSoundIndo.visibility = View.GONE
                binding.ivSoundJapan.visibility = View.GONE
                playAudioKegiatan()
            }
        }

        adapter3.setOnItemClickListener {
            binding.apply {
                tvJapanVersion.setText(listJapanKegiatan3[it])
                tvIndoVersion.setText(listIndoKegiatan3[it])
                if(soundid.isPlaying || soundjp.isPlaying){
                    soundid.stop()
                    soundjp.stop()
                    soundid.reset()
                    soundjp.reset()
                }
                soundid = MediaPlayer.create(this@KegiatanActivity, audioid3[it])
                soundjp = MediaPlayer.create(this@KegiatanActivity, audiojp3[it])
                binding.ivSoundIndo.visibility = View.GONE
                binding.ivSoundJapan.visibility = View.GONE
                playAudioKegiatan()
            }
        }

    }

    fun playAudioKegiatan(){
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

    private fun dialog(){
        val dialog = AlertDialog.Builder(this@KegiatanActivity)
        dialog.apply {
            val view = layoutInflater.inflate(R.layout.layout_info_makanan, null)
            setView(view)
            setCancelable(true)
            setIcon(R.mipmap.ic_launcher)

            val close = view.findViewById<ImageView>(R.id.ivClose)

            close.setOnClickListener {

            }
        }.show()

    }
}