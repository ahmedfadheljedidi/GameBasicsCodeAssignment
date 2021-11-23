package com.gamebasics.ahmed.domain.usecases

import com.gamebasics.ahmed.domain.models.FootballMatch
import com.gamebasics.ahmed.domain.models.Team
import com.gamebasics.ahmed.domain.repositories.GroupRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MatchPlanningUseCase @Inject constructor() {

    suspend fun planMatches(teams: List<Team>): List<FootballMatch> {
        return withContext(Dispatchers.Default) {
            val matches = mutableListOf<FootballMatch>()
            for (i in 0 until teams.size - 1) {
                for (j in i + 1 until teams.size) {
                    matches.add(FootballMatch(teams[i], teams[j]))
                }
            }
            return@withContext matches
        }
    }
}