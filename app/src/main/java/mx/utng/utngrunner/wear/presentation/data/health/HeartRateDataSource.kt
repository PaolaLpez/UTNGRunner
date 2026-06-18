package mx.utng.utngrunner.wear.data.health


import androidx.health.services.client.HealthServicesClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HeartRateDataSource(
    private val healthServicesClient: HealthServicesClient
) {

    private val _heartRate = MutableStateFlow(72)

    val heartRate: StateFlow<Int> = _heartRate.asStateFlow()

    suspend fun startMonitoring() {

        // Simulación inicial
        // Posteriormente aquí se conecta el sensor real
        _heartRate.value = 72
    }

    fun updateHeartRate(value: Int) {
        _heartRate.value = value
    }
}