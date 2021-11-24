package com.gamebasics.ahmed.domain.utils

const val POINTS_WON = 3
const val POINTS_DRAW = 1
const val POINTS_LOST = 0
internal fun getMatchPointsFromScore(goalsFor: Int, goalsAgainst: Int): Int {
    return when {
        goalsFor > goalsAgainst -> POINTS_WON
        goalsFor == goalsAgainst -> POINTS_DRAW
        else -> POINTS_LOST
    }
}