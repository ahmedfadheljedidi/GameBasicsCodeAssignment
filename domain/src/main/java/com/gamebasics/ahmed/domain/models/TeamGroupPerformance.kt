package com.gamebasics.ahmed.domain.models

data class TeamGroupPerformance(
    val team: Team,
    val points: Int,
    val goalDifference: Int,
    val goalsFor: Int,
    val goalsAgainst: Int
)
