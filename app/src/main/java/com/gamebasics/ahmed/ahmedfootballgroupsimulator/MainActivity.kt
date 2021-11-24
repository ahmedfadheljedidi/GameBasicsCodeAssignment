package com.gamebasics.ahmed.ahmedfootballgroupsimulator

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.gamebasics.ahmed.ahmedfootballgroupsimulator.databinding.ActivityMainBinding
import com.gamebasics.ahmed.ahmedfootballgroupsimulator.viewModels.GroupSimulatorViewModel
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
    }
}