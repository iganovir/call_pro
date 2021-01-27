package com.call.learnjapanese

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.call.learnjapanese.databinding.ItemListBinding

/**
 * @author [Iga Noviyanti R] on 24/01/2021 at 8:02.
 */
class ListKegiatanAdapter : RecyclerView.Adapter<ListKegiatanAdapter.MyViewHolder>() {

    private val listImgKegiatan = mutableListOf(R.drawable.img_facewash, R.drawable.img_toothbrush, R.drawable.img_bath, R.drawable.img_breakfast, R.drawable.img_tea,
            R.drawable.img_newspaper, R.drawable.img_listeningmusic, R.drawable.img_letter, R.drawable.img_watchtv, R.drawable.img_homework,
            R.drawable.img_praying, R.drawable.img_mencuci, R.drawable.img_cleaning, R.drawable.img_sleeping, R.drawable.img_wakeup,
            R.drawable.img_travel,R.drawable.img_student, R.drawable.img_walkingschool)

    inner class MyViewHolder(private val binding : ItemListBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(pos : Int, img : Int){

            binding.apply {
                imgFood.setImageResource(img)

                cdRoot.setOnClickListener {
                    onItemClickListener?.invoke(pos)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(position, listImgKegiatan[position])
    }

    override fun getItemCount(): Int  = listImgKegiatan.size

    private var onItemClickListener: ((Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }
}