package com.dudas.randomnogenerator.ui.main.lotto

import androidx.lifecycle.ViewModel
import kotlin.random.Random

class LottoViewModel : ViewModel() {
    var lottoType: Int = 0

    fun getRandomNumbers(minValue: Int, maxValue: Int, listSize: Int, duplicate: Boolean): List<Int> {
        val randomNumbers = mutableListOf<Int>()
        for ( i in 0 until listSize) {
            var number: Int =  Random.nextInt(minValue, maxValue)

            var duplicateExist = false
            if (!duplicate) {
                do {
                    for (j in 0 until randomNumbers.count()) {
                        if (randomNumbers[j] == number) {
                            duplicateExist = true
                            number = Random.nextInt(minValue, maxValue)
                            break
                        } else {
                            duplicateExist = false
                        }
                    }
                } while (duplicateExist)
            }

            randomNumbers.add(number)
        }
        return randomNumbers
    }
}