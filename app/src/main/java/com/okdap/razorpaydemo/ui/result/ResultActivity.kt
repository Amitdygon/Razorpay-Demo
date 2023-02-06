package com.okdap.razorpaydemo.ui.result

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.okdap.razorpaydemo.R
import com.okdap.razorpaydemo.data.model.EnteredModel
import com.okdap.razorpaydemo.databinding.ActivityResultBinding
import com.okdap.razorpaydemo.utils.IntenetConstant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    private var enteredModel: EnteredModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result)
        setupUI()
    }

    private fun setupUI() {
        enteredModel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(IntenetConstant.EnteredResponse, EnteredModel::class.java)
        } else {
            intent.getParcelableExtra(IntenetConstant.EnteredResponse)
        }

        with(binding){
            enterModel = enteredModel
            executePendingBindings()
        }
    }
}