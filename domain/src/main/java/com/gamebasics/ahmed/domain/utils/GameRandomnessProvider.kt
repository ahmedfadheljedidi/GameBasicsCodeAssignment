package com.gamebasics.ahmed.domain.utils

import javax.inject.Inject

class GameRandomnessProvider @Inject constructor() {
    /**
     * Function returning the random nature of a football match
     * @return A random number ranging from -20 to 20
     */
    internal fun provideGameRandomness() = (-20..20).random()
}
