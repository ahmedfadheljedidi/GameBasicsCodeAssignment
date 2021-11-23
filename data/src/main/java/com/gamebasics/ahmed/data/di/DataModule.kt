package com.gamebasics.ahmed.data.di

import com.gamebasics.ahmed.data.factories.TeamFactory
import com.gamebasics.ahmed.data.local.FootballGroupCache
import com.gamebasics.ahmed.data.local.FootballGroupCacheImpl
import com.gamebasics.ahmed.data.repositories.GroupRepositoryImp
import com.gamebasics.ahmed.domain.repositories.GroupRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Provides
    @Singleton
    internal fun provideFootballGroupCache(teamFactory: TeamFactory): FootballGroupCache {
        return FootballGroupCacheImpl(teamFactory)
    }

    @Provides
    @Singleton
    fun provideGroupRepository(footballGroupCache: FootballGroupCache): GroupRepository {
        return GroupRepositoryImp(footballGroupCache)
    }
}