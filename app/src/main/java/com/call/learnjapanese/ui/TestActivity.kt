package com.call.learnjapanese.ui

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.call.learnjapanese.ListJawabanAdapter
import com.call.learnjapanese.R
import com.call.learnjapanese.databinding.ActivityQuizBinding
import com.call.learnjapanese.util.viewBinding

/**
 * @author [Iga Noviyanti R] on 24/01/2021 at 10:03.
 */
class TestActivity : AppCompatActivity() {

    private val binding by viewBinding<ActivityQuizBinding>()
    private val adapter = ListJawabanAdapter()
    private var pos = 0

    private val listImg = mutableListOf(R.drawable.img_breakfast, R.drawable.img_toothbrush, R.drawable.img_tea, R.drawable.img_homework, R.drawable.img_watchtv,
                            R.drawable.img_praying, R.drawable.img_mencuci)

    private val jawabanBenar = mutableListOf(3,2,0,3,0,1,1)

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
            rvListJawaban.adapter = adapter
            tvNumber.text = "${pos+1}"
            ivTest.setImageResource(listImg[pos])
        }

        adapter.setOnItemClickListener{ position, item ->
            binding.apply {
                if(position == jawabanBenar[pos]) {
                    pos++
                    if (pos < 7) {
                        adapter.addPosSoal(pos)
                        tvNumber.text = "${pos + 1}"
                        ivTest.setImageResource(listImg[pos])
                    }else{
                        val i = Intent(this@TestActivity, Test2Activity::class.java)
                        i.putExtra("result", true)
                        startActivity(i)
                        finish()
                    }
                }else{
                    val i = Intent(this@TestActivity, ResultTestActivity::class.java)
                    i.putExtra("result", false)
                    startActivity(i)
                    finish()
                }
            }
        }

    }
}