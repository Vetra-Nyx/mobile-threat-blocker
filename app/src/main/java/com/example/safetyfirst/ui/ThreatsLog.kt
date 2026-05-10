package com.example.safetyfirst.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable

fun ThreatLogScreen(
    navbar: @Composable () -> Unit,

){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.padding(30.dp))
        Text(text = "Hello")


                    Text(text = "Domain: ${event.domain}")

                    Text(text = "Verdict: ${event.verdict}")

                    Text(text = "Type: ${event.type}")

                    Spacer(modifier = Modifier.padding(4.dp))
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .padding(8.dp),

            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Button(onClick = DashsClick) {
                Text("Dashboard")
            }

            Button(onClick = ThreatsClick) {
                Text("Threats")
            }

            Button(onClick = SettingsClick) {
                Text("Settings")
            }
        }
        Spacer(modifier = Modifier.weight(1f))

        navbar()
    }
}


//
//}
