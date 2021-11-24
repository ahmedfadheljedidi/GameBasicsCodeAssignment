package com.gamebasics.ahmed.ahmedfootballgroupsimulator.viewModels

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import com.gamebasics.ahmed.domain.usecases.CalculateGroupStandingUseCase
import com.gamebasics.ahmed.domain.usecases.GetTeamsUseCase
import com.gamebasics.ahmed.domain.usecases.MatchPlanningUseCase
import com.gamebasics.ahmed.domain.usecases.PlayMatchesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GroupSimulatorViewModel @Inject constructor(
    private val getTeamsUseCase: GetTeamsUseCase,
    private val matchPlanningUseCase: MatchPlanningUseCase,
    private val playMatchesUseCase: PlayMatchesUseCase,
    private val calculateGroupStandingUseCase: CalculateGroupStandingUseCase
) : ViewModel(), DefaultLifecycleObserver