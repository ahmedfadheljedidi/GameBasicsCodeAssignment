package com.gamebasics.ahmed.data.factories

import com.gamebasics.ahmed.data.models.Team
import javax.inject.Inject

internal class TeamFactory @Inject constructor() {
     fun generateTeam(): Team {
         val attack = (0..100).random()
         val midfield = (0..100).random()
         val defence = (0..100).random()
         val goalKeeper = (0..100).random()

         //Just for the fun of it, i will call the teams by their strengths
         val teamName = "Team ${attack + midfield + defence + goalKeeper}"

         return Team(teamName, attack, midfield, defence, goalKeeper)
    }
}