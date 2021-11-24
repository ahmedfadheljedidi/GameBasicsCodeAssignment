package com.gamebasics.ahmed.domain.utils

import com.gamebasics.ahmed.domain.models.FootballMatchResult
import com.gamebasics.ahmed.domain.models.Team
import com.gamebasics.ahmed.domain.models.TeamGroupPerformance

fun Team.getTotalStrength() = attack + midfield + defence + goalKeeper
fun Team.getAttackingStrength() = attack + midfield
fun Team.getDefendingStrength() = defence + goalKeeper

fun List<FootballMatchResult>.getTeamGroupPerformance(team: Team): TeamGroupPerformance {
    var goalsAgainst = 0
    var goalsFor = 0
    var points = 0
    forEach { footballMatchResult ->
        with(footballMatchResult) {
            if (firstTeamPerformance.team == team) {
                goalsFor += firstTeamPerformance.goalsScored
                goalsAgainst += secondTeamPerformance.goalsScored
                points += getMatchPointsFromScore(
                    firstTeamPerformance.goalsScored,
                    secondTeamPerformance.goalsScored
                )
            } else if (secondTeamPerformance.team == team) {
                goalsFor += secondTeamPerformance.goalsScored
                goalsAgainst += firstTeamPerformance.goalsScored
                points += getMatchPointsFromScore(
                    secondTeamPerformance.goalsScored,
                    firstTeamPerformance.goalsScored
                )
            }
        }
    }
    return TeamGroupPerformance(
        team = team,
        points = points,
        goalDifference = goalsFor - goalsAgainst,
        goalsFor = goalsFor,
        goalsAgainst = goalsAgainst
    )
}
