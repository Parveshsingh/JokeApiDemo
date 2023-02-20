package com.sansoftwares.jokeapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sansoftwares.jokeapi.databinding.RecyclerviewItemBinding

class RecyclerViewAdapter(private val data : List<String>) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>()
{

    inner  class MyViewHolder(val binding : RecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : String)
        {
            binding.textview.text = item
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val listItembinding = RecyclerviewItemBinding.inflate(inflate,parent,false)
        return MyViewHolder(listItembinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
