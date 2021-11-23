package com.gamebasics.ahmed.data.local

import com.gamebasics.ahmed.data.factories.TeamFactory
import com.gamebasics.ahmed.data.models.Team
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class FootballGroupCacheImpl(private val teamFactory: TeamFactory) : FootballGroupCache {
    private var teams: List<Team>? = null

    override suspend fun getFootballGroup(groupSize: Int): List<Team> {
        return teams ?: withContext(Dispatchers.Default) {
            val tempTeams = mutableListOf<Team>()
            for (i in 1..groupSize) {
                tempTeams.add(teamFactory.generateTeam())
            }
            teams = tempTeams
            return@withContext tempTeams
        }
    }
}