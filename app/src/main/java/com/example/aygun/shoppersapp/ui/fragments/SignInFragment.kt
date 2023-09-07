package com.example.aygun.shoppersapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.aygun.shoppersapp.R
import com.example.aygun.shoppersapp.databinding.FragmentSignInBinding
import com.example.aygun.shoppersapp.room.AppDatabase
import com.example.aygun.shoppersapp.room.UserDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    private lateinit var db: AppDatabase
    private lateinit var udao: UserDao
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSignInBinding.inflate(inflater,container,false)

        db = AppDatabase.dataAccess(requireContext())!!
        udao = db.getUserDao()

        signIn()
        goToSignUp()
        return binding.root
    }

    private fun signIn() {
        binding.btnSignIn.setOnClickListener {
            val job = CoroutineScope(Dispatchers.Main).launch() {
                val users = udao.getAllUsers()
                val username = binding.edtUsernameSignIn.text.toString()
                val psw = binding.edtPasswordSignIn.text.toString()
                var res = false
                for(user in users) {
                    if(username == user.username && psw == user.password) {
                        res = true
                        break
                    }
                }
                if(res) {
                    Toast.makeText(requireContext(),"Welcome back $username!",Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.signInToContainerFragment)
                } else {
                    Toast.makeText(requireContext(),"Your username or password is wrong!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun goToSignUp() {
        binding.tvSignUp.setOnClickListener {
            findNavController().navigate(SignInFragmentDirections.signInToSignUp())
        }
    }

}