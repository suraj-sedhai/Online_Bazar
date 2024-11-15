package com.example.onlinebazar.utils

import android.view.View
import androidx.fragment.app.Fragment
import com.example.onlinebazar.activities.ShoppingActivity
import nl.joery.animatedbottombar.AnimatedBottomBar

fun Fragment.hideBottomNavigationView(){
    val bottomNavigationView =
        (activity as ShoppingActivity).findViewById<AnimatedBottomBar>(
            com.example.onlinebazar.R.id.bottomNavigation
        )
    bottomNavigationView.visibility = android.view.View.GONE
}

fun Fragment.showBottomNavigationView(){
    val bottomNavigationView =
        (activity as ShoppingActivity).findViewById<AnimatedBottomBar>(
            com.example.onlinebazar.R.id.bottomNavigation
        )
    bottomNavigationView.visibility = android.view.View.VISIBLE
}