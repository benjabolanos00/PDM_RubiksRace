package uabc.ic.benjaminbolanos.rubiksrace.highscore_database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import uabc.ic.benjaminbolanos.rubiksrace.highscores_view.Highscore

class HighscoreRepository(private val highscoreDao: HighscoreDao) {

    val allHighscores: Flow<List<Highscore>> = highscoreDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(highscore: Highscore){
        highscoreDao.insert(highscore)
    }
}