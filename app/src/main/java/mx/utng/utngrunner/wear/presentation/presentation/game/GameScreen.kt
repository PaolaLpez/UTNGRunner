package mx.utng.utngrunner.wear.presentation.game

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.rotary.onRotaryScrollEvent
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import mx.utng.utngrunner.wear.presentation.game.GameRenderer
import mx.utng.utngrunner.wear.domain.model.GamePhase

@Composable
fun GameScreen(
    viewModel: GameViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()
    var frame by remember { mutableLongStateOf(0L) }

    // Frame loop (render control)
    LaunchedEffect(state.phase) {
        while (state.phase == GamePhase.PLAYING) {
            delay(16L)
            frame++
        }
    }

    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .focusRequester(focusRequester)
            .focusable()
            .onRotaryScrollEvent { event ->
                if (event.verticalScrollPixels < 0f) {
                    viewModel.onJump()
                } else {
                    viewModel.onSlide()
                }
                true
            }
            .clickable {
                viewModel.onJump()
            }
    ) {

        Canvas(modifier = Modifier.fillMaxSize()) {
            GameRenderer.draw(
                drawContext.canvas,
                size,
                state,
                frame
            )
        }

        when (state.phase) {
            GamePhase.IDLE -> {
                IdleOverlay(onStart = viewModel::onJump)
            }

            GamePhase.DEAD -> {
                GameOverOverlay(
                    score = state.score,
                    highScore = state.highScore,
                    onRetry = viewModel::onJump
                )
            }

            else -> Unit
        }
    }
}