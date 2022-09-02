package com.example.projectmanager.Fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.projectmanager.databinding.FragmentSignUpBinding
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class SignUp : BaseFragment() {

    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(layoutInflater)

        binding.signUpBtn.setOnClickListener {
            registerUser()
        }

        return binding.root
    }

    private fun registerUser() {
        val name: String = binding.nameEditText.text.toString().trimEnd()
        val email: String = binding.emailEditText.text.toString().trimEnd()
        val password: String = binding.passwordEditText.text.toString()
        val confirmPassword: String = binding.confirmPasswordEditText.text.toString()

        if(validateForm(name, email, password, confirmPassword)) {
            showProgressDialog("Please wait...")
            val firebaseAuth = FirebaseAuth.getInstance()

            lifecycleScope.launch(Dispatchers.IO) {
                val result = createUser(firebaseAuth, email, password)
                hideProgressDialog()

                if(result!=null) {
                    val registeredEmail = result.user?.email
                    withContext(Dispatchers.Main) {
                        Toast.makeText (
                            context,
                            "$name you have successfully registered the email address $registeredEmail",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    firebaseAuth.signOut()
                }
            }
        }
    }

    private suspend fun createUser(firebaseAuth: FirebaseAuth, email: String, password: String) : AuthResult? {
        return try {
            val data = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            data
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

    private fun validateForm(name: String, email: String, password: String, confirmPassword: String) : Boolean {
        return when {
            TextUtils.isEmpty(name) -> {
                showSnackBarError("Please enter a name")
                false
            }
            TextUtils.isEmpty(email) -> {
                showSnackBarError("Please enter your email")
                false
            }
            TextUtils.isEmpty(password) -> {
                showSnackBarError("Please enter a password")
                false
            }
            TextUtils.isEmpty(confirmPassword) -> {
                showSnackBarError("Please confirm the password")
                false
            }
            !password.equals(confirmPassword) -> {
                showSnackBarError("Passwords do not match")
                false
            }
            else -> true
        }
    }
}