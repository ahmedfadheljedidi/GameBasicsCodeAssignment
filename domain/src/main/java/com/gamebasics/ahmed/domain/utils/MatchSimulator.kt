package com.gamebasics.ahmed.domain.utils

import com.gamebasics.ahmed.domain.models.FootballMatch
import com.gamebasics.ahmed.domain.models.FootballMatchResult
import javax.inject.Inject

class MatchSimulator @Inject constructor() {

    fun playMatch(match: FootballMatch, gameRandomness: Int = 0): FootballMatchResult {
        val firstTeam = match.firstTeam
        val secondTeam = match.SecondTeam

        val firstTeamGoals = calculateGoalsBasedOnStrength(
            firstTeam.getAttackingStrength() - secondTeam.getDefendingStrength() - gameRandomness
        )

        val secondTeamGoals = calculateGoalsBasedOnStrength(
            secondTeam.getAttackingStrength() - firstTeam.getDefendingStrength() - gameRandomness
        )

        val firstTeamPerformance = FootballMatchResult.TeamPerformance(firstTeam, firstTeamGoals)
        val secondTeamPerformance = FootballMatchResult.TeamPerformance(secondTeam, secondTeamGoals)
        return FootballMatchResult(firstTeamPerformance, secondTeamPerformance)


    }

    private fun calculateGoalsBasedOnStrength(power: Int): Int {
        return when {
            //If power difference is strictly positive then the attacking team scores a goal automatically,
            // and for every additional 30 points of difference the attacking team scores another goal.
            power > 0 -> 1 + power / 30
            else -> 0
        }
    }
}