package mx.utng.utngrunner.wear.presentation.game

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import mx.utng.utngrunner.wear.domain.model.GameState

object GameRenderer {

    fun draw(
        canvas: Canvas,
        size: Size,
        state: GameState,
        frame: Long
    ) {
        val native = canvas.nativeCanvas

        val paintPlayer = android.graphics.Paint().apply {
            color = android.graphics.Color.CYAN
        }

        val paintObstacle = android.graphics.Paint().apply {
            color = android.graphics.Color.RED
        }

        val paintGround = android.graphics.Paint().apply {
            color = android.graphics.Color.DKGRAY
        }

        // fondo
        native.drawRect(
            0f,
            0f,
            size.width,
            size.height,
            paintGround
        )

        // player
        val player = state.player
        native.drawCircle(
            player.x,
            player.y,
            20f,
            paintPlayer
        )

        // obstáculos
        state.obstacles.forEach { obstacle ->
            native.drawRect(
                obstacle.x,
                0f,
                obstacle.x + obstacle.width,
                obstacle.height.toFloat(),
                paintObstacle
            )
        }
    }
}