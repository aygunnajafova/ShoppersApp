package com.example.aygun.shoppersapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.aygun.shoppersapp.R
import com.example.aygun.shoppersapp.data.entity.BasketItem
import com.example.aygun.shoppersapp.data.entity.HomeItem
import com.example.aygun.shoppersapp.databinding.FragmentDetailsBinding
import com.example.aygun.shoppersapp.room.AppDatabase
import com.example.aygun.shoppersapp.room.BasketItemDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var db: AppDatabase
    private lateinit var bidao: BasketItemDao
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailsBinding.inflate(inflater,container,false)

        val bundle: DetailsFragmentArgs by navArgs()
        val item = bundle.homeItem


        //val item = HomeItem(1,"nike",250.0,"New collection item for you!","nike.jpg")
        val resourceId = requireContext().resources.getIdentifier(item.image,"drawable",requireContext().packageName)
        binding.ivDetails.setImageResource(resourceId)
        binding.tvNameDetails.text = item.name
        binding.tvPriceDetails.text = "${item.price.toString()} $"
        binding.tvDescriptionDetails.text = item.description

        //add
        db = AppDatabase.dataAccess(requireContext())!!
        bidao = db.getBasketItemDao()
        addToCart(item)
        return binding.root
    }

    private fun addToCart(item: HomeItem) {
        binding.btnDetails.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.detailsToBasket)
            val job = CoroutineScope(Dispatchers.Main).launch {
                val basketItems = bidao.getAllBasketItems()
                val basketItem = BasketItem(item.id,item.name,1,item.price,item.image)
                var cond = true
                for(item in basketItems) {
                    if(basketItem.id == item.id) {
                        cond=false
                        basketItem.quantity = item.quantity
                        break
                    }
                }
                if(cond) {
                    bidao.addBasketItems(basketItem)
                }
                else {
                    basketItem.quantity ++
                    bidao.updateBasketItems(basketItem)
                }
            }

        }
    }

}