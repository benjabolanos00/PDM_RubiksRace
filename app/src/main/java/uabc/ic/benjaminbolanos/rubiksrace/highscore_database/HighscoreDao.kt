package uabc.ic.benjaminbolanos.rubiksrace.highscore_database

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import uabc.ic.benjaminbolanos.rubiksrace.highscores_view.Highscore

@Dao
interface HighscoreDao {

    @Query("SELECT * FROM HIGHSCORE_TABLE")
    fun getAll(): Flow<List<Highscore>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(highscore: Highscore)

    @Delete
    fun deleteHighscore(highscore: Highscore)
}