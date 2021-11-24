package com.gamebasics.ahmed.ahmedfootballgroupsimulator.viewStates

sealed class TeamsListViewState {
    class Loaded(val teams: List<TeamView>): TeamsListViewState()
    object Loading : TeamsListViewState()
}

data class TeamView(
    val teamName: String,
    val teamAtt: Int,
    val teamMid: Int,
    val teamDef: Int,
    val teamGK: Int
)


