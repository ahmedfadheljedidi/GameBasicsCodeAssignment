package com.gamebasics.ahmed.ahmedfootballgroupsimulator.viewStates


sealed class TeamsStandingViewState {
    class Loaded(val teamsStanding: List<TeamPerformance>): TeamsStandingViewState()
    object Loading : TeamsStandingViewState()
}

data class TeamPerformance(
    val teamName: String,
    val points: Int,
    val goalDifference: Int,
    val goalsFor: Int,
    val goalsAgainst: Int
)


