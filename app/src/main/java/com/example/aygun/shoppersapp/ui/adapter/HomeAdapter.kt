package com.example.aygun.shoppersapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.aygun.shoppersapp.R
import androidx.recyclerview.widget.RecyclerView
import com.example.aygun.shoppersapp.data.entity.HomeItem
import com.example.aygun.shoppersapp.databinding.HomeItemBinding
import com.example.aygun.shoppersapp.ui.fragments.HomeFragmentDirections

class HomeAdapter(private val homeItems: List<HomeItem>): RecyclerView.Adapter<HomeAdapter.HomeCardViewHolder>() {

    inner class HomeCardViewHolder(val binding: HomeItemBinding): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HomeItemBinding.inflate(inflater,parent,false)
        return HomeCardViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return homeItems.size
    }

    override fun onBindViewHolder(holder: HomeCardViewHolder, position: Int) {
        val item = homeItems[position]
        val b = holder.binding

        b.ivHomeItem.setImageResource(R.drawable.nike)
        b.tvNameHomeItem.text = item.name
        b.tvPriceHomeItem.text = "${item.price.toString()} $"
        b.cvHomeItem.setOnClickListener {
            val transition = HomeFragmentDirections.homeToDetails(item)
            Navigation.findNavController(it).navigate(transition)
        }
    }
}