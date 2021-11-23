package com.gamebasics.ahmed.domain.models

data class FootballMatchResult(val firstTeamPerformance: TeamPerformance,
                               val secondTeamPerformance: TeamPerformance) {
    data class TeamPerformance(val team: Team, val goalsScored: Int)
}