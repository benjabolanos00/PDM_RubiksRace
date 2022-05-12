package uabc.ic.benjaminbolanos.rubiksrace.highscore_database

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class HighscoreViewModel(private val repository: HighscoreRepository): ViewModel() {
    val allHighscores: LiveData<List<Highscore>> = repository.allHighscores.asLiveData()
    val orderedHighscores: LiveData<List<Highscore>> = repository.orderedHighscores.asLiveData()

    fun insert(highscore: Highscore) = viewModelScope.launch{
        repository.insert(highscore)
    }

    fun deleteAll() = viewModelScope.launch{
        repository.deleteAll()
    }

    fun getMax(){
        val hsString = Transformations.map(orderedHighscores){
            list -> "${list[0].tiempo} ${list[0].movimientos}"
        }

        //return Highscore(hs)


        //val hsValue = Transformations.switchMap(orderedHighscores) {
        //    list -> getHighscore(list)
        //}
    }

    private fun getHighscore(lista:List<Highscore>): LiveData<List<Highscore>>{
        return orderedHighscores
    }
}

class HighscoreViewModelFactory(private val repository: HighscoreRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HighscoreViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HighscoreViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}