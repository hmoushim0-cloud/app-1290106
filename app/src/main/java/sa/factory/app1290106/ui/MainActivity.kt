package sa.factory.app1290106.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import sa.factory.app1290106.data.InMemoryMathReferenceQuestionRepository
import sa.factory.app1290106.model.MathSolutionStep

val BespokeColorScheme = darkColorScheme(
    primary = Color(0xFF1E3A8A),
    secondary = Color(0xFF0284C7),
    tertiary = Color(0xFF0284C7),
    background = Color(0xFF0F172A),
    surface = Color(0xFF1E293B),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color(0xFFF8FAFC),
    onSurface = Color(0xFFF8FAFC)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = InMemoryMathReferenceQuestionRepository()
        val viewModel = MathReferenceQuestionViewModel(repository)

        // Mathematical Lock 2: Support start_screen and screen intent extras
        val startScreen = intent?.getStringExtra("start_screen")
            ?: intent?.getStringExtra("screen")
            ?: "home"

        setContent {
            MaterialTheme(colorScheme = BespokeColorScheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var currentScreen by remember { mutableStateOf(startScreen) }
                    var selectedItem by remember { mutableStateOf<MathSolutionStep?>(null) }

                    when (currentScreen) {
                        "details" -> {
                            MathReferenceQuestionDetailsScreen(
                                item = selectedItem,
                                onBack = { currentScreen = "home" },
                                onProceedCheckout = { currentScreen = "checkout" }
                            )
                        }
                        "checkout", "tracking" -> {
                            MathReferenceQuestionCheckoutScreen(
                                onBack = { currentScreen = "details" },
                                onComplete = { currentScreen = "home" }
                            )
                        }
                        else -> {
                            MathReferenceQuestionHomeScreen(
                                viewModel = viewModel,
                                onSelectItem = { item ->
                                    selectedItem = item
                                    currentScreen = "details"
                                },
                                onQuickCheckout = {
                                    currentScreen = "checkout"
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
