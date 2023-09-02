package com.example.aygun.shoppersapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aygun.shoppersapp.databinding.FragmentBasketBinding
import com.example.aygun.shoppersapp.room.AppDatabase
import com.example.aygun.shoppersapp.room.BasketItemDao
import com.example.aygun.shoppersapp.ui.adapter.BasketAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BasketFragment : Fragment() {
    private lateinit var binding: FragmentBasketBinding
    private lateinit var db: AppDatabase
    private lateinit var bdao: BasketItemDao

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBasketBinding.inflate(inflater,container,false)

        db = AppDatabase.dataAccess(requireContext())!!
        bdao = db.getBasketItemDao()

        setBasketAdapter()
        basketTotal()

        //val bundle: BasketFragmentArgs by navArgs()

        return binding.root
    }

    private fun setBasketAdapter() {
        binding.rvBasket.layoutManager = LinearLayoutManager(context)
        val job = CoroutineScope(Dispatchers.Main).launch {
            val basketItems = bdao.getAllBasketItems()
            binding.rvBasket.adapter = BasketAdapter(basketItems)
        }

    }

    private fun basketTotal(){
        val job = CoroutineScope(Dispatchers.Main).launch {
            var total = 0.0
            val basketItems = bdao.getAllBasketItems()
            for (i in basketItems) {
                total += i.quantity * i.price
            }
            binding.tvTotalBasket.text = "$total $"
        }

    }

    /*private fun getAll() : List<BasketItem> {
        lateinit var basketItems: List<BasketItem>
        val job = CoroutineScope(Dispatchers.Main).launch {
            basketItems = bidao.getAll()
        }
        return basketItems
    }*/

}