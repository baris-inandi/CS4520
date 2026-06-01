package com.itsbaris.cs4520.a6.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itsbaris.cs4520.a6.R
import com.itsbaris.cs4520.a6.model.Contact
import com.itsbaris.cs4520.a6.ui.theme.A6_Inandioglu_6696Theme

/**
 * 1. What: Displays one contact's full details or a fallback if the contact is missing.
 * 2. Who: Called by ContactNavigation for the ContactDetail route.
 * 3. When: Shown after the user taps a contact card from the list.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactDetailScreen(
    contact: Contact?,
    onBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "Back to Contact List",
                        )
                    }
                },
                title = {
                    Text(
                        text = "Contact Detail",
                        fontWeight = FontWeight.Bold,
                    )
                },
                colors =
                    TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFFD7ECFF),
                        titleContentColor = MaterialTheme.colorScheme.primary,
                        navigationIconContentColor = MaterialTheme.colorScheme.primary,
                    ),
            )
        },
    ) { innerPadding ->
        if (contact == null) {
            MissingContactContent(modifier = Modifier.padding(innerPadding))
        } else {
            ContactDetailContent(
                contact = contact,
                modifier = Modifier.padding(innerPadding),
            )
        }
    }
}

/**
 * 1. What: Shows all saved fields for the selected contact.
 * 2. Who: Called by ContactDetailScreen.
 * 3. When: Rendered when the route's contact id matches a saved contact.
 */
@Composable
private fun ContactDetailContent(
    contact: Contact,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp),
            ) {
                Text(
                    text = contact.name,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                )
                ContactDetailRow(label = "Phone", value = "${contact.phone} (${contact.phoneType})")
                ContactDetailRow(label = "Email", value = contact.email)
                ContactDetailRow(label = "Street Address", value = contact.streetAddress)
                ContactDetailRow(label = "City, State", value = contact.cityState)
                ContactDetailRow(label = "Zip Code", value = contact.zipCode)
            }
        }
    }
}

/**
 * 1. What: Shows one labeled field on the contact detail screen.
 * 2. Who: Called by ContactDetailContent.
 * 3. When: Rendered for each saved contact value.
 */
@Composable
private fun ContactDetailRow(
    label: String,
    value: String,
) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

/**
 * 1. What: Shows a fallback message if a contact id no longer exists.
 * 2. Who: Called by ContactDetailScreen.
 * 3. When: Rendered when the detail route cannot find the requested contact.
 */
@Composable
private fun MissingContactContent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Contact not found",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

/**
 * 1. What: Previews the contact detail screen with a saved contact.
 * 2. Who: Used by Android Studio's preview tooling.
 * 3. When: Rendered at design time without running the app.
 */
@Preview(showBackground = true)
@Composable
private fun ContactDetailScreenPreview() {
    A6_Inandioglu_6696Theme {
        ContactDetailScreen(
            contact =
                Contact(
                    name = "Jane Doe",
                    phone = "6175551234",
                    phoneType = "Mobile",
                    email = "jane@example.com",
                    streetAddress = "1 College Ave",
                    cityState = "Boston, MA",
                    zipCode = "02115",
                ),
            onBack = {},
        )
    }
}

/**
 * 1. What: Previews one labeled detail row component.
 * 2. Who: Used by Android Studio's preview tooling.
 * 3. When: Rendered at design time without running the app.
 */
@Preview(showBackground = true)
@Composable
private fun ContactDetailRowPreview() {
    A6_Inandioglu_6696Theme {
        Box(modifier = Modifier.padding(16.dp)) {
            ContactDetailRow(
                label = "Email",
                value = "john.doe@northeastern.edu",
            )
        }
    }
}

/**
 * 1. What: Previews the missing contact fallback state.
 * 2. Who: Used by Android Studio's preview tooling.
 * 3. When: Rendered at design time without running the app.
 */
@Preview(showBackground = true)
@Composable
private fun MissingContactContentPreview() {
    A6_Inandioglu_6696Theme {
        ContactDetailScreen(
            contact = null,
            onBack = {},
        )
    }
}
