package com.itsbaris.cs4520.a7.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.itsbaris.cs4520.a7.ui.theme.A7_Inandioglu_6696Theme

/**
 * 1. What: Shows the book search input with search and clear actions.
 * 2. Who:  Called by SearchScreen.
 * 3. When: Rendered at the top of the search screen.
 */
@Composable
fun BookSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val keyboard = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        label = { Text("Search books") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = { onQueryChange("") }) {
                    Icon(Icons.Default.Clear, contentDescription = "Clear")
                }
            }
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions =
            KeyboardActions(
                onSearch = {
                    keyboard?.hide()
                    onSearch()
                },
            ),
    )
}

/**
 * 1. What: Previews the search bar before any text is typed.
 * 2. Who:  Used by Android Studio's preview panel.
 * 3. When: Checked while reviewing the empty search input state.
 */
@Preview(showBackground = true)
@Composable
private fun BookSearchBarEmptyPreview() {
    A7_Inandioglu_6696Theme {
        BookSearchBar(
            query = "",
            onQueryChange = {},
            onSearch = {},
        )
    }
}

/**
 * 1. What: Previews the search bar with typed text and a clear button.
 * 2. Who:  Used by Android Studio's preview panel.
 * 3. When: Checked while reviewing the filled search input state.
 */
@Preview(showBackground = true)
@Composable
private fun BookSearchBarFilledPreview() {
    A7_Inandioglu_6696Theme {
        BookSearchBar(
            query = "harry potter",
            onQueryChange = {},
            onSearch = {},
        )
    }
}
