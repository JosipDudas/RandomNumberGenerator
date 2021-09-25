package com.dudas.randomnogenerator.ui.main.rng

import androidx.lifecycle.ViewModel
import kotlinx.android.synthetic.main.rng_fragment.*
import kotlin.random.Random

class RngViewModel : ViewModel() {
    var minValue: Int = 1
    var maxValue: Int = 50
    var listSize: Int = 1
    var excludeNumbers: List<Int> = mutableListOf<Int>()
    var duplicate: Boolean = false

    fun getRandomNumber(): List<Int> {
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

    fun checkIfListIsToBig(): Boolean {
        var tooBig = false

        val diffBetweenMaxMin = maxValue - minValue
        val diffBetweenListSizeAndMinMax = diffBetweenMaxMin - listSize

        if (diffBetweenListSizeAndMinMax < 0)
            tooBig = true

        return tooBig
    }
}