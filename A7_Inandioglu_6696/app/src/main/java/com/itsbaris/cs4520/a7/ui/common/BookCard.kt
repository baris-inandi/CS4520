package com.itsbaris.cs4520.a7.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itsbaris.cs4520.a7.model.Book
import com.itsbaris.cs4520.a7.ui.theme.A7_Inandioglu_6696Theme

/**
 * 1. What: Shows one book result in a clickable card.
 * 2. Who:  Called by the search results list.
 * 3. When: Rendered for each book returned by a search.
 */
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

/**
 * 1. What: Previews one populated book result card.
 * 2. Who:  Used by Android Studio's preview panel.
 * 3. When: Checked while reviewing the reusable result component.
 */
@Preview(showBackground = true)
@Composable
private fun BookCardPreview() {
    A7_Inandioglu_6696Theme {
        BookCard(
            book =
                Book(
                    key = "/works/OL82563W",
                    title = "Harry Potter and the Philosopher's Stone",
                    author = "J.K. Rowling",
                    year = 1997,
                    subjects = listOf("Fantasy", "Magic"),
                ),
            onClick = {},
        )
    }
}
