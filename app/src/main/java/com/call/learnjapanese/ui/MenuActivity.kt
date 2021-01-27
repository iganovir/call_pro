package com.call.learnjapanese.ui

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.call.learnjapanese.R
import com.call.learnjapanese.databinding.ActivityMenuBinding
import com.call.learnjapanese.util.viewBinding

/**
 * @author [Iga Noviyanti R] on 22/01/2021 at 8:35.
 */
class MenuActivity : AppCompatActivity() {
    private val binding by viewBinding<ActivityMenuBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        apply {
            window?.apply {
                clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            }
        }

        binding.btnMenuMakanan.setOnClickListener {
            startActivity(Intent(this, MakananActivity::class.java))
        }

        binding.btnMenuKegiatan.setOnClickListener {
            startActivity(Intent(this, KegiatanActivity::class.java))
        }

        binding.btnMenuKendaraan.setOnClickListener {
            startActivity(Intent(this, KendaraanActivity::class.java))
        }

        binding.btnMenuTest.setOnClickListener {
            startActivity(Intent(this, TestActivity::class.java))
        }
    }
}