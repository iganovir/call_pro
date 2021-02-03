package com.call.learnjapanese.ui

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.call.learnjapanese.R
import com.call.learnjapanese.databinding.ActivityResultTestBinding
import com.call.learnjapanese.util.viewBinding

/**
 * @author [Iga Noviyanti R] on 28/01/2021 at 8:15.
 */
class ResultTestActivity : AppCompatActivity() {

    private val binding by viewBinding<ActivityResultTestBinding>()
    lateinit var sound : MediaPlayer
    var result = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        apply {
            window?.apply {
                clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            }
        }

        var i = intent
        result = i.getBooleanExtra("result", false)

        if(result){
            binding.apply {
                tvResult.text = resources.getString(R.string.point_100)
                sound = MediaPlayer.create(this@ResultTestActivity, R.raw.win_quiz)
                sound.start()
                btnRestart.visibility = View.GONE
            }
        }else{
            binding.apply {
                tvResult.text = resources.getString(R.string.point_wrong)
                sound = MediaPlayer.create(this@ResultTestActivity, R.raw.wrong_answer)
                sound.start()
            }
        }

        binding.apply {
            btnRestart.setOnClickListener {
                finish()
                startActivity(Intent(this@ResultTestActivity, TestActivity::class.java))
            }

            btnKembali.setOnClickListener {
                finish()
            }
        }

    }
}