package uabc.ic.benjaminbolanos.rubiksrace.highscore_database

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class HighscoresApplication: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { HighscoreRoomDatabase.getDatabase(this, applicationScope) }

    val repository by lazy { HighscoreRepository(database.highscoreDao()) }
}