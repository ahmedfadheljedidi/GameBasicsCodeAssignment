package com.gamebasics.ahmed.ahmedfootballgroupsimulator.viewModels

import androidx.lifecycle.*
import com.gamebasics.ahmed.ahmedfootballgroupsimulator.viewStates.TeamPerformance
import com.gamebasics.ahmed.ahmedfootballgroupsimulator.viewStates.TeamView
import com.gamebasics.ahmed.ahmedfootballgroupsimulator.viewStates.TeamsListViewState
import com.gamebasics.ahmed.ahmedfootballgroupsimulator.viewStates.TeamsStandingViewState
import com.gamebasics.ahmed.domain.models.Team
import com.gamebasics.ahmed.domain.usecases.CalculateGroupStandingUseCase
import com.gamebasics.ahmed.domain.usecases.GetTeamsUseCase
import com.gamebasics.ahmed.domain.usecases.MatchPlanningUseCase
import com.gamebasics.ahmed.domain.usecases.PlayMatchesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupSimulatorViewModel @Inject constructor(
    private val getTeamsUseCase: GetTeamsUseCase,
    private val matchPlanningUseCase: MatchPlanningUseCase,
    private val playMatchesUseCase: PlayMatchesUseCase,
    private val calculateGroupStandingUseCase: CalculateGroupStandingUseCase
) : ViewModel(), DefaultLifecycleObserver {

    val teamsViewState: MutableLiveData<TeamsListViewState> by lazy { MutableLiveData() }
    val teamsStandingViewState: MutableLiveData<TeamsStandingViewState> by lazy { MutableLiveData() }

    lateinit var teams: List<Team>


    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        fetchTeams()
    }

    fun playMatches() {
        if (!::teams.isInitialized) {
            throw IllegalStateException("Loading teams should happen first")
        }
        viewModelScope.launch {
            teamsStandingViewState.value = TeamsStandingViewState.Loading

            //Planning and scores for each matches could also be displayed,
            // but it won't add much value to the assignment,
            // and for the interest of time i am jumping directly to display the standings
            val planning = matchPlanningUseCase.planMatches(teams)
            val playedMatches = playMatchesUseCase.playMatches(planning)
            val standing = calculateGroupStandingUseCase.calculateStandings(teams, playedMatches)
            val teamsPerformance = standing.map {
                TeamPerformance(
                    it.team.teamName,
                    it.points,
                    it.goalDifference,
                    it.goalsFor,
                    it.goalsAgainst
                )
            }
            teamsStandingViewState.value = TeamsStandingViewState.Loaded(teamsPerformance)
        }
    }

    private fun fetchTeams() {
        viewModelScope.launch {
            teamsViewState.value = TeamsListViewState.Loading
            teams = getTeamsUseCase.generateMatches()

            val teamsView = teams.map {
                TeamView(
                    it.teamName,
                    it.attack,
                    it.midfield,
                    it.defence,
                    it.goalKeeper
                )
            }
            teamsViewState.value = TeamsListViewState.Loaded(teamsView)
        }
    }
}