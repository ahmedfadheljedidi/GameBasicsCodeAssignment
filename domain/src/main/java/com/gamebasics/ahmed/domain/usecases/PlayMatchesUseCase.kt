package com.gamebasics.ahmed.domain.usecases

import com.gamebasics.ahmed.domain.models.FootballMatch
import com.gamebasics.ahmed.domain.models.FootballMatchResult
import com.gamebasics.ahmed.domain.utils.GameRandomnessProvider
import com.gamebasics.ahmed.domain.utils.MatchSimulator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PlayMatchesUseCase @Inject constructor(
    private val matchSimulator: MatchSimulator,
    private val gameRandomnessProvider: GameRandomnessProvider
) {

    suspend fun playMatches(matches: List<FootballMatch>): List<FootballMatchResult> =
        withContext(Dispatchers.Default) {
            return@withContext matches.map {
                matchSimulator.playMatch(
                    it,
                    gameRandomnessProvider.provideGameRandomness()
                )
            }
        }


}