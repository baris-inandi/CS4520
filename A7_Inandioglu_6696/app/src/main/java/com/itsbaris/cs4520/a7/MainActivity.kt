package com.itsbaris.cs4520.a7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.itsbaris.cs4520.a7.ui.navigation.AppNavigation
import com.itsbaris.cs4520.a7.ui.theme.A7_Inandioglu_6696Theme

class MainActivity : ComponentActivity() {
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
