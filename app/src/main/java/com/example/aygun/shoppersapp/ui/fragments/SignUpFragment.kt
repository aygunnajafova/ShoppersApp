package com.example.aygun.shoppersapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.aygun.shoppersapp.R
import com.example.aygun.shoppersapp.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSignUpBinding.inflate(inflater,container,false)
        signUp()
        goToSignIn()
        return binding.root
    }

    private fun signUp() {
        binding.btnSignUp.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.signUpToHome)
        }
    }

    private fun goToSignIn() {
        binding.tvGoToSignIn.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.signUpToSignIn)
        }
    }

}