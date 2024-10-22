package com.practicum.testcleanarchitecture.data.core.navigation.impl

import androidx.fragment.app.Fragment
import com.practicum.testcleanarchitecture.data.core.navigation.Navigator
import com.practicum.testcleanarchitecture.data.core.navigation.NavigatorHolder

class NavigatorHolderImpl : NavigatorHolder {

    private var navigator: Navigator? = null

    override fun attachNavigator(navigator: Navigator) {
        this.navigator = navigator
    }

    override fun detachNavigator() {
        this.navigator = null
    }

    override fun openFragment(fragment: Fragment) {
        navigator?.openFragment(fragment)
    }

}