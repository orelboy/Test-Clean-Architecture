package com.practicum.testcleanarchitecture.ui.poster

import android.app.Activity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.practicum.testcleanarchitecture.util.Creator
import com.practicum.testcleanarchitecture.R
import com.practicum.testcleanarchitecture.databinding.ActivityDetailsBinding
import com.practicum.testcleanarchitecture.presentation.poster.PosterPresenter
import com.practicum.testcleanarchitecture.presentation.poster.PosterView

//class DetailsActivity : Activity(), PosterView {
//
//    private lateinit var posterPresenter: PosterPresenter
//
//    private lateinit var poster: ImageView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        // Мы не можем создать PosterPresenter раньше,
//        // потому что нам нужен imageUrl, который
//        // станет доступен только после super.onCreate
//        val imageUrl = intent.extras?.getString("poster", "") ?: ""
//        posterPresenter = Creator.providePosterPresenter(this, imageUrl)
//        setContentView(R.layout.activity_poster)
//        poster = findViewById(R.id.poster)
//
//        posterPresenter.onCreate()
//    }
//
//    override fun setupPosterImage(url: String) {
//        Glide.with(applicationContext)
//            .load(url)
//            .into(poster)
//    }
//}

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    private lateinit var tabMediator: TabLayoutMediator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val poster = intent.getStringExtra("poster") ?: ""
        val movieId = intent.getStringExtra("id") ?: ""

        binding.viewPager.adapter = DetailsViewPagerAdapter(
            fragmentManager = supportFragmentManager,
            lifecycle = lifecycle,
            posterUrl = poster,
            movieId = movieId,
        )

        tabMediator = TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when(position) {
                0 -> tab.text = getString(R.string.poster)
                1 -> tab.text = getString(R.string.details)
            }
        }
        tabMediator.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        tabMediator.detach()
    }

}