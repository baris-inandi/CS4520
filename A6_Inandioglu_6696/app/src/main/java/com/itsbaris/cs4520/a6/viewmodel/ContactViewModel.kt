package com.itsbaris.cs4520.a6.viewmodel

import androidx.lifecycle.ViewModel
import com.itsbaris.cs4520.a6.model.Contact
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * 1. What: Holds the contact list state and exposes immutable contact actions to the UI.
 * 2. Who: Created by MainActivity and used by the navigation/screens layer.
 * 3. When: Alive while the activity is alive so contacts survive recomposition.
 */
class ContactViewModel : ViewModel() {
    private val _contacts = MutableStateFlow<List<Contact>>(emptyList())
    val contacts: StateFlow<List<Contact>> = _contacts.asStateFlow()

    /**
     * 1. What: Adds a new contact by creating a new list instance.
     * 2. Who: Called by the add contact screen after validation succeeds.
     * 3. When: Runs when the user taps Save with valid contact details.
     */
    fun addContact(contact: Contact) {
        _contacts.update { currentContacts ->
            currentContacts + contact
        }
    }

    /**
     * 1. What: Removes the contact whose id matches the provided contact id.
     * 2. Who: Called by the contact list screen after a swipe delete gesture.
     * 3. When: Runs when a contact row settles in the dismissed state.
     */
    fun removeContact(contactId: String) {
        _contacts.update { currentContacts ->
            currentContacts.filter { contact -> contact.id != contactId }
        }
    }

    /**
     * 1. What: Finds a single contact by id from the current contact list.
     * 2. Who: Called by the detail screen route.
     * 3. When: Runs when the user opens a saved contact's detail screen.
     */
    fun getContact(contactId: String): Contact? =
        contacts.value.firstOrNull { contact ->
            contact.id == contactId
        }
}
