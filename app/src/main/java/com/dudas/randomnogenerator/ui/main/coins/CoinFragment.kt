package com.dudas.randomnogenerator.ui.main.coins

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dudas.randomnogenerator.R
import kotlinx.android.synthetic.main.coin_fragment.*
import java.util.*

class CoinFragment : Fragment() {

    val RANDOM: Random = Random()

    companion object {
        fun newInstance() = CoinFragment()
    }

    private lateinit var viewModel: CoinViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.coin_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CoinViewModel::class.java)

        btn_flip.setOnClickListener {
            flipCoin()
        }
    }

    private fun flipCoin() {
        val fadeOut: Animation = AlphaAnimation(1.toFloat(), 0.toFloat())
        fadeOut.interpolator = AccelerateInterpolator()
        fadeOut.duration = 1000
        fadeOut.fillAfter = true
        fadeOut.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                iv_coin.setImageResource(if (RANDOM.nextFloat() > 0.5f) R.drawable.tails else R.drawable.heads)
                val fadeIn: Animation = AlphaAnimation(0.toFloat(), 1.toFloat())
                fadeIn.interpolator = DecelerateInterpolator()
                fadeIn.duration = 3000
                fadeIn.fillAfter = true
                iv_coin.startAnimation(fadeIn)
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
        iv_coin.startAnimation(fadeOut)
    }
}