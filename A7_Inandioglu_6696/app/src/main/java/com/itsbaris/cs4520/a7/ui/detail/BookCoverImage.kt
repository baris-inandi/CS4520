package com.itsbaris.cs4520.a7.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.itsbaris.cs4520.a7.ui.theme.A7_Inandioglu_6696Theme

/**
 * 1. What: Shows a book cover image with loading and error states.
 * 2. Who:  Called by BookDetailContent.
 * 3. When: Rendered when a loaded book detail includes a cover id.
 */
@Composable
fun BookCoverImage(
    title: String,
    coverId: Int,
    modifier: Modifier = Modifier,
) {
    SubcomposeAsyncImage(
        model = "https://covers.openlibrary.org/b/id/$coverId-M.jpg",
        contentDescription = "Cover of $title",
        modifier = modifier,
        loading = {
            Box(
                modifier = Modifier.width(120.dp).height(180.dp).background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator()
            }
        },
        error = {
            Text("Cover unavailable", style = MaterialTheme.typography.bodySmall)
        },
        success = {
            SubcomposeAsyncImageContent(
                modifier = Modifier.height(180.dp),
                contentScale = ContentScale.Fit,
            )
        },
    )
}

/**
 * 1. What: Previews the cover image component.
 * 2. Who:  Used by Android Studio's preview panel.
 * 3. When: Checked while reviewing the detail cover layout.
 */
@Preview(showBackground = true)
@Composable
private fun BookCoverImagePreview() {
    A7_Inandioglu_6696Theme {
        BookCoverImage(
            title = "Harry Potter and the Philosopher's Stone",
            coverId = 10521270,
        )
    }
}
