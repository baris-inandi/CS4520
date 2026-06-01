package com.itsbaris.cs4520.a6.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itsbaris.cs4520.a6.R
import com.itsbaris.cs4520.a6.model.Contact
import com.itsbaris.cs4520.a6.ui.theme.A6_Inandioglu_6696Theme

/**
 * 1. What: Stores the title and message for one contact form validation error.
 * 2. Who: Used by validateContactForm and AddContactScreen.
 * 3. When: Created when a save attempt fails validation.
 */
private data class ValidationError(
    val title: String,
    val message: String,
)

/**
 * 1. What: Displays the add-contact form and saves valid contact entries.
 * 2. Who: Called by ContactNavigation for the AddContact route.
 * 3. When: Shown after the user taps the add contact floating action button.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContactScreen(
    onSaveContact: (Contact) -> Unit,
    onBack: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    var name by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var phoneType by rememberSaveable { mutableStateOf("Mobile") }
    var email by rememberSaveable { mutableStateOf("") }
    var streetAddress by rememberSaveable { mutableStateOf("") }
    var cityState by rememberSaveable { mutableStateOf("") }
    var zipCode by rememberSaveable { mutableStateOf("") }
    var validationTitle by rememberSaveable { mutableStateOf<String?>(null) }
    var validationMessage by rememberSaveable { mutableStateOf<String?>(null) }

    BackHandler {
        focusManager.clearFocus()
        onBack()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {
                            focusManager.clearFocus()
                            onBack()
                        },
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "Back to Contact List",
                        )
                    }
                },
                title = {
                    Text(
                        text = "Add Contact",
                        fontWeight = FontWeight.Bold,
                    )
                },
                actions = {
                    TextButton(
                        onClick = {
                            focusManager.clearFocus()
                            val validationError =
                                validateContactForm(
                                    name = name,
                                    phone = phone,
                                    email = email,
                                    streetAddress = streetAddress,
                                    cityState = cityState,
                                    zipCode = zipCode,
                                )

                            if (validationError == null) {
                                onSaveContact(
                                    Contact(
                                        name = name.trim(),
                                        phone = phone.trim(),
                                        phoneType = phoneType,
                                        email = email.trim(),
                                        streetAddress = streetAddress.trim(),
                                        cityState = cityState.trim(),
                                        zipCode = zipCode.trim(),
                                    ),
                                )
                            } else {
                                validationTitle = validationError.title
                                validationMessage = validationError.message
                            }
                        },
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_save),
                            contentDescription = null,
                        )
                        Text(text = "Save")
                    }
                },
                colors =
                    TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFFD7ECFF),
                        titleContentColor = MaterialTheme.colorScheme.primary,
                        navigationIconContentColor = MaterialTheme.colorScheme.primary,
                        actionIconContentColor = MaterialTheme.colorScheme.primary,
                    ),
            )
        },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp),
        ) {
            Text(
                text = "Enter Contact Details",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
            )
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Full Name") },
                placeholder = { Text("John Doe") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
            )
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Phone Number") },
                    placeholder = { Text("6175551234") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f),
                )
                PhoneTypeDropdown(
                    selectedType = phoneType,
                    onTypeSelected = { selectedType -> phoneType = selectedType },
                    modifier = Modifier.width(140.dp),
                )
            }
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email Address") },
                placeholder = { Text("student@northeastern.edu") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth(),
            )
            OutlinedTextField(
                value = streetAddress,
                onValueChange = { streetAddress = it },
                label = { Text("Street Address") },
                placeholder = { Text("360 Huntington Ave.") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
            )
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = cityState,
                    onValueChange = { cityState = it },
                    label = { Text("City, State") },
                    placeholder = { Text("Boston, MA") },
                    singleLine = true,
                    modifier = Modifier.weight(1f),
                )
                OutlinedTextField(
                    value = zipCode,
                    onValueChange = { zipCode = it },
                    label = { Text("Zip Code") },
                    placeholder = { Text("02120") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(0.7f),
                )
            }
        }
    }

    if (validationTitle != null && validationMessage != null) {
        ContactValidationDialog(
            title = validationTitle.orEmpty(),
            message = validationMessage.orEmpty(),
            onDismiss = {
                validationTitle = null
                validationMessage = null
            },
        )
    }
}

/**
 * 1. What: Displays the phone type selector using Material 3 exposed dropdown controls.
 * 2. Who: Called by AddContactScreen and preview tooling.
 * 3. When: Rendered while the user chooses Mobile, Home, or Work.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneTypeDropdown(
    selectedType: String,
    onTypeSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    val options = listOf("Mobile", "Home", "Work")

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
        modifier = modifier,
    ) {
        OutlinedTextField(
            value = selectedType,
            onValueChange = {},
            readOnly = true,
            label = { Text("Type") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            singleLine = true,
            modifier =
                Modifier
                    .menuAnchor(type = ExposedDropdownMenuAnchorType.PrimaryNotEditable, enabled = true)
                    .fillMaxWidth(),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onTypeSelected(option)
                        expanded = false
                    },
                )
            }
        }
    }
}

/**
 * 1. What: Shows validation feedback when a contact form value is invalid.
 * 2. Who: Called by AddContactScreen after validation fails.
 * 3. When: Displayed immediately after the user taps Save with invalid data.
 */
@Composable
private fun ContactValidationDialog(
    title: String,
    message: String,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = title) },
        text = { Text(text = message) },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "OK")
            }
        },
    )
}

/**
 * 1. What: Validates all form fields before a contact is saved.
 * 2. Who: Called by AddContactScreen when the Save action is tapped.
 * 3. When: Runs before ContactViewModel receives a new Contact.
 */
private fun validateContactForm(
    name: String,
    phone: String,
    email: String,
    streetAddress: String,
    cityState: String,
    zipCode: String,
): ValidationError? {
    val emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
    val trimmedPhone = phone.trim()
    val trimmedZip = zipCode.trim()

    return when {
        name.isBlank() -> {
            ValidationError(
                title = "Missing Name",
                message = "Please enter the contact's full name.",
            )
        }

        trimmedPhone.isBlank() || !trimmedPhone.all { character -> character.isDigit() } -> {
            ValidationError(
                title = "Invalid Phone",
                message = "Please enter a phone number using numeric digits only.",
            )
        }

        !emailPattern.matches(email.trim()) -> {
            ValidationError(
                title = "Invalid Email",
                message = "Please enter a valid email address (e.g. name@example.com).",
            )
        }

        streetAddress.isBlank() -> {
            ValidationError(
                title = "Missing Street Address",
                message = "Please enter the contact's street address.",
            )
        }

        cityState.isBlank() -> {
            ValidationError(
                title = "Missing City/State",
                message = "Please enter the contact's city and state.",
            )
        }

        trimmedZip.length != 5 || !trimmedZip.all { character -> character.isDigit() } -> {
            ValidationError(
                title = "Invalid Zip Code",
                message = "Please enter a valid 5-digit zip code.",
            )
        }

        else -> {
            null
        }
    }
}

/**
 * 1. What: Previews the blank add contact form.
 * 2. Who: Used by Android Studio's preview tooling.
 * 3. When: Rendered at design time without running the app.
 */
@Preview(showBackground = true)
@Composable
private fun AddContactScreenPreview() {
    A6_Inandioglu_6696Theme {
        AddContactScreen(
            onSaveContact = {},
            onBack = {},
        )
    }
}

/**
 * 1. What: Previews the phone type dropdown component.
 * 2. Who: Used by Android Studio's preview tooling.
 * 3. When: Rendered at design time without running the app.
 */
@Preview(showBackground = true)
@Composable
private fun PhoneTypeDropdownPreview() {
    A6_Inandioglu_6696Theme {
        PhoneTypeDropdown(
            selectedType = "Mobile",
            onTypeSelected = {},
            modifier =
                Modifier
                    .padding(16.dp)
                    .width(140.dp),
        )
    }
}

/**
 * 1. What: Previews the validation alert dialog.
 * 2. Who: Used by Android Studio's preview tooling.
 * 3. When: Rendered at design time without running the app.
 */
@Preview(showBackground = true)
@Composable
private fun ContactValidationDialogPreview() {
    A6_Inandioglu_6696Theme {
        ContactValidationDialog(
            title = "Invalid Email",
            message = "Please enter a valid email address (e.g. name@example.com).",
            onDismiss = {},
        )
    }
}
