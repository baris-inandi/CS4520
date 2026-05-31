package com.itsbaris.cs4520.a6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.itsbaris.cs4520.a6.navigation.ContactNavigation
import com.itsbaris.cs4520.a6.ui.theme.A6_Inandioglu_6696Theme
import com.itsbaris.cs4520.a6.viewmodel.ContactViewModel

class MainActivity : ComponentActivity() {
    private val contactViewModel: ContactViewModel by viewModels()

    /**
     * 1. What: Initializes the activity and displays the contact app content.
     * 2. Who: Called by the Android framework when MainActivity starts.
     * 3. When: Runs once for this activity instance during app launch.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            A6_Inandioglu_6696Theme {
                ContactNavigation(contactViewModel = contactViewModel)
            }
        }
    }
}
