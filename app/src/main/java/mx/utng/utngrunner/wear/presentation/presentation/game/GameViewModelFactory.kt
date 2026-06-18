package mx.utng.utngrunner.wear.presentation.game

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.health.services.client.HealthServices
import mx.utng.utngrunner.wear.data.health.HeartRateDataSource
import mx.utng.utngrunner.wear.data.datasource.PreferencesDataSource
import mx.utng.utngrunner.wear.data.repository.ScoreRepositoryImpl
import mx.utng.utngrunner.wear.domain.usecase.GetHighScoreUseCase
import mx.utng.utngrunner.wear.domain.usecase.SaveHighScoreUseCase

class GameViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        // Construir el grafo de dependencias de forma explícita

        val healthClient = HealthServices.getClient(context)
        val heartRateDs = HeartRateDataSource(healthClient)

        val prefsDs = PreferencesDataSource(context)

        val repository = ScoreRepositoryImpl(prefsDs)

        val getHighScore = GetHighScoreUseCase(repository)
        val saveHighScore = SaveHighScoreUseCase(repository)

        return GameViewModel(
            getHighScore = getHighScore,
            saveHighScore = saveHighScore,
            heartRateSource = heartRateDs
        ) as T
    }
}