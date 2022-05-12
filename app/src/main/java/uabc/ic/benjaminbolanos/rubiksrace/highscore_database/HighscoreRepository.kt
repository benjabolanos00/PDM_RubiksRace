package uabc.ic.benjaminbolanos.rubiksrace.highscore_database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import java.util.concurrent.Callable

class HighscoreRepository(private val highscoreDao: HighscoreDao) {

    val allHighscores: Flow<List<Highscore>> = highscoreDao.getAll()
    val orderedHighscores: Flow<List<Highscore>> = highscoreDao.getOrdered()


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(highscore: Highscore){
        highscoreDao.insert(highscore)
    }

}