package com.call.learnjapanese

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.call.learnjapanese.databinding.ItemListBinding

/**
 * @author [Iga Noviyanti R] on 24/01/2021 at 8:02.
 */
class ListKegiatanAdapter(private val images: MutableList<Int> = mutableListOf<Int>()) : RecyclerView.Adapter<ListKegiatanAdapter.MyViewHolder>() {

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
        holder.onBind(position, images[position])
    }

    override fun getItemCount(): Int  = images.size

    private var onItemClickListener: ((Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }
}