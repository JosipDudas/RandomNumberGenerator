package com.dudas.randomnogenerator.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dudas.randomnogenerator.R
import com.dudas.randomnogenerator.ui.main.coins.CoinFragment
import com.dudas.randomnogenerator.ui.main.lotto.LottoFragment
import com.dudas.randomnogenerator.ui.main.rng.RngFragment

private val TAB_TITLES = arrayOf(
        R.string.tab_rng,
        R.string.tab_lotto,
        R.string.tab_coins
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager)
    : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        when(position) {
            0 -> {
                return RngFragment.newInstance()
            }
            1 -> {
                return LottoFragment.newInstance()
            }
            2 -> {
                return CoinFragment.newInstance()
            }
            else -> {
                return PlaceholderFragment.newInstance(position + 1)
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 3 total pages.
        return 3
    }
}