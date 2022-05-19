package uabc.ic.benjaminbolanos.rubiksrace.highscores_view

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import uabc.ic.benjaminbolanos.rubiksrace.highscore_database.Highscore
import uabc.ic.benjaminbolanos.rubiksrace.highscore_database.HighscoreRepository

/**
 * Clase HighscoreViewModel que hereda de ViewModel. Esta clase está hecha para sobrevivir a los
 * cambios de configuracion y proporcionar datos a la UI. Con esta clase aseguramos un buen
 * acceso a la base de datos desde el hilo principal.
 */
class HighscoreViewModel(private val repository: HighscoreRepository): ViewModel() {
    /**
     * Tener las listas como LiveData permite que se guarden en caché
     */
    val allHighscores: LiveData<List<Highscore>> = repository.allHighscores.asLiveData()
    val orderedHighscores: LiveData<List<Highscore>> = repository.orderedHighscores.asLiveData()

    fun insert(highscore: Highscore) = viewModelScope.launch{
        repository.insert(highscore)
    }

    fun deleteAll() = viewModelScope.launch{
        repository.deleteAll()
    }
}

/**
 * Clase para crear un HighscoreViewModel a partir del ViewModelProvider.Factory
 */
class HighscoreViewModelFactory(private val repository: HighscoreRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HighscoreViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HighscoreViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}