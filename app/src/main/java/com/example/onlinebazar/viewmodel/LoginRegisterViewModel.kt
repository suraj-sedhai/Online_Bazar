package com.example.onlinebazar.viewmodel

import android.app.Activity
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlinebazar.activities.ShoppingActivity
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject


@HiltViewModel
class LoginRegisterViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
) : ViewModel() {



    fun checkAndNavigate(activity: Activity) {
        firebaseAuth.currentUser?.let {
            Intent(activity, ShoppingActivity::class.java).also { intent ->
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                activity.startActivity(intent)
            }
        }
    }

}