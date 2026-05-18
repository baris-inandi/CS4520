package com.itsbaris.cs4520.a4.ui.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itsbaris.cs4520.a4.R
import com.itsbaris.cs4520.a4.model.Profile
import com.itsbaris.cs4520.a4.ui.theme.A4_Inandioglu_6696Theme

@Composable
fun Avatar(
    profile: Profile,
    size: Int,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.size(size.dp)) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "Profile avatar",
            contentScale = ContentScale.Crop,
            modifier =
                Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
        )

        if (profile.showOnline) {
            Box(
                modifier =
                    Modifier
                        .align(Alignment.BottomEnd)
                        .size((size / 4).coerceAtLeast(14).dp)
                        .border(2.dp, MaterialTheme.colorScheme.surface, CircleShape)
                        .clip(CircleShape)
                        .background(colorResource(R.color.teal_200)),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AvatarPreview() {
    A4_Inandioglu_6696Theme {
        Avatar(
            profile = Profile(name = "Baris", showOnline = true),
            size = 96,
        )
    }
}
