package mx.utng.utngrunner.wear.domain.usecase

import mx.utng.utngrunner.wear.domain.repository.ScoreRepository

class SaveHighScoreUseCase(
    private val repository: ScoreRepository
) {
    suspend operator fun invoke(score: Int) {
        val current = repository.getHighScore()
        if (score > current) {
            repository.saveHighScore(score)
        }
    }
}