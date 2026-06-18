package mx.utng.utngrunner.wear

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import mx.utng.utngrunner.wear.presentation.game.GameViewModelFactory
import mx.utng.utngrunner.wear.presentation.game.GameScreen
import mx.utng.utngrunner.wear.presentation.theme.WearAppTheme
import mx.utng.utngrunner.wear.presentation.game.GameViewModel

class GameActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WearAppTheme {

                val viewModel: GameViewModel = viewModel(
                    factory = GameViewModelFactory(applicationContext)
                )

                GameScreen(viewModel = viewModel)
            }
        }
    }
}