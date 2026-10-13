package com.example.myapplication // ⚠️ Снова проверь, чтобы здесь было имя именно ТВОЕГО пакета!

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun AppListScreen() {


    val myApps = listOf(
        AppInfo("RuStore", "Официальный российский магазин приложений", "Инструменты"),
        AppInfo("VK Видео", "Смотри фильмы, сериалы и шоу бесплатно", "Развлечения"),
        AppInfo("Сбербанк Онлайн", "Управляйте финансами безопасно", "Финансы"),
        AppInfo("Яндекс Навигатор", "Быстрые маршруты и подсказки на дорогах", "Навигация"),
        AppInfo("Telegram", "Безопасный и быстрый мессенджер", "Общение")
    )


    Column(modifier = Modifier.fillMaxSize()) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp) //
                .background(color = Color(0xFF0077FF))
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(color = Color.White, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "VK",
                    color = Color(0xFF0077FF),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Список приложений",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {

            items(myApps) { app ->
                AppItem(app = app)
            }
        }
    }
}
