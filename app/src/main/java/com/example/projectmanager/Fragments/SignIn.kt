package com.example.projectmanager.Fragments

import android.graphics.DiscretePathEffect
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.projectmanager.R
import com.example.projectmanager.databinding.FragmentSignInBinding
import com.example.projectmanager.databinding.FragmentSignUpBinding
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class SignIn : BaseFragment() {

    private lateinit var binding: FragmentSignInBinding
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(layoutInflater)

        binding.signInBtn.setOnClickListener {
            logInUser()
        }

        return binding.root
    }

    private fun logInUser() {
        val email: String = binding.emailEditText.text.toString().trimEnd()
        val password: String = binding.passwordEditText.text.toString()

        if(validateForm(email, password)) {
            showProgressDialog("Please wait...")
            val firebaseAuth = FirebaseAuth.getInstance()

            lifecycleScope.launch(Dispatchers.IO) {
                val result = logIn(firebaseAuth, email, password)
                hideProgressDialog()

                if(result!=null) {
                    val registeredEmail = result.user?.email
                    withContext(Dispatchers.Main) {
                        Toast.makeText (
                            context,
                            "You have successfully registered the email address $registeredEmail",
                            Toast.LENGTH_SHORT
                        ).show()
                        firebaseAuth.signOut()
                    }
                }
            }
        }
    }

    private suspend fun logIn(firebaseAuth: FirebaseAuth, email: String, password: String) : AuthResult? {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            result
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    context,
                    "${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
            null
        }
    }

    private fun validateForm(email: String, password: String) : Boolean {
        return when {
            TextUtils.isEmpty(email) -> {
                showSnackBarError("Please enter your email")
                false
            }
            TextUtils.isEmpty(password) -> {
                showSnackBarError("Please enter a password")
                false
            }
            else -> true
        }
    }
}