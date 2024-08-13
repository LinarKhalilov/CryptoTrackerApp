package android.dev.cryptotrackerapp

import android.dev.cryptotrackerapp.navigation.NavigationRoot
import android.dev.cryptotrackerapp.ui.theme.ApplicationTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApplicationTheme {
                NavigationRoot()
            }
        }
    }
}