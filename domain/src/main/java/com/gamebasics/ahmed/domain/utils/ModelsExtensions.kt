package com.gamebasics.ahmed.domain.utils

import com.gamebasics.ahmed.domain.models.Team

fun Team.getTotalStrength() = attack + midfield + defence + goalKeeper
fun Team.getAttackingStrength() = attack + midfield
fun Team.getDefendingStrength() = defence + goalKeeper
