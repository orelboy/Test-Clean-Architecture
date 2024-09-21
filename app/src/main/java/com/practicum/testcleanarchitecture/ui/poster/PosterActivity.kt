package com.practicum.testcleanarchitecture.ui.poster

import android.app.Activity
import android.os.Bundle
import com.practicum.testcleanarchitecture.util.Creator
import com.practicum.testcleanarchitecture.R

class PosterActivity : Activity() {

    private val posterController = Creator.providePosterController(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poster)
        posterController.onCreate()
    }
}