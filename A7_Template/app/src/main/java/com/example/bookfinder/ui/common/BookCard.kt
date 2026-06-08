package com.example.bookfinder.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.bookfinder.model.Book

@Composable
fun BookCard(
    book: Book,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 6.dp),
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(book.title, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
            Text(book.author, style = MaterialTheme.typography.bodyMedium)
            if (book.year != 0) Text("First published: ${book.year}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
