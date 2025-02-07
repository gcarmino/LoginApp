package com.example.loginapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginapp.ui.theme.LoginAppTheme

class WelcomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginAppTheme {
                WelcomeScreen(
                    username = UserRepository.username,
                    onEmailClick = {
                        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                            data = Uri.parse("mailto:")
                        }
                        startActivity(emailIntent)
                    },
                    onMapsClick = {
                        val mapsIntent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=Ottawa"))
                        startActivity(mapsIntent)
                    }
                )
            }
        }
    }
}

@Composable
fun WelcomeScreen(username: String, onEmailClick: () -> Unit, onMapsClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome back, $username!",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        Button(
            onClick = onEmailClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            colors = ButtonDefaults.buttonColors(containerColor = androidx.compose.ui.graphics.Color(0xFFFF5722))
        ) {
            Text("Open Email", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onMapsClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            colors = ButtonDefaults.buttonColors(containerColor = androidx.compose.ui.graphics.Color(0xFFFF5722))
        ) {
            Text("Open Maps", fontSize = 18.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    LoginAppTheme {
        WelcomeScreen("User", {}, {})
    }
}
