package com.gamewar.cryptotrading.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gamewar.cryptotrading.ui.watchlist.WatchlistFourFragment
import com.gamewar.cryptotrading.ui.watchlist.WatchlistOneFragment
import com.gamewar.cryptotrading.ui.watchlist.WatchlistThreeFragment
import com.gamewar.cryptotrading.ui.watchlist.WatchlistTwoFragment

private const val WATCHLIST_TABS = 4

class WatchlistViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, val navController: NavController): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return WATCHLIST_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when(position) {
            0 -> return WatchlistOneFragment(navController)
            1 -> return WatchlistTwoFragment(navController)
            2 -> return WatchlistThreeFragment(navController)
        }
        return WatchlistFourFragment(navController)
    }
}