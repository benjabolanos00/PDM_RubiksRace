package uabc.ic.benjaminbolanos.rubiksrace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SwitchCompat
import uabc.ic.benjaminbolanos.rubiksrace.highscore_database.HighscoresActivity
import uabc.ic.benjaminbolanos.rubiksrace.highscores_view.Highscores

class MainMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
    }

    fun newGame(view: View){
        val switch = findViewById<SwitchCompat>(R.id.main_menu_colorblind_switch)
        val newGameIntent = Intent(applicationContext, RubiksRace::class.java)
        newGameIntent.putExtra("ColorBlindMode", switch.isChecked)
        startActivity(newGameIntent)
    }

    fun goToHighscores(view: View){
        val highscoresIntent = Intent(applicationContext, HighscoresActivity::class.java)
        startActivity(highscoresIntent)
    }
}