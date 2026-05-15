package com.itsbaris.cs4520.a3.ui.profiledashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itsbaris.cs4520.a3.R
import com.itsbaris.cs4520.a3.ui.theme.A3_Inandioglu_6696Theme

@Composable
internal fun UserInfoSection(
    username: String,
    bio: String,
    onUsernameChange: (String) -> Unit,
    onBioChange: (String) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        AvatarBox()

        OutlinedTextField(
            value = username,
            onValueChange = onUsernameChange,
            label = { Text("Username") },
            isError = username.isBlank(),
            supportingText = {
                if (username.isBlank()) {
                    Text("Username cannot be empty")
                }
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
        )

        OutlinedTextField(
            value = bio,
            onValueChange = onBioChange,
            label = { Text("Bio") },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
private fun AvatarBox() {
    Box(modifier = Modifier.size(120.dp)) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "Profile avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape),
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(24.dp)
                .clip(CircleShape)
                .background(Color.Green),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UserInfoSectionPreview() {
    A3_Inandioglu_6696Theme {
        UserInfoSection(
            username = "baris",
            bio = "Mobile dev student",
            onUsernameChange = {},
            onBioChange = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AvatarBoxPreview() {
    A3_Inandioglu_6696Theme {
        AvatarBox()
    }
}
