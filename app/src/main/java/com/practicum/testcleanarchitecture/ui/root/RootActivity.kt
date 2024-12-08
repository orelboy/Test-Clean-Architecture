package com.practicum.testcleanarchitecture.ui.root

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.practicum.testcleanarchitecture.R
import com.practicum.testcleanarchitecture.databinding.ActivityRootBinding


class RootActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.rootFragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.detailsFragment, R.id.moviesCastFragment -> {
                    binding.bottomNavigationView.visibility = View.GONE
                }
                else -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
            }
        }
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(enabled = true) {
            override fun handleOnBackPressed() {

                exitConfirmDialog().show()

            }
        })

    }
    fun animateBottomNavigationView() {
        binding.bottomNavigationView.visibility = View.GONE
    }


    private fun exitConfirmDialog(): MaterialAlertDialogBuilder {
        return MaterialAlertDialogBuilder(this)
            .setTitle("Вы действительно хотите выйти из приложения?")
            .setPositiveButton("Да") { dialog, which -> this.finish() }
            .setNegativeButton("Нет") { dialog, which -> }
    }
}