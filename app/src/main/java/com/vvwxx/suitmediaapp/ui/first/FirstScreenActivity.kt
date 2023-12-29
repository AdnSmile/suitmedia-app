package com.vvwxx.suitmediaapp.ui.first

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vvwxx.suitmediaapp.databinding.ActivityFirstScreenBinding
import com.vvwxx.suitmediaapp.di.ViewModelFactory
import com.vvwxx.suitmediaapp.ui.second.SecondScreenActivity

class FirstScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstScreenBinding

    private lateinit var factory: ViewModelFactory
//    private val viewModel: FirstViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        factory = ViewModelFactory.getInstance(this)

        binding.btnNext.setOnClickListener {
            val intent = Intent(this, SecondScreenActivity::class.java)
            startActivity(intent)
        }
    }
}