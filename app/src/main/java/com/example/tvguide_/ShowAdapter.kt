package com.example.tvguide_

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tvguide_.databinding.ItemShowBinding
import com.example.tvguide_.model.ShowItem

class ShowAdapter : RecyclerView.Adapter<ShowAdapter.ViewHolder>() {
    private var itemList = ArrayList<ShowItem>()
    private lateinit var callBack : IEventReturnMainCallBack

    fun setItemList(itemList: List<ShowItem>, callBack: IEventReturnMainCallBack) {
        this.itemList = itemList as ArrayList<ShowItem>
        this.callBack = callBack
        notifyDataSetChanged()
    }


    class ViewHolder(val binding: ItemShowBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemShowBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(itemList[position].imageUrl)
            .into(holder.binding.ivItemImage)
        holder.binding.tvItemName.text = itemList[position].name
        holder.binding.tvItemNetworkName.text = itemList[position].networkName
        holder.binding.tvItemDate.text = itemList[position].date

        holder.binding.clItem.setOnClickListener {
            callBack.CommunicationMain(itemList[position])
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
