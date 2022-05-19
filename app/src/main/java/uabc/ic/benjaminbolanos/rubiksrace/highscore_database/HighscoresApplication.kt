package uabc.ic.benjaminbolanos.rubiksrace.highscore_database

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

/**
 * Clase HighscoresApplication que crea una instancia de la base de datos y otra de repositorio
 * para solo tenerlo una vez en la aplicación. La propiedad lazy permite que estos solo se creén
 * al utilizarse por primera vez.
 */
class HighscoresApplication: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { HighscoreRoomDatabase.getDatabase(this, applicationScope) }

    val repository by lazy { HighscoreRepository(database.highscoreDao()) }
}