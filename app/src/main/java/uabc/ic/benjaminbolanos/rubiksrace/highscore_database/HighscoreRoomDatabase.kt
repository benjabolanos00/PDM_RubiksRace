package uabc.ic.benjaminbolanos.rubiksrace.highscore_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Highscore::class), version = 1, exportSchema = false)
abstract class HighscoreRoomDatabase: RoomDatabase() {

    abstract fun highscoreDao(): HighscoreDao

    companion object{
        @Volatile
        private var INSTANCE: HighscoreRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): HighscoreRoomDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HighscoreRoomDatabase::class.java,
                    "highscore_database"
                )
                    .addCallback(HighscoreDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class HighscoreDatabaseCallback(
        private val scope: CoroutineScope
        ): RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var highscoreDao = database.highscoreDao()
                    highscoreDao.deleteAll()
                    val highscore = Highscore.random()
                    highscoreDao.insert(highscore)
                    highscoreDao.insert(Highscore.random())
                }
            }
        }
        }
}