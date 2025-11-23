package com.nickolss.madeforyou

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nickolss.madeforyou.data.AuthRepository
import com.nickolss.madeforyou.screens.HomeScreen
import com.nickolss.madeforyou.screens.LoginScreen
import com.nickolss.madeforyou.screens.RegisterScreen
import com.nickolss.madeforyou.ui.theme.MadeForYouTheme

class MainActivity : ComponentActivity() {

    private val authRepository = AuthRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            MadeForYouTheme {
                var currentScreen by remember {
                    mutableStateOf(if (authRepository.currentUser != null) "home" else "login")
                }

                when (currentScreen) {
                    "login" -> LoginScreen(
                        authRepository = authRepository,
                        onLoginSuccess = {
                            currentScreen = "home"
                        },
                        onNavigateToRegister = {
                            currentScreen = "register"
                        }
                    )
                    "register" -> RegisterScreen(
                        authRepository = authRepository,
                        onRegisterSuccess = {
                            currentScreen = "home"
                        },
                        onNavigateToLogin = {
                            currentScreen = "login"
                        }
                    )
                    "home" -> HomeScreen(
                        authRepository = authRepository
                    )
                }
            }
        }
    }
}