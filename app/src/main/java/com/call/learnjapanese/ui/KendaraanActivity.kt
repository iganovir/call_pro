package com.call.learnjapanese.ui

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.call.learnjapanese.ListKendaraanAdapter
import com.call.learnjapanese.ListMakananAdapter
import com.call.learnjapanese.R
import com.call.learnjapanese.databinding.ActivityKendaraanBinding
import com.call.learnjapanese.databinding.ActivityMakananBinding
import com.call.learnjapanese.util.viewBinding
import com.king.view.supertextview.SuperTextView

/**
 * @author [Iga Noviyanti R] on 24/01/2021 at 8:34.
 */
class KendaraanActivity : AppCompatActivity() {

    private val binding by viewBinding<ActivityKendaraanBinding>()
    private val adapter = ListKendaraanAdapter()

    private val listJapanKendaraan = mutableListOf(R.string.bis_japan, R.string.motor_japan, R.string.sepeda_japan, R.string.mobil_japan)
    private val listIndoKendaraan = mutableListOf(R.string.bis, R.string.motor, R.string.sepeda, R.string.mobil)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        apply {
            window?.apply {
                clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            }
        }

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
            }
        }

    }
}