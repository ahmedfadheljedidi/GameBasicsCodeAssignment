package com.gamebasics.ahmed.domain.usecases

import com.gamebasics.ahmed.domain.models.Team
import com.gamebasics.ahmed.domain.repositories.GroupRepository
import javax.inject.Inject

class GetTeamsUseCase @Inject constructor(private val groupRepository: GroupRepository) {
    companion object {
        private const val GROUP_SIZE = 4
    }

    suspend fun generateMatches(): List<Team> {
        return groupRepository.getGroup(GROUP_SIZE)
    }
}