package com.practicum.testcleanarchitecture.ui.root

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.practicum.testcleanarchitecture.R
import com.practicum.testcleanarchitecture.data.core.navigation.NavigatorHolder
import com.practicum.testcleanarchitecture.data.core.navigation.impl.NavigatorImpl
import com.practicum.testcleanarchitecture.databinding.ActivityRootBinding
import com.practicum.testcleanarchitecture.ui.movies.MoviesFragment
import org.koin.android.ext.android.inject

class RootActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRootBinding

    private val navigatorHolder: NavigatorHolder by inject()
    // Создали Navigator
    private val navigator = NavigatorImpl(
        fragmentContainerViewId = R.id.rootFragmentContainerView,
        fragmentManager = supportFragmentManager
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Привязываем вёрстку к экрану
        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            // С помощью навигатора открываем первый экран
            navigator.openFragment(
                MoviesFragment()
            )
        }
    }

    // Прикрепляем Navigator к NavigatorHolder
    override fun onResume() {
        super.onResume()
        navigatorHolder.attachNavigator(navigator)
    }

    // Открепляем Navigator от NavigatorHolder
    override fun onPause() {
        super.onPause()
        navigatorHolder.detachNavigator()
    }
}