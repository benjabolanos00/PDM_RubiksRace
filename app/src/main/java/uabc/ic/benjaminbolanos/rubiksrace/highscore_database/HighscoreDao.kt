package uabc.ic.benjaminbolanos.rubiksrace.highscore_database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * Interfaz HighscoreDao que contiene las sentencias de SQLite necesarias para el funcionamiento de la
 * base de datos.
 */
@Dao
interface HighscoreDao {

    /**
     * Método para obtener una lista de todos los elementos de la tabla HIGHSCORE_TABLE que contiene
     * todos los highscores ordenados de manera en que se fueron insertando
     */
    @Query("SELECT * FROM HIGHSCORE_TABLE")
    fun getAll(): Flow<List<Highscore>>

    /**
     * Metodo para insertar un nuevo Highscore.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(highscore: Highscore)

    /**
     * Metodo para borrar todos los elementos de la HIGHSCORE_TABLE
     */
    @Query("DELETE FROM HIGHSCORE_TABLE")
    suspend fun deleteAll()

    /**
     * Metodo para obtener una lista de los highscores ordenada por tiempo y movimientos.
     */
    @Query("SELECT * FROM HIGHSCORE_TABLE ORDER BY tiempo ASC, movimientos ASC")
    fun getOrdered(): Flow<List<Highscore>>
}