package mx.utng.utngrunner.wear.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import mx.utng.utngrunner.wear.presentation.game.GameScreen
import mx.utng.utngrunner.wear.presentation.game.GameViewModel
import mx.utng.utngrunner.wear.presentation.game.GameViewModelFactory
import mx.utng.utngrunner.wear.presentation.theme.UTNGRunnerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UTNGRunnerTheme {

                val viewModel: GameViewModel = viewModel(
                    factory = GameViewModelFactory(applicationContext)
                )

                GameScreen(viewModel = viewModel)
            }
        }
    }
}