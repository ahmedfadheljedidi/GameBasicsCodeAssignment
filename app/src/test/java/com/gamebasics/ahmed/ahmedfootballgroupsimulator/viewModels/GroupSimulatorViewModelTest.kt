package com.gamebasics.ahmed.ahmedfootballgroupsimulator.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gamebasics.ahmed.ahmedfootballgroupsimulator.testExtensions.CoroutinesTestExtension
import com.gamebasics.ahmed.ahmedfootballgroupsimulator.testExtensions.MainCoroutineRule
import com.gamebasics.ahmed.ahmedfootballgroupsimulator.viewStates.TeamsListViewState
import com.gamebasics.ahmed.ahmedfootballgroupsimulator.viewStates.TeamsStandingViewState
import com.gamebasics.ahmed.domain.models.FootballMatch
import com.gamebasics.ahmed.domain.models.FootballMatchResult
import com.gamebasics.ahmed.domain.models.Team
import com.gamebasics.ahmed.domain.models.TeamGroupPerformance
import com.gamebasics.ahmed.domain.usecases.CalculateGroupStandingUseCase
import com.gamebasics.ahmed.domain.usecases.GetTeamsUseCase
import com.gamebasics.ahmed.domain.usecases.MatchPlanningUseCase
import com.gamebasics.ahmed.domain.usecases.PlayMatchesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(CoroutinesTestExtension::class)

internal class GroupSimulatorViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private val getTeamsUseCase: GetTeamsUseCase = mockk()
    private val matchPlanningUseCase: MatchPlanningUseCase = mockk()
    private val playMatchesUseCase: PlayMatchesUseCase = mockk()
    private val calculateGroupStandingUseCase: CalculateGroupStandingUseCase = mockk()

    private val subject: GroupSimulatorViewModel by lazy {
        GroupSimulatorViewModel(
            getTeamsUseCase,
            matchPlanningUseCase,
            playMatchesUseCase,
            calculateGroupStandingUseCase
        )
    }

    @Test
    fun `When created, the list of teams is fetched`() {
        val teamName = "Team 123"
        val teams = listOf(Team(teamName, 0, 0, 0, 0))
        coEvery { getTeamsUseCase.generateMatches() } returns teams

        subject.onCreate(mockk())

        val testResult = subject.teamsViewState.value
        testResult as TeamsListViewState.Loaded
        assertEquals(teamName, testResult.teams[0].teamName)
    }

    @Test
    fun `When play matches, the list team performances is returned`() {

        val team1Name = "Team 123"
        val team2Name = "Team 456"
        val team1 = Team(team1Name, 0, 0, 0, 0)
        val team2 = Team(team2Name, 0, 0, 0, 0)
        val teams = listOf(team1, team2)
        coEvery { getTeamsUseCase.generateMatches() } returns teams
        subject.onCreate(mockk())

        val footballMatches = listOf(FootballMatch(team1, team2))
        coEvery { matchPlanningUseCase.planMatches(teams) } returns footballMatches

        val firstTeamPerformance = FootballMatchResult.TeamPerformance(team1, 3)
        val secondTeamPerformance = FootballMatchResult.TeamPerformance(team2, 2)
        val footballMatchResults =
            listOf(FootballMatchResult(firstTeamPerformance, secondTeamPerformance))
        coEvery { playMatchesUseCase.playMatches(footballMatches) } returns footballMatchResults

        val firstTeamGroupPerformance = TeamGroupPerformance(team1, 3, 1, 3, 2)
        val secondTeamGroupPerformance = TeamGroupPerformance(team2, 0, -1, 2, 3)
        coEvery {
            calculateGroupStandingUseCase.calculateStandings(
                teams,
                footballMatchResults
            )
        } returns listOf(firstTeamGroupPerformance, secondTeamGroupPerformance)

        subject.onCreate(mockk())
        subject.playMatches()

        val testResult = subject.teamsStandingViewState.value
        testResult as TeamsStandingViewState.Loaded
        assertEquals(team1Name, testResult.teamsStanding[0].teamName)
        assertEquals(firstTeamGroupPerformance.points, testResult.teamsStanding[0].points)
        assertEquals(firstTeamGroupPerformance.goalDifference, testResult.teamsStanding[0].goalDifference)
        assertEquals(firstTeamGroupPerformance.goalsFor, testResult.teamsStanding[0].goalsFor)
        assertEquals(firstTeamGroupPerformance.goalsAgainst, testResult.teamsStanding[0].goalsAgainst)

        assertEquals(team2Name, testResult.teamsStanding[1].teamName)
        assertEquals(secondTeamGroupPerformance.points, testResult.teamsStanding[1].points)
        assertEquals(secondTeamGroupPerformance.goalDifference, testResult.teamsStanding[1].goalDifference)
        assertEquals(secondTeamGroupPerformance.goalsFor, testResult.teamsStanding[1].goalsFor)
        assertEquals(secondTeamGroupPerformance.goalsAgainst, testResult.teamsStanding[1].goalsAgainst)
    }


}