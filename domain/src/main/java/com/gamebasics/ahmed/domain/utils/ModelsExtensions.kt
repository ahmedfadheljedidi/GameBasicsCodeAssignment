package com.gamebasics.ahmed.domain.utils

import com.gamebasics.ahmed.domain.models.FootballMatchResult
import com.gamebasics.ahmed.domain.models.Team
import com.gamebasics.ahmed.domain.models.TeamGroupPerformance

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

fun FootballMatchResult.isParticipant(team: Team): Boolean {
    return when (team) {
        firstTeamPerformance.team -> true
        secondTeamPerformance.team -> true
        else -> false
    }
}

fun List<FootballMatchResult>.getGoalsDiffBetween(team: Team, opponent: Team): Pair<Int, Int> {
    val matchResult = find { footballMatchResult ->
        footballMatchResult.isParticipant(team) &&
                footballMatchResult.isParticipant(opponent)
    }
    return matchResult?.let {
        if (it.firstTeamPerformance.team == team) {
            return@let it.firstTeamPerformance.goalsScored to it.secondTeamPerformance.goalsScored
        } else {
            return@let it.secondTeamPerformance.goalsScored to it.firstTeamPerformance.goalsScored
        }
    } ?: 0 to 0
}

