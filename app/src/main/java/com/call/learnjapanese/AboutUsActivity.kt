package com.call.learnjapanese

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.call.learnjapanese.databinding.ActivityAboutUsBinding
import com.call.learnjapanese.util.viewBinding

/**
 * @author [Iga Noviyanti R] on 04/02/2021 at 20:00.
 */
class AboutUsActivity : AppCompatActivity() {

    private val binding by viewBinding<ActivityAboutUsBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        apply {
            window?.apply {
                clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            }
        }

        binding.rvListteam.adapter = ListTeamAdapter(context = this@AboutUsActivity)
        binding.rvListteam.setHasFixedSize(true)

    }
}