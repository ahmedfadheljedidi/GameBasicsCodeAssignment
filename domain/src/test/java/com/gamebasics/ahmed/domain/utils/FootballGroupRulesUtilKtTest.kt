package com.gamebasics.ahmed.domain.utils

import org.junit.Test
import org.junit.jupiter.api.Assertions.*

internal class FootballGroupRulesUtilKtTest {

    @Test
    fun `When a team scored more goals than its opponent, Then it gets 3 points`() {
        val points  = getMatchPointsFromScore(3,0)
        assertTrue(points == 3)
    }

    @Test
    fun `When a team scored same number of goals as its opponent, Then it gets 1 point`() {
        val points  = getMatchPointsFromScore(3,3)
        assertTrue(points == 1)
    }

    @Test
    fun `When a team scored less goals than its opponent, Then it gets 0 points`() {
        val points  = getMatchPointsFromScore(0,3)
        assertTrue(points == 0)
    }
}