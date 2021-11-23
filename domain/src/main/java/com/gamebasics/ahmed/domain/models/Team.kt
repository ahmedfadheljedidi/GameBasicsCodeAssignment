package com.gamebasics.ahmed.domain.models

data class Team(
    val teamName: String,
    val attack: Int,
    val midfield: Int,
    val defence: Int,
    val goalKeeper: Int
)