package com.call.learnjapanese.ui

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.call.learnjapanese.ListJawabanAdapter
import com.call.learnjapanese.R
import com.call.learnjapanese.databinding.ActivityQuiz2Binding
import com.call.learnjapanese.databinding.ActivityQuizBinding
import com.call.learnjapanese.util.viewBinding

/**
 * @author [Iga Noviyanti R] on 25/01/2021 at 21:32.
 */
class Test2Activity : AppCompatActivity() {

    private val binding by viewBinding<ActivityQuiz2Binding>()
    private val adapter = ListJawabanAdapter()
    private var pos = 0
    private var noSoal = 7

    private val imgTest1 = mutableListOf(R.drawable.ic_img_home, R.drawable.ic_img_home,R.drawable.ic_img_home)
    private val imgTest2 = mutableListOf(R.raw.bus, R.raw.motor,R.raw.car)
    private val imgTest3 = mutableListOf(R.drawable.ic_img_school, R.drawable.ic_img_school,R.drawable.mall)

    private val jawabanBenar = mutableListOf(0,3,3)

    private val menit = mutableListOf("45分", "15分", "25分")

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
            adapter.addPosSoal(noSoal)
            tvNumber.text = "${noSoal+1}"
            ivTest1.setImageResource(imgTest1[pos])
            ivTest3.setImageResource(imgTest3[pos])
            ivTest2.setAnimation(imgTest2[pos])
            tvMenit.text = menit[pos]
        }

        adapter.setOnItemClickListener{ position, item ->
            binding.apply {
                if(position == jawabanBenar[pos]) {
                    pos++
                    noSoal++
                    if (pos < 3) {
                        adapter.addPosSoal(noSoal)
                        tvNumber.text = "${noSoal+1}"
                        ivTest1.setImageResource(imgTest1[pos])
                        ivTest3.setImageResource(imgTest3[pos])
                        ivTest2.setAnimation(imgTest2[pos])
                        tvMenit.text = menit[pos]
                    }else{
                        val i = Intent(this@Test2Activity, ResultTestActivity::class.java)
                        i.putExtra("result", true)
                        startActivity(i)
                        finish()
                    }
                }else{
                    val i = Intent(this@Test2Activity, ResultTestActivity::class.java)
                    i.putExtra("result", false)
                    startActivity(i)
                    finish()
                }
            }
        }

    }
}