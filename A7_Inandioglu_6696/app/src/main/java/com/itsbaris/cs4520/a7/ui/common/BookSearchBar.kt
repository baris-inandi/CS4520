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
        // ✍️ TODO 1 — trailing "clear" button. When `query` is NOT empty, show an
        //   IconButton that clears the field:
        //   trailingIcon = {
        //       if (query.isNotEmpty()) {
        //           IconButton(onClick = { onQueryChange("") }) {
        //               Icon(Icons.Default.Clear, contentDescription = "Clear")
        //           }
        //       }
        //   },
        // ✍️ TODO 2 — show a "Search" action key:
        //   keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        // ✍️ TODO 3 — run the search + dismiss the keyboard when that key is tapped:
        //   keyboardActions = KeyboardActions(onSearch = { keyboard?.hide(); onSearch() }),
    )
}
