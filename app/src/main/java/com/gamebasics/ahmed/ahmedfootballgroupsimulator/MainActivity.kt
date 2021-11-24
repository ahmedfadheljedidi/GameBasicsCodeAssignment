package com.gamebasics.ahmed.ahmedfootballgroupsimulator

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.gamebasics.ahmed.ahmedfootballgroupsimulator.adapters.TeamsAdapter
import com.gamebasics.ahmed.ahmedfootballgroupsimulator.adapters.TeamsStandingsAdapter
import com.gamebasics.ahmed.ahmedfootballgroupsimulator.databinding.ActivityMainBinding
import com.gamebasics.ahmed.ahmedfootballgroupsimulator.viewModels.GroupSimulatorViewModel
import com.gamebasics.ahmed.ahmedfootballgroupsimulator.viewStates.TeamsListViewState
import com.gamebasics.ahmed.ahmedfootballgroupsimulator.viewStates.TeamsStandingViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), LifecycleOwner {
    private val viewModel: GroupSimulatorViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycle.addObserver(viewModel)
        initTeamsRecycler()
        initTeamsStandingRecycler()
        initPlayMatchesInteraction()
        observeTeamsViewState()
        observeStandingsViewState()
    }

    private fun initTeamsRecycler() {
        binding.teamsRv.adapter = TeamsAdapter()
    }

    private fun initTeamsStandingRecycler() {
        binding.groupStandingRv.adapter = TeamsStandingsAdapter()
    }

    private fun initPlayMatchesInteraction() {
        binding.playGroupBtn.setOnClickListener { viewModel.playMatches() }
    }

    private fun observeTeamsViewState() {
        viewModel.teamsViewState.observe(this, { viewState ->
            when (viewState) {
                is TeamsListViewState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.teamsRv.visibility = View.GONE
                    binding.playGroupBtn.visibility = View.GONE
                }
                is TeamsListViewState.Loaded -> {
                    binding.progressBar.visibility = View.GONE
                    binding.teamsRv.visibility = View.VISIBLE
                    binding.playGroupBtn.visibility = View.VISIBLE

                    (binding.teamsRv.adapter as TeamsAdapter).teams = viewState.teams
                }
            }
        })
    }

    private fun observeStandingsViewState() {
        viewModel.teamsStandingViewState.observe(this, { viewState ->
            when (viewState) {
                is TeamsStandingViewState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.groupStandingRv.visibility = View.GONE
                }
                is TeamsStandingViewState.Loaded -> {
                    binding.progressBar.visibility = View.GONE

                    binding.groupStandingRv.visibility = View.VISIBLE
                    (binding.groupStandingRv.adapter as TeamsStandingsAdapter).teamsStanding =
                        viewState.teamsStanding
                }
            }
        })
    }
}
