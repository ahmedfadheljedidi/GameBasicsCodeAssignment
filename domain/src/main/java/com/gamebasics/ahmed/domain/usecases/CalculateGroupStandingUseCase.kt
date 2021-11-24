package com.gamebasics.ahmed.domain.usecases

import com.gamebasics.ahmed.domain.models.FootballMatchResult
import com.gamebasics.ahmed.domain.models.Team
import com.gamebasics.ahmed.domain.models.TeamGroupPerformance
import com.gamebasics.ahmed.domain.utils.getTeamGroupPerformance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CalculateGroupStandingUseCase @Inject constructor() {

    suspend fun calculateStandings(
        teams: List<Team>,
        results: List<FootballMatchResult>
    ): List<TeamGroupPerformance> =
        withContext(Dispatchers.Default) {
            return@withContext teams.map { results.getTeamGroupPerformance(it) }
                .sortedWith(
                    compareByDescending<TeamGroupPerformance> { it.points }
                        .thenByDescending { it.goalDifference }
                        .thenByDescending { it.goalsFor }
                        .thenBy { it.goalsAgainst }
                    //TODO: Finish Mutual Result ranking.
                )
        }
}