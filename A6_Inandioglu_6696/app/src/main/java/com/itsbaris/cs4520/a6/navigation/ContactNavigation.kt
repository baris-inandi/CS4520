package com.itsbaris.cs4520.a6.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.itsbaris.cs4520.a6.ui.theme.A6_Inandioglu_6696Theme
import com.itsbaris.cs4520.a6.viewmodel.ContactViewModel

/**
 * 1. What: Creates the app's type-safe navigation graph and observes contact state.
 * 2. Who: Called by MainActivity after the theme is applied.
 * 3. When: Runs whenever the activity content is composed.
 */
@Composable
fun ContactNavigation(contactViewModel: ContactViewModel) {
    val navController = rememberNavController()
    val contacts by contactViewModel.contacts.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = ContactList,
    ) {
        composable<ContactList> {
            Surface(modifier = Modifier.fillMaxSize()) {
                StageText(text = "Contact List (${contacts.size})")
            }
        }
        composable<AddContact> {
            Surface(modifier = Modifier.fillMaxSize()) {
                StageText(text = "Add Contact")
            }
        }
        composable<ContactDetail> { backStackEntry ->
            val route = backStackEntry.toRoute<ContactDetail>()
            val contact = contactViewModel.getContact(route.contactId)

            Surface(modifier = Modifier.fillMaxSize()) {
                StageText(text = contact?.name ?: "Contact not found")
            }
        }
    }
}

/**
 * 1. What: Shows temporary centered text while the real A6 screens are built.
 * 2. Who: Called by ContactNavigation for the initial app shell.
 * 3. When: Used during the early setup stage before final screen layouts are added.
 */
@Composable
private fun StageText(text: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineMedium,
        )
    }
}

/**
 * 1. What: Previews the early navigation shell text component.
 * 2. Who: Used by Android Studio's preview tooling.
 * 3. When: Rendered at design time outside the running app.
 */
@Preview(showBackground = true)
@Composable
private fun StageTextPreview() {
    A6_Inandioglu_6696Theme {
        StageText(text = "Contact List (0)")
    }
}
