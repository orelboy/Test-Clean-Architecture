package com.practicum.testcleanarchitecture.ui.root

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.practicum.testcleanarchitecture.databinding.ActivityRootBinding


class RootActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}