package com.dudas.randomnogenerator.ui.main.rng

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dudas.randomnogenerator.R
import kotlinx.android.synthetic.main.result_dialog.*
import kotlinx.android.synthetic.main.rng_fragment.*


class RngFragment : Fragment() {

    companion object {
        fun newInstance() = RngFragment()
    }

    private lateinit var viewModel: RngViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.rng_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RngViewModel::class.java)

        setMinMaxValuesForNumberPicker()
        setMinMaxOnchangeListeners()
        setListSizeOnchangeListener()
        setBtnGenerateRandomListener()
        setDuplicateOnclickListener()
    }

    private fun setDuplicateOnclickListener() {
        cb_duplicate.isChecked = viewModel.duplicate

        cb_duplicate.setOnCheckedChangeListener { _, isChecked ->
            viewModel.duplicate = isChecked
        }
    }

    private fun setBtnGenerateRandomListener() {
        btn_generate_random_no.setOnClickListener {
            val resultText = getRandomNumbers()
            openResultDialog(resultText)
        }
    }

    private fun getRandomNumbers(): String {
        var resultText = ""
        val listIsTooBig = viewModel.checkIfListIsToBig()

        if (listIsTooBig && !viewModel.duplicate) {
            resultText = resources.getString(R.string.txt_list_size_error)
        } else {
            val randomNumbers = viewModel.getRandomNumber()

            for ( i in 0 until randomNumbers.count()) {
                when(i) {
                    0 ->
                        resultText = randomNumbers[i].toString()
                    else ->
                        resultText = resultText + ", " + randomNumbers[i].toString()
                }
            }
        }

        return resultText
    }

    private fun setListSizeOnchangeListener() {
        np_list_size.value = viewModel.listSize



        np_list_size.setListener { value ->
            viewModel.listSize = value
        }
    }

    private fun setMinMaxOnchangeListeners() {
        np_min.setOnValueChangedListener { _, _, newVal ->
            viewModel.minValue = newVal

            if (viewModel.maxValue <= viewModel.minValue) {
                viewModel.maxValue = viewModel.minValue + 1
                np_max.value = viewModel.minValue + 1
            }
        }

        np_max.setOnValueChangedListener { _, _, newVal: Int ->
            viewModel.maxValue = newVal

            if (viewModel.maxValue <= viewModel.minValue) {
                viewModel.minValue = viewModel.maxValue - 1
                np_min.value = viewModel.maxValue - 1
            }
        }
    }

    private fun setMinMaxValuesForNumberPicker() {
        np_min.minValue = 0
        np_min.maxValue = Int.MAX_VALUE - 2

        np_max.minValue = 1
        np_max.maxValue = Int.MAX_VALUE - 1

        np_min.value = viewModel.minValue
        np_max.value = viewModel.maxValue
    }

    private fun openResultDialog(resultText: String) {
        val dialog = Dialog(requireActivity())
        dialog.setContentView(R.layout.result_dialog)

        dialog.btn_close.setOnClickListener {
            dialog.dismiss()
        }

        dialog.txt_random_number.text = resultText

        dialog.btn_generate_random_no_dialog.setOnClickListener {
            val resultTextDialog = getRandomNumbers()
            dialog.txt_random_number.text = resultTextDialog
        }

        dialog.show()
    }
}