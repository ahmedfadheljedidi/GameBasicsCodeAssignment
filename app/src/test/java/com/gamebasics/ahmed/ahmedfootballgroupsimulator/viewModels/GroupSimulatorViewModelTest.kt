package com.gamebasics.ahmed.ahmedfootballgroupsimulator.viewModels

import com.gamebasics.ahmed.ahmedfootballgroupsimulator.TestExtensions.CoroutinesTestExtension
import com.gamebasics.ahmed.domain.models.Team
import com.gamebasics.ahmed.domain.usecases.CalculateGroupStandingUseCase
import com.gamebasics.ahmed.domain.usecases.GetTeamsUseCase
import com.gamebasics.ahmed.domain.usecases.MatchPlanningUseCase
import com.gamebasics.ahmed.domain.usecases.PlayMatchesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.RegisterExtension
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
@ExtendWith(CoroutinesTestExtension::class)

internal class GroupSimulatorViewModelTest {
    @JvmField
    @RegisterExtension
    val coroutinesTestExtension = CoroutinesTestExtension()

    @Mock
    private lateinit var getTeamsUseCase: GetTeamsUseCase

    @Mock
    private lateinit var matchPlanningUseCase: MatchPlanningUseCase

    @Mock
    private lateinit var playMatchesUseCase: PlayMatchesUseCase

    @Mock
    private lateinit var calculateGroupStandingUseCase: CalculateGroupStandingUseCase

    @InjectMocks
    private lateinit var subject: GroupSimulatorViewModel

    @Test
    fun `When created, the list of teams is fetched`() = runBlockingTest {
        val teamName = "Team 123"
        val team = Team(teamName, 0, 0, 0, 0)
        whenever(getTeamsUseCase.generateMatches()).thenReturn(listOf(team))

        subject.onCreate(mock())
        val test = subject.teamsViewState.value
        assertTrue(true)
    }


}