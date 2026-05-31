package com.itsbaris.cs4520.a6.navigation

import kotlinx.serialization.Serializable

/**
 * 1. What: Type-safe route for the main contact list screen.
 * 2. Who: Used by ContactNavigation as the app's start destination.
 * 3. When: Active when the app launches or returns from add/detail screens.
 */
@Serializable
object ContactList

/**
 * 1. What: Type-safe route for the screen where a new contact is created.
 * 2. Who: Used by ContactNavigation when the user taps the add action.
 * 3. When: Active while the user is filling out the contact form.
 */
@Serializable
object AddContact

/**
 * 1. What: Type-safe route for one contact's details using the selected contact id.
 * 2. Who: Used by ContactNavigation when the user taps a contact card.
 * 3. When: Active while the user is viewing a saved contact.
 */
@Serializable
data class ContactDetail(
    val contactId: String,
)
