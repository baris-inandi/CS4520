package com.itsbaris.cs4520.a3

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.itsbaris.cs4520.a3.ui.theme.A3_Inandioglu_6696Theme
import com.itsbaris.cs4520.a3.ui.profiledashboard.ProfileDashboard

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            A3_Inandioglu_6696Theme {
                ProfileDashboard()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.i("Lifecycle", "App returned to foreground!")
    }

    override fun onStop() {
        super.onStop()
        Log.w("Lifecycle", "App sent to background!")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.w("Lifecycle", "App destroyed!")
    }
}
