package com.example.watchlist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.watchlist.R
import com.example.watchlist.databinding.ActivityMainBinding
import com.example.watchlist.db.AnimeDatabase
import com.example.watchlist.repository.AnimeRepo

class MainActivity : AppCompatActivity() {
    // ctrl + alt + l
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: AnimeListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val animeRepo = AnimeRepo(AnimeDatabase.getInstance(this))
        val viewModelProviderFactory = AnimeListViewModelProviderFactory(animeRepo)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[AnimeListViewModel::class.java]


        // setting our bottom navigation menu
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.findNavController()
        binding.bottomNavigationMenu
            .setupWithNavController(navController)

        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

}