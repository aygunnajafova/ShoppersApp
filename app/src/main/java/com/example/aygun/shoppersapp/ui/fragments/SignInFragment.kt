package com.example.aygun.shoppersapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.aygun.shoppersapp.R
import com.example.aygun.shoppersapp.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSignInBinding.inflate(inflater,container,false)
        signIn()
        goToSignUp()
        return binding.root
    }

    private fun signIn() {
        binding.btnSignIn.setOnClickListener {
            findNavController().navigate(R.id.signInToHome)
        }
    }

    private fun goToSignUp() {
        binding.tvSignUp.setOnClickListener {
            findNavController().navigate(SignInFragmentDirections.signInToSignUp())
        }
    }

}