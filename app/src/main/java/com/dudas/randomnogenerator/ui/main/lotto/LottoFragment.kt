package com.dudas.randomnogenerator.ui.main.lotto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dudas.randomnogenerator.R
import kotlinx.android.synthetic.main.lotto_eurojackpot.view.*
import kotlinx.android.synthetic.main.lotto_fragment.*
import kotlinx.android.synthetic.main.lotto_loto7.view.*


class LottoFragment : Fragment() {

    companion object {
        fun newInstance() = LottoFragment()
    }

    private lateinit var viewModel: LottoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.lotto_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LottoViewModel::class.java)

        setSpinner()
        setEuroJackpotLayout()
        setButtonGenerate()
    }

    private fun setButtonGenerate() {
        btn_generate_random_no_lotto.setOnClickListener {
            when(viewModel.lottoType) {
                0 -> {
                    setEuroJackpotLayout()
                }
                1 -> {
                    setLotto7Layout()
                }
                else -> {

                }
            }
        }
    }

    private fun setEuroJackpotLayout() {
        val fiveNumbers = viewModel.getRandomNumbers(1, 50, 5, false)
        val twoNumbers = viewModel.getRandomNumbers(1,10,2,false)

        val wizardView: View = layoutInflater
            .inflate(R.layout.lotto_eurojackpot, ll_placeholder_lotto, false)
        ll_placeholder_lotto.removeAllViews()
        ll_placeholder_lotto.addView(wizardView)

        ll_placeholder_lotto.txt_lotto_0.text = fiveNumbers[0].toString()
        ll_placeholder_lotto.txt_lotto_1.text = fiveNumbers[1].toString()
        ll_placeholder_lotto.txt_lotto_2.text = fiveNumbers[2].toString()
        ll_placeholder_lotto.txt_lotto_3.text = fiveNumbers[3].toString()
        ll_placeholder_lotto.txt_lotto_4.text = fiveNumbers[4].toString()

        ll_placeholder_lotto.txt_lotto_additional_0.text = twoNumbers[0].toString()
        ll_placeholder_lotto.txt_lotto_additional_1.text = twoNumbers[1].toString()
    }

    private fun setLotto7Layout() {
        val fiveNumbers = viewModel.getRandomNumbers(1, 35, 7, false)

        val wizardView: View = layoutInflater
            .inflate(R.layout.lotto_loto7, ll_placeholder_lotto, false)
        ll_placeholder_lotto.removeAllViews()
        ll_placeholder_lotto.addView(wizardView)

        ll_placeholder_lotto.txt_lotto7_0.text = fiveNumbers[0].toString()
        ll_placeholder_lotto.txt_lotto7_1.text = fiveNumbers[1].toString()
        ll_placeholder_lotto.txt_lotto7_2.text = fiveNumbers[2].toString()
        ll_placeholder_lotto.txt_lotto7_3.text = fiveNumbers[3].toString()
        ll_placeholder_lotto.txt_lotto7_4.text = fiveNumbers[4].toString()
        ll_placeholder_lotto.txt_lotto7_4.text = fiveNumbers[5].toString()

        ll_placeholder_lotto.txt_lotto7_additional_0.text = fiveNumbers[6].toString()
    }

    private fun setSpinner() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.lotto_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            sp_lotto.adapter = adapter
        }

        sp_lotto.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View,
                position: Int,
                id: Long
            ) {
                viewModel.lottoType = position
                when(position) {
                    0 -> {
                        setEuroJackpotLayout()
                    }
                    1 -> {
                        setLotto7Layout()
                    }
                    else -> {

                    }
                }
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
            }
        }
    }

}