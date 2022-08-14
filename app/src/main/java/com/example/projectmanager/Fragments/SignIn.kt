package com.example.projectmanager.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projectmanager.R
import com.example.projectmanager.databinding.FragmentSignInBinding
import com.example.projectmanager.databinding.FragmentSignUpBinding

class SignIn : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(layoutInflater)
        return binding.root
    }
}