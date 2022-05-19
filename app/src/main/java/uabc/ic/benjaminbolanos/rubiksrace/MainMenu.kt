package uabc.ic.benjaminbolanos.rubiksrace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SwitchCompat
import uabc.ic.benjaminbolanos.rubiksrace.highscores_view.HighscoresActivity

class MainMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
    }

    /**
     * Metodo del boton Nuevo Juego que crea un intent hacia RubiksRace y manda si es en modo
     * daltonico.
     */
    fun newGame(view: View){
        val switch = findViewById<SwitchCompat>(R.id.main_menu_colorblind_switch)
        val newGameIntent = Intent(applicationContext, RubiksRace::class.java)
        newGameIntent.putExtra("ColorBlindMode", switch.isChecked)
        startActivity(newGameIntent)
    }

    /**
     * Metodo del boton Highscores que crea un intent hacia HighscoresActivity
     */
    fun goToHighscores(view: View){
        val highscoresIntent = Intent(applicationContext, HighscoresActivity::class.java)
        startActivity(highscoresIntent)
    }
}