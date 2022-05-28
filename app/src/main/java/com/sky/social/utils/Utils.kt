package com.sky.social.utils

import kotlin.random.Random

object Utils {

    fun generateRandomNumber(min: Int = 0, max: Int = 1000): Int {
        return Random.nextInt(min, max)
    }

}