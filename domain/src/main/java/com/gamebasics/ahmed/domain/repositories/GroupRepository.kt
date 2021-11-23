package com.gamebasics.ahmed.domain.repositories

import com.gamebasics.ahmed.domain.models.Team

interface GroupRepository {
    suspend fun getGroup(groupSize: Int):  List<Team>
}