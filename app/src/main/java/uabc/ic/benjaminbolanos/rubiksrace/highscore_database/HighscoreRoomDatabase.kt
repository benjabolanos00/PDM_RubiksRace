package uabc.ic.benjaminbolanos.rubiksrace.highscore_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uabc.ic.benjaminbolanos.rubiksrace.highscores_view.Highscore

@Database(entities = arrayOf(Highscore::class), version = 1, exportSchema = false)
abstract class HighscoreRoomDatabase: RoomDatabase() {

    abstract fun highscoreDao(): HighscoreDao

    companion object{
        @Volatile
        private var INSTANCE: HighscoreRoomDatabase? = null

        fun getDatabase(context: Context): HighscoreRoomDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HighscoreRoomDatabase::class.java,
                    "highscore_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}