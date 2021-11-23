package com.gamebasics.ahmed.data.repositories

import com.gamebasics.ahmed.data.local.FootballGroupCache
import com.gamebasics.ahmed.domain.models.Team
import com.gamebasics.ahmed.domain.repositories.GroupRepository
import javax.inject.Inject

internal class GroupRepositoryImp @Inject constructor(private val footballGroupCache: FootballGroupCache) :
    GroupRepository {
    override suspend fun getGroup(groupSize: Int): List<Team> {
        return footballGroupCache.getFootballGroup(groupSize)
            .map { Team(it.teamName, it.attack, it.midfield, it.defence, it.goalKeeper) }
    }
}