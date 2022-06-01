package uabc.ic.benjaminbolanos.rubiksrace.highscore_database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

/**
 * Clase HighscoreRepository que abstrae el acceso a los datos de la base de datos. En este caso
 * solo se necesita acceso al DAO, no es necesario obtener una copia de la base de datos.
 */
class HighscoreRepository(private val highscoreDao: HighscoreDao) {

    val allHighscores: Flow<List<Highscore>> = highscoreDao.getAll() //Lista de todos los elementos
    val orderedHighscores: Flow<List<Highscore>> = highscoreDao.getOrdered() //Lista ordenada de los elementos

    /**
     * Método para insertar un nuevo highscore en la base de datos, este proceso se realiza en
     * un hilo diferente del principal.
     */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(highscore: Highscore){
        highscoreDao.insert(highscore)
    }

    /**
     * Método para eliminar todos los elementos en la base de datos, este proceso se realiza en
     * un hilo diferente del principal.
     */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAll(){
        highscoreDao.deleteAll()
    }
}