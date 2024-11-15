package com.example.onlinebazar.activities

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.onlinebazar.R
import com.example.onlinebazar.utils.Resource
import com.example.onlinebazar.viewmodel.factory.CartViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import nl.joery.animatedbottombar.AnimatedBottomBar

@AndroidEntryPoint
class ShoppingActivity : AppCompatActivity(R.layout.activity_shopping) {
    private lateinit var navController: NavController
    private lateinit var bottomBar: AnimatedBottomBar
    val viewModel by viewModels<CartViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        bottomBar = findViewById(R.id.bottomNavigation)
        navController = findNavController(R.id.fragmentContainerView3)
        setupActionBarWithNavController(navController)

        lifecycleScope.launchWhenStarted {
            viewModel.cartProducts.collectLatest {
                when (it) {
                    is Resource.Success -> {
                        val count = it.data?.size ?.toString()?:"0"
                        bottomBar.setBadgeAtTabIndex(2, AnimatedBottomBar.Badge(text = count))
                    }
                    else -> Unit
                }
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_navigation_menu, menu)
        bottomBar.setupWithNavController(menu!!, navController)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        navController.navigateUp()
        return true
    }
}