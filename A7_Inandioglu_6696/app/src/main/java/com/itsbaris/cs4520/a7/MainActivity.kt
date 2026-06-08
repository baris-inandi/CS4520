package com.itsbaris.cs4520.a7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.itsbaris.cs4520.a7.ui.navigation.AppNavigation
import com.itsbaris.cs4520.a7.ui.theme.A7_Inandioglu_6696Theme

class MainActivity : ComponentActivity() {
    /**
     * 1. What: Starts the Compose UI for the Book Finder app.
     * 2. Who:  Called by Android when the activity is created.
     * 3. When: Runs when the app launches.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            A7_Inandioglu_6696Theme {
                AppNavigation()
            }
        }
    }
}
