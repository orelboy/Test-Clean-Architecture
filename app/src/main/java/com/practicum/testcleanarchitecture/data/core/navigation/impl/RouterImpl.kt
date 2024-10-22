package com.practicum.testcleanarchitecture.data.core.navigation.impl

import androidx.fragment.app.Fragment
import com.practicum.testcleanarchitecture.data.core.navigation.NavigatorHolder
import com.practicum.testcleanarchitecture.data.core.navigation.Router

class RouterImpl: Router {
    override val navigatorHolder: NavigatorHolder = NavigatorHolderImpl()

    override fun openFragment(fragment: Fragment) {
        navigatorHolder.openFragment(fragment)
    }
}