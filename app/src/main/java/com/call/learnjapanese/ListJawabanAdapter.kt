package com.call.learnjapanese

import android.provider.Settings.Secure.getString
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.call.learnjapanese.databinding.ItemListBinding
import com.call.learnjapanese.databinding.ItemListJawabanBinding

/**
 * @author [Iga Noviyanti R] on 24/01/2021 at 10:04.
 */
class ListJawabanAdapter  : RecyclerView.Adapter<ListJawabanAdapter.MyViewHolder>() {

    private var posSoal = 0
    private val listJawaban = mutableListOf(
            1 to arrayOf(R.string.test1_option1,R.string.test1_option2,R.string.test1_option3,R.string.test1_option4),
            2 to arrayOf(R.string.test2_option1,R.string.test2_option2,R.string.test2_option3,R.string.test2_option4),
            3 to arrayOf(R.string.test3_option1,R.string.test3_option2,R.string.test3_option3,R.string.test3_option4),
            4 to arrayOf(R.string.test4_option1,R.string.test4_option2,R.string.test4_option3,R.string.test4_option4),
            5 to arrayOf(R.string.test5_option1,R.string.test5_option2,R.string.test5_option3,R.string.test5_option4),
            6 to arrayOf(R.string.test6_option1,R.string.test6_option2,R.string.test6_option3,R.string.test6_option4),
            7 to arrayOf(R.string.test7_option1,R.string.test7_option2,R.string.test7_option3,R.string.test7_option4)
    )

    inner class MyViewHolder(private val binding : ItemListJawabanBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(pos : Int, txt : Int){

            binding.apply {
                btnJawaban.setText(txt)

                btnJawaban.setOnClickListener {
                    onItemClickListener?.invoke(pos, btnJawaban.text.toString())
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemListJawabanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(position, listJawaban[posSoal].second[position])
    }

    override fun getItemCount(): Int  = listJawaban[posSoal].second.size

    private var onItemClickListener: ((Int, String) -> Unit)? = null

    fun setOnItemClickListener(listener: (Int, String) -> Unit) {
        onItemClickListener = listener
    }

    fun addPosSoal(pos : Int){
        posSoal = pos
        notifyDataSetChanged()
    }
}