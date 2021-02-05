package com.call.learnjapanese

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.call.learnjapanese.databinding.ItemKelompokBinding


/**
 * @author [Iga Noviyanti R] on 04/02/2021 at 19:55.
 */
class ListTeamAdapter(private val context: Context) : RecyclerView.Adapter<ListTeamAdapter.MyViewHolder>() {

    private val listImg = mutableListOf(R.drawable.img_fitri, R.drawable.img_fivit, R.drawable.img_auliya, R.drawable.img_masthuur)
    private val listName = mutableListOf(R.string.fitri, R.string.fivit, R.string.auliya, R.string.masthur)
    private val listIg= mutableListOf(R.string.igfitri, R.string.igfivit, R.string.igauliya, R.string.igmasthur)
    private val listemail= mutableListOf(R.string.efitri, R.string.efivit, R.string.eauliya, R.string.emasthur)

    inner class MyViewHolder(private val binding: ItemKelompokBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(pos: Int){

            binding.apply {
                ivImage.setImageResource(listImg[pos])
                tvName.setText(listName[pos])
                tvInsta.setText(listIg[pos])
                tvEmail.setText(listemail[pos])

                linGmail.setOnClickListener {
                    goGmail(pos, context.getString(listemail[pos]))
                }

                linIg.setOnClickListener {
                    context.startActivity(newInstagramProfileIntent(context.packageManager, context.getString(listIg[pos])))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemKelompokBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int  = listImg.size

    fun newInstagramProfileIntent(pm: PackageManager, user: String): Intent? {
        var url = "http://instagram.com/$user"
        val intent = Intent(Intent.ACTION_VIEW)
        try {
            if (pm.getPackageInfo("com.instagram.android", 0) != null) {
                intent.data = Uri.parse("http://instagram.com/_u/$user")
                intent.setPackage("com.instagram.android")
                return intent
            }else{
                intent.data = Uri.parse("http://instagram.com/$user")
            }
        } catch (e : Exception) {
            intent.data = Uri.parse("http://instagram.com/$user")
        }
        intent.data = Uri.parse("http://instagram.com/$user")
        return intent
    }

    private fun goGmail(pos: Int, email: String){
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:") // only email apps should handle this

        intent.putExtra(Intent.EXTRA_EMAIL, email)
        intent.putExtra(Intent.EXTRA_SUBJECT, "Hallo ${context.getString(listName[pos])}")
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }
    }
}