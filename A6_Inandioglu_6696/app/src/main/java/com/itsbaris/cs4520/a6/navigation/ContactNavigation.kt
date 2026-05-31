package com.itsbaris.cs4520.a6.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.itsbaris.cs4520.a6.screens.AddContactScreen
import com.itsbaris.cs4520.a6.screens.ContactDetailScreen
import com.itsbaris.cs4520.a6.screens.ContactListScreen
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
            ContactListScreen(
                contacts = contacts,
                onAddContact = { navController.navigate(AddContact) },
                onContactClick = { contactId -> navController.navigate(ContactDetail(contactId)) },
                onDeleteContact = { contactId -> contactViewModel.removeContact(contactId) },
            )
        }
        composable<AddContact> {
            AddContactScreen(
                onSaveContact = { contact ->
                    contactViewModel.addContact(contact)
                    navController.popBackStack()
                },
                onBack = { navController.popBackStack() },
            )
        }
        composable<ContactDetail> { backStackEntry ->
            val route = backStackEntry.toRoute<ContactDetail>()
            val contact = contactViewModel.getContact(route.contactId)

            ContactDetailScreen(
                contact = contact,
                onBack = { navController.popBackStack() },
            )
        }
    }
}

/**
 * 1. What: Previews the contact navigation graph at its start destination.
 * 2. Who: Used by Android Studio's preview tooling.
 * 3. When: Rendered at design time outside the running app.
 */
@Preview(showBackground = true)
@Composable
private fun ContactNavigationPreview() {
    A6_Inandioglu_6696Theme {
        ContactNavigation(contactViewModel = ContactViewModel())
    }
}
