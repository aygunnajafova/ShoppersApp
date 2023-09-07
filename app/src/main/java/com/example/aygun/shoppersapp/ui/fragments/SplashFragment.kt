package com.example.aygun.shoppersapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.aygun.shoppersapp.MainActivity
import com.example.aygun.shoppersapp.R
import com.example.aygun.shoppersapp.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSplashBinding.inflate(layoutInflater,container,false)

        Handler().postDelayed(Runnable {
            findNavController().navigate(R.id.splashToSignIn)
        }, 3000)

        return binding.root
    }
}