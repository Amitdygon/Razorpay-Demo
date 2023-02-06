package com.okdap.razorpaydemo.ui.main1

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.okdap.razorpaydemo.R
import com.okdap.razorpaydemo.databinding.ActivityMainBinding
import com.okdap.razorpaydemo.ui.result.ResultActivity
import com.okdap.razorpaydemo.utils.IntenetConstant
import com.okdap.razorpaydemo.utils.ValidationCode
import com.okdap.razorpaydemo.utils.setError
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), TextWatcher {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = mainViewModel
        binding.lifecycleOwner = this

        setObservers()

    }

    private fun setObservers() {
        mainViewModel.getErrorValidation().observe(this) {
            it ?: return@observe
            when (it.type) {
                ValidationCode.NAME -> {
                    binding.tlName.setError(it.message, true)
                }
                ValidationCode.PHONE_NUMBER -> {
                    binding.tlPhone.setError(it.message, true)
                }
                ValidationCode.CITY -> {
                    binding.tlCity.setError(it.message, true)
                }
            }
        }

        mainViewModel.assignmentModelResponse.observe(this) {
            it ?: return@observe
            with(binding) {
                imageUrl = it.logoUrl
                executePendingBindings()
            }
            Log.e(TAG, "" + it)
        }

        mainViewModel.enterResponse.observe(this) {
            it ?: return@observe
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra(IntenetConstant.EnteredResponse, it)
            startActivity(intent)
        }


    }

    override fun onResume() {
        super.onResume()
        binding.etCity.addTextChangedListener(this)
        binding.etName.addTextChangedListener(this)
        binding.etPhone.addTextChangedListener(this)
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun afterTextChanged(p0: Editable?) {
        if (binding.tlName.isErrorEnabled) {
            binding.tlName.setError(null, false)
        }
        if (binding.tlCity.isErrorEnabled) {
            binding.tlCity.setError(null, false)
        }
        if (binding.tlPhone.isErrorEnabled) {
            binding.tlPhone.setError(null, false)
        }
    }
}