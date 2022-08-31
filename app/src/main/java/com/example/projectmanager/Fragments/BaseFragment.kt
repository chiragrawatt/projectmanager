package com.example.projectmanager.Fragments

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projectmanager.R
import com.example.projectmanager.databinding.DialogProgressBinding

class BaseFragment : Fragment() {

    private lateinit var mProgressDialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base, container, false)
    }

    fun showProgressDialog(text: String) {
        mProgressDialog = Dialog(requireContext())

        val mProgressDialogBinding = DialogProgressBinding.inflate(layoutInflater)

        mProgressDialog.setContentView(mProgressDialogBinding.root)

        mProgressDialogBinding.tvProgressText.text = text

        mProgressDialog.show()
    }
    
    
}