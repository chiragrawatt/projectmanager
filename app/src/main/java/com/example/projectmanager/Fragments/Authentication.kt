package com.example.projectmanager.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.projectmanager.R
import com.example.projectmanager.databinding.FragmentAuthenticationBinding

class Authentication : Fragment() {

    private lateinit var binding: FragmentAuthenticationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthenticationBinding.inflate(layoutInflater)

        binding.signUpBtn.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_authentication_to_signUp)
        }

        binding.logInBtn.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_authentication_to_signIn)
        }

        return binding.root
    }
}