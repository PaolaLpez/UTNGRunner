package mx.utng.utngrunner.wear.data.repository

import mx.utng.utngrunner.wear.data.datasource.PreferencesDataSource
import mx.utng.utngrunner.wear.domain.repository.ScoreRepository

/**
 * Implementación concreta.
 * La capa Data implementa el contrato definido en Domain.
 */
class ScoreRepositoryImpl(
    private val dataSource: PreferencesDataSource
) : ScoreRepository {

    override suspend fun getHighScore(): Int =
        dataSource.getHighScore()

    override suspend fun saveHighScore(score: Int) =
        dataSource.saveHighScore(score)
}