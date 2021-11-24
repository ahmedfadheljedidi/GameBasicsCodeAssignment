package com.gamebasics.ahmed.ahmedfootballgroupsimulator.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gamebasics.ahmed.ahmedfootballgroupsimulator.R
import com.gamebasics.ahmed.ahmedfootballgroupsimulator.databinding.HolderTeamBinding
import com.gamebasics.ahmed.ahmedfootballgroupsimulator.viewStates.TeamView

internal class TeamsAdapter() :
    RecyclerView.Adapter<TeamsViewHolder>() {

    var teams: List<TeamView> = emptyList()
        set(value) {
            if (field != value) {
                field = value
                //Could use diff utils if business expects huge groups..
                notifyDataSetChanged()
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsViewHolder {
        val binding = HolderTeamBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamsViewHolder(binding)
    }

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {
        holder.bind(teams[position])
    }
}

internal class TeamsViewHolder(private val binding: HolderTeamBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(team: TeamView) {
        val context = binding.root.context
        binding.TeamNameTv.text = team.teamName

        binding.teamAtt.text = context.getString(R.string.team_attack_strength, team.teamAtt)
        binding.teamMid.text = context.getString(R.string.team_midfield_strength, team.teamMid)
        binding.teamDef.text = context.getString(R.string.team_defence_strength, team.teamDef)
        binding.teamGK.text = context.getString(R.string.team_goalKeeper_strength, team.teamGK)
    }
}