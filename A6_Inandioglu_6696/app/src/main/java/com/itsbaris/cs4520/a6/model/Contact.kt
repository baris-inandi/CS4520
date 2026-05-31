package com.itsbaris.cs4520.a6.model

import kotlinx.serialization.Serializable

/**
 * 1. What: Represents one saved contact with personal, phone, and address information.
 * 2. Who: Used by ContactViewModel and the contact screens.
 * 3. When: Created when the user saves a valid contact from the add contact screen.
 */
@Serializable
data class Contact(
    val id: String =
        java.util.UUID
            .randomUUID()
            .toString(),
    val name: String,
    val email: String,
    val phone: String,
    val phoneType: String,
    val streetAddress: String,
    val cityState: String,
    val zipCode: String,
)
