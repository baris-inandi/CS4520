package com.itsbaris.cs4520.a6.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itsbaris.cs4520.a6.R
import com.itsbaris.cs4520.a6.model.Contact
import com.itsbaris.cs4520.a6.ui.theme.A6_Inandioglu_6696Theme

/**
 * 1. What: Displays the main contact list with empty, add, tap, and swipe-delete behavior.
 * 2. Who: Called by ContactNavigation for the ContactList route.
 * 3. When: Shown on app launch and whenever the user returns from add/detail screens.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactListScreen(
    contacts: List<Contact>,
    onAddContact: () -> Unit,
    onContactClick: (String) -> Unit,
    onDeleteContact: (String) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Contact List",
                        fontWeight = FontWeight.Bold,
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFD7ECFF),
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddContact) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = "Add Contact",
                )
            }
        },
    ) { innerPadding ->
        if (contacts.isEmpty()) {
            EmptyContactList(modifier = Modifier.padding(innerPadding))
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentPadding = PaddingValues(top = 12.dp, bottom = 96.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(
                    items = contacts,
                    key = { contact -> contact.id },
                ) { contact ->
                    SwipeableContactRow(
                        contact = contact,
                        onClick = { onContactClick(contact.id) },
                        onDelete = { onDeleteContact(contact.id) },
                        modifier = Modifier.animateItem(),
                    )
                }
            }
        }
    }
}

/**
 * 1. What: Shows the centered empty-state text when no contacts have been saved.
 * 2. Who: Called by ContactListScreen.
 * 3. When: Rendered whenever the contacts list is empty.
 */
@Composable
private fun EmptyContactList(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "No contacts saved yet.",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = "Tap + to add one!",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}

/**
 * 1. What: Wraps a contact card in a right-to-left swipe dismissal container.
 * 2. Who: Called by ContactListScreen for each contact in the LazyColumn.
 * 3. When: Rendered for every saved contact row.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeableContactRow(
    contact: Contact,
    onClick: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val dismissState = rememberSwipeToDismissBoxState()

    LaunchedEffect(dismissState.currentValue) {
        if (dismissState.currentValue == SwipeToDismissBoxValue.EndToStart) {
            onDelete()
        }
    }

    SwipeToDismissBox(
        state = dismissState,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        enableDismissFromStartToEnd = false,
        enableDismissFromEndToStart = true,
        backgroundContent = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.errorContainer)
                    .padding(end = 24.dp),
                contentAlignment = Alignment.CenterEnd,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_delete),
                    contentDescription = "Delete Contact",
                    tint = MaterialTheme.colorScheme.onErrorContainer,
                )
            }
        },
    ) {
        ContactCard(
            contact = contact,
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

/**
 * 1. What: Shows one saved contact's summary information in a bordered card.
 * 2. Who: Called by SwipeableContactRow and previews.
 * 3. When: Rendered as the foreground content for each contact row.
 */
@Composable
fun ContactCard(
    contact: Contact,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = contact.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "${contact.phone} (${contact.phoneType})",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                text = contact.email,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

/**
 * 1. What: Previews the empty contact list screen.
 * 2. Who: Used by Android Studio's preview tooling.
 * 3. When: Rendered at design time without running the app.
 */
@Preview(showBackground = true)
@Composable
private fun ContactListScreenEmptyPreview() {
    A6_Inandioglu_6696Theme {
        ContactListScreen(
            contacts = emptyList(),
            onAddContact = {},
            onContactClick = {},
            onDeleteContact = {},
        )
    }
}

/**
 * 1. What: Previews the contact list screen with saved contacts.
 * 2. Who: Used by Android Studio's preview tooling.
 * 3. When: Rendered at design time without running the app.
 */
@Preview(showBackground = true)
@Composable
private fun ContactListScreenPopulatedPreview() {
    A6_Inandioglu_6696Theme {
        ContactListScreen(
            contacts = listOf(
                Contact(
                    name = "asdf",
                    phone = "9805555555",
                    phoneType = "Home",
                    email = "asf@gh.fg",
                    streetAddress = "123 Main St",
                    cityState = "Boston, MA",
                    zipCode = "02115",
                ),
            ),
            onAddContact = {},
            onContactClick = {},
            onDeleteContact = {},
        )
    }
}

/**
 * 1. What: Previews a single contact card component.
 * 2. Who: Used by Android Studio's preview tooling.
 * 3. When: Rendered at design time without running the app.
 */
@Preview(showBackground = true)
@Composable
private fun ContactCardPreview() {
    A6_Inandioglu_6696Theme {
        Box(modifier = Modifier.padding(16.dp)) {
            ContactCard(
                contact = Contact(
                    name = "Jane Doe",
                    phone = "6175551234",
                    phoneType = "Mobile",
                    email = "jane@example.com",
                    streetAddress = "1 College Ave",
                    cityState = "Boston, MA",
                    zipCode = "02115",
                ),
                onClick = {},
            )
        }
    }
}
