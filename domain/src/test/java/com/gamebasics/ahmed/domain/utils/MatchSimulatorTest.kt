package com.gamebasics.ahmed.domain.utils

import com.gamebasics.ahmed.domain.models.FootballMatch
import com.gamebasics.ahmed.domain.models.Team
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

internal class MatchSimulatorTest {

    @Test
    fun `When simulating the result between a strong and a weak team, the stronger team scored more`() {
        val strongTeam = Team("", 100, 100, 100, 100)
        val weakTeam = Team("", 0, 0, 0, 0)
        val gameRandomness = 0
        val match = FootballMatch(strongTeam, weakTeam)
        val result = MatchSimulator().playMatch(match, gameRandomness)

        assertTrue(result.firstTeamPerformance.goalsScored > result.secondTeamPerformance.goalsScored)
    }

    @Test
    fun `When simulating the result between two equal teams, the result is a draw`() {
        val strongTeam = Team("", 100, 100, 0, 0)
        val weakTeam = Team("", 100, 100, 0, 0)
        val gameRandomness = 0
        val match = FootballMatch(strongTeam, weakTeam)
        val result = MatchSimulator().playMatch(match, gameRandomness)

        assertTrue(result.firstTeamPerformance.goalsScored == result.secondTeamPerformance.goalsScored)
    }
}