package com.example.aygun.shoppersapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.aygun.shoppersapp.R
import com.example.aygun.shoppersapp.data.entity.User
import com.example.aygun.shoppersapp.databinding.FragmentSignUpBinding
import com.example.aygun.shoppersapp.room.AppDatabase
import com.example.aygun.shoppersapp.room.UserDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var db: AppDatabase
    private lateinit var udao: UserDao
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSignUpBinding.inflate(inflater,container,false)

        db = AppDatabase.dataAccess(requireContext())!!
        udao = db.getUserDao()

        signUp()
        goToSignIn()
        return binding.root
    }

    private fun signUp() {
        binding.btnSignUp.setOnClickListener {
            val job = CoroutineScope(Dispatchers.Main).launch() {
                val id = udao.getAllUsers().last().id + 1
                val username = binding.edtNameSignUp.text.toString()
                val email = binding.edtEmailSignUp.text.toString()
                val psw = binding.edtPasswordSignUp.text.toString()
                val user = User(id,username,email,psw)
                if(username!="" && email!="" && psw!="") {
                    udao.add(user)
                    Toast.makeText(requireContext(),"Your account was created successfully!",Toast.LENGTH_SHORT).show()
                    Navigation.findNavController(it).navigate(R.id.signUpToContainerFragment)
                } else {
                    Toast.makeText(requireContext(),"You should full all sections!",Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    private fun goToSignIn() {
        binding.tvGoToSignIn.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.signUpToSignIn)
        }
    }

}