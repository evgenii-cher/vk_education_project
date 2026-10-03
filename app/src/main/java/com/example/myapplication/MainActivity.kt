package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme
import java.net.URI
import androidx.core.net.toUri

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        context = this,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(context: Context, modifier: Modifier = Modifier) {
    var textState by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = textState,
            onValueChange = { textState = it },
            label = { Text("Введите текст") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (textState.trim().isEmpty()) {
                Toast.makeText(context, "Поле ввода пустое!", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(context, SecondActivity::class.java)
                intent.putExtra("USER_INPUT_KEY", textState)
                context.startActivity(intent)
            }

        }) {
            Text("Открыть вторую Activity")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val trimmedText = textState.trim()


            if (trimmedText.isEmpty()) {
                Toast.makeText(context, "Введите номер телефона в поле выше!", Toast.LENGTH_SHORT)
                    .show()
            } else if (!android.util.Patterns.PHONE.matcher(trimmedText).matches()) {
                Toast.makeText(
                    context,
                    "Неверный формат номера! Буквы запрещены, разрешены цифры и '+'",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val dialIntent = Intent(
                    Intent.ACTION_DIAL,
                    "tel:${textState.trim()}".toUri()
                )

                context.startActivity(dialIntent)
            }
        }) {
            Text("Позвонить другу")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val textToShare = textState.trim()

            if (textToShare.isEmpty()) {
                Toast.makeText(
                    context,
                    "Введите текст, которым хотите поделиться!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val sendIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"

                    putExtra(Intent.EXTRA_TEXT, textToShare)
                }
                val chooserIntent = Intent.createChooser(sendIntent, "Поделиться через")
                context.startActivity(chooserIntent)
            }
        }) {
            Text("Поделиться текстом")
        }

    }
}