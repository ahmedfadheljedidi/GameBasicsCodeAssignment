package com.gamebasics.ahmed.data.local

import com.gamebasics.ahmed.data.models.Team

interface FootballGroupCache {
    suspend fun getFootballGroup(groupSize: Int): List<Team>
}