package com.example.aygun.shoppersapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.aygun.shoppersapp.databinding.FragmentHomeBinding
import com.example.aygun.shoppersapp.room.AppDatabase
import com.example.aygun.shoppersapp.room.HomeItemDao
import com.example.aygun.shoppersapp.ui.adapter.HomeAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var db: AppDatabase
    private lateinit var hdao: HomeItemDao
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        db = AppDatabase.dataAccess(requireContext())!!
        hdao = db.getHomeItemDao()
        setHomeAdapter()

        return binding.root
    }

    private fun setHomeAdapter() {
        binding.rvHome.layoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)

        val job = CoroutineScope(Dispatchers.Main).launch {
            val homeItems = hdao.getAllHomeItems()

            binding.rvHome.adapter = HomeAdapter(homeItems)
        }
    }

}