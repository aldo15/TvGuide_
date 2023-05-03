package com.example.tvguide_.ui.modules.showdatils.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tvguide_.databinding.ItemTalentsBinding
import com.example.tvguide_.ui.modules.data.model.cast.CastResponse

class CastAdapter : RecyclerView.Adapter<CastAdapter.ViewHolder>() {
    private var itemList = ArrayList<CastResponse>()

    fun setItemList(itemList: List<CastResponse>) {
        this.itemList = itemList as ArrayList<CastResponse>
        notifyDataSetChanged()
    }


    class ViewHolder(val binding: ItemTalentsBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTalentsBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(itemList[position].person.image?.medium)
            .into(holder.binding.ivImage)
        holder.binding.tvName.text = itemList[position].person.name

    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
