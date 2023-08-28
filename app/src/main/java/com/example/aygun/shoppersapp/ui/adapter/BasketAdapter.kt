package com.example.aygun.shoppersapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aygun.shoppersapp.R
import com.example.aygun.shoppersapp.data.entity.BasketItem
import com.example.aygun.shoppersapp.databinding.BasketItemBinding
import com.example.aygun.shoppersapp.room.AppDatabase
import com.example.aygun.shoppersapp.room.BasketItemDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BasketAdapter(val basketItems: List<BasketItem>): RecyclerView.Adapter<BasketAdapter.BasketItemViewHolder>() {
    private lateinit var db: AppDatabase
    private lateinit var bidao: BasketItemDao

    inner class BasketItemViewHolder(val binding: BasketItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BasketItemBinding.inflate(inflater,parent,false)

        return BasketItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return basketItems.size
    }

    override fun onBindViewHolder(holder: BasketItemViewHolder, position: Int) {
        val item = basketItems[position]
        val b = holder.binding

        b.ivBasketItem.setImageResource(R.drawable.nike)
        b.tvNameBasketItem.text = item.name
        b.tvPriceBasketItem.text = "${item.price} $"
        b.tvQtyBasketItem.text = item.quantity.toString()
        b.tvQtyDecrease.setOnClickListener {
            if(item.quantity > 0) {
                item.quantity--
                update(item)
                b.tvQtyBasketItem.text = item.quantity.toString()
            }

        }
        b.tvQtyIncrease.setOnClickListener {
            item.quantity++
            update(item)
            b.tvQtyBasketItem.text = item.quantity.toString()
        }

        db = AppDatabase.dataAccess(b.clBasketItem.context)!!
        bidao = db.getBasketItemDao()

        b.ivDelete.setOnClickListener {
            delete(item)
        }

    }

    private fun delete(item: BasketItem) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            bidao.deleteBasketItems(item)
        }
    }

    private fun update(item: BasketItem) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            bidao.updateBasketItems(item)
        }
    }
}