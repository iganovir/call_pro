package com.call.learnjapanese

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.call.learnjapanese.databinding.ItemListBinding
import com.call.learnjapanese.databinding.ItemListKendaraanBinding

/**
 * @author [Iga Noviyanti R] on 24/01/2021 at 8:36.
 */
class ListKendaraanAdapter: RecyclerView.Adapter<ListKendaraanAdapter.MyViewHolder>() {

    private val listImgMakanan = mutableListOf(R.raw.bus, R.raw.motor, R.raw.bike, R.raw.car)

    inner class MyViewHolder(private val binding : ItemListKendaraanBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(pos : Int, img : Int){

            binding.apply {
                imgFood.setAnimation(listImgMakanan[pos])

                cdRoot.setOnClickListener {
                    onItemClickListener?.invoke(pos)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemListKendaraanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(position, listImgMakanan[position])
    }

    override fun getItemCount(): Int  = listImgMakanan.size

    private var onItemClickListener: ((Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }
}