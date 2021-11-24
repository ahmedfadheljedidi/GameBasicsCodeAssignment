package com.gamebasics.ahmed.ahmedfootballgroupsimulator.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gamebasics.ahmed.ahmedfootballgroupsimulator.R
import com.gamebasics.ahmed.ahmedfootballgroupsimulator.databinding.HolderTeamStandingBinding
import com.gamebasics.ahmed.ahmedfootballgroupsimulator.viewStates.TeamPerformance

internal class TeamsStandingsAdapter() : RecyclerView.Adapter<TeamStandingViewHolder>() {

    var teamsStanding: List<TeamPerformance> = emptyList()
        set(value) {
            if (field != value) {
                field = value
                //Could use diff utils if business expects huge groups..
                notifyDataSetChanged()
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamStandingViewHolder {
        val binding = HolderTeamStandingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamStandingViewHolder(binding)
    }

    override fun getItemCount(): Int = teamsStanding.size

    override fun onBindViewHolder(holder: TeamStandingViewHolder, position: Int) {
        holder.bind(teamsStanding[position])
    }
}

internal class TeamStandingViewHolder(private val binding: HolderTeamStandingBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(team: TeamPerformance) {
        val context = binding.root.context
        binding.TeamNameTv.text = team.teamName
        binding.teamPoints.text = context.getString(R.string.team_points, team.points)
        binding.teamGoalDifference.text =
            context.getString(R.string.team_goals_difference, team.goalDifference)
        binding.teamGoalsFor.text = context.getString(R.string.team_goals_for, team.goalsFor)
        binding.teamGoalsAgainst.text =
            context.getString(R.string.team_goals_against, team.goalsAgainst)
    }
}