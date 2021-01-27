package com.call.learnjapanese.ui

import android.os.Bundle
import android.view.WindowManager
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

    private val binding by viewBinding<ActivityKegiatanBinding>()
    private val adapter = ListKegiatanAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        apply {
            window?.apply {
                clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            }
        }

        val listJapanKegiatan = resources.getStringArray(R.array.kegiatanjapan)
        val listIndoKegiatan = resources.getStringArray(R.array.kegiatanindo)

        binding.apply {
            rvListKegiatan.adapter = adapter
            tvJapanVersion.duration = 6
            tvIndoVersion.duration = 6
            tvJapanVersion.dynamicStyle = SuperTextView.DynamicStyle.TYPEWRITING
            tvIndoVersion.dynamicStyle = SuperTextView.DynamicStyle.TYPEWRITING
        }

        adapter.setOnItemClickListener {
            binding.apply {
                tvJapanVersion.setText(listJapanKegiatan[it])
                tvIndoVersion.setText(listIndoKegiatan[it])
            }
        }

    }
}