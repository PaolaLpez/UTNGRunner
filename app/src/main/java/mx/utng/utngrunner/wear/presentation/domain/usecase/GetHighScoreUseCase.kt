package mx.utng.utngrunner.wear.domain.usecase

import mx.utng.utngrunner.wear.domain.repository.ScoreRepository

/**
 * Caso de uso: obtiene el mejor puntaje almacenado.
 */
class GetHighScoreUseCase(
    private val repository: ScoreRepository
) {
    suspend operator fun invoke(): Int {
        return repository.getHighScore()
    }
}