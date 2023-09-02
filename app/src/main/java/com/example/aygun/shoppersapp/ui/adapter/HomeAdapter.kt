package com.example.aygun.shoppersapp.ui.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation
import com.example.aygun.shoppersapp.R
import androidx.recyclerview.widget.RecyclerView
import com.example.aygun.shoppersapp.data.entity.HomeItem
import com.example.aygun.shoppersapp.databinding.HomeItemBinding
import com.example.aygun.shoppersapp.ui.fragments.HomeFragmentDirections

class HomeAdapter(private val homeItems: List<HomeItem>): RecyclerView.Adapter<HomeAdapter.HomeCardViewHolder>() {

    inner class HomeCardViewHolder(val binding: HomeItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HomeItem) {

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HomeItemBinding.inflate(inflater,parent,false)
        return HomeCardViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return homeItems.size
    }

    @SuppressLint("DiscouragedApi")
    override fun onBindViewHolder(holder: HomeCardViewHolder, position: Int) {
        val item = homeItems[position]
        val b = holder.binding

        with(b) {
            val resourceId = holder.itemView.context.resources.getIdentifier(item.image, "drawable", holder.itemView.context.packageName)
            ivHomeItem.setImageResource(resourceId)
            tvNameHomeItem.text = item.name
            tvPriceHomeItem.text = "${item.price} $"
            cvHomeItem.setOnClickListener {
                val transition = HomeFragmentDirections.homeToDetails(item)
                Navigation.findNavController(it).navigate(transition)
            }
        }
    }
}