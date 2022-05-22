package uabc.ic.benjaminbolanos.rubiksrace

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import uabc.ic.benjaminbolanos.rubiksrace.grid.Grid
import uabc.ic.benjaminbolanos.rubiksrace.highscore_database.*
import uabc.ic.benjaminbolanos.rubiksrace.highscores_view.HighscoreViewModel
import uabc.ic.benjaminbolanos.rubiksrace.highscores_view.HighscoreViewModelFactory
import uabc.ic.benjaminbolanos.rubiksrace.scrambler.Scrambler
import uabc.ic.benjaminbolanos.rubiksrace.util.Cronometro

class RubiksRace() : AppCompatActivity() {

    //Cronometro
    private val cronometro = Cronometro()

    //HighscoreDatabase
    private val highscoreViewModel: HighscoreViewModel by viewModels{
        HighscoreViewModelFactory((application as HighscoresApplication).repository)
    }

    //Grid & Scrambler
    private lateinit var scrambler: Scrambler
    private lateinit var grid: Grid

    //Botones
    private lateinit var scrambleButton:Button
    private lateinit var slamButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rubiksrace)
        setModoDaltonico()
        iniciarBotones()
        cronometro.iniciar()
    }

    private fun setModoDaltonico(){
        val data = intent.extras
        var modoDaltonico = false
        if(data != null){
            modoDaltonico = data.getBoolean("ColorBlindMode")
        }
        grid = Grid(findViewById(R.id.player_grid), modoDaltonico)
        scrambler = Scrambler(findViewById(R.id.scrambler_grid), modoDaltonico)
        scrambler.scramble()
    }

    private fun iniciarBotones(){
        scrambleButton = findViewById(R.id.scramble_button)
        slamButton = findViewById(R.id.slam_button)

        scrambleButton.setOnClickListener {
            scrambler.scramble()
        }

        slamButton.setOnClickListener {
            if(grid.hayGanador(scrambler.getCombinacion())){
                terminarJuego()
            } else {
                val v = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                v.vibrate(VibrationEffect.createOneShot(500,
                    VibrationEffect.DEFAULT_AMPLITUDE))
                Toast.makeText(applicationContext, "Nel pa", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun terminarJuego(){
        cronometro.parar()
        val newHighscore = Highscore(cronometro.tiempo, grid.getMovimientos(), scrambler.getCombinacion())
        val intent = Intent(applicationContext,Winner::class.java)
        highscoreViewModel.insert(newHighscore)
        intent.putExtra("movs", newHighscore.movimientos)
        intent.putExtra("tiempo", newHighscore.tiempoString())
        finish()
        startActivity(intent)
    }

}