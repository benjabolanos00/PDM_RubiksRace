package uabc.ic.benjaminbolanos.rubiksrace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import uabc.ic.benjaminbolanos.rubiksrace.highscores_view.HighscoresActivity

class Winner : AppCompatActivity() {
    private lateinit var movesText: TextView
    private lateinit var timeText: TextView
    private lateinit var highscoresButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_winner)
        initViews()
        setInfo()
    }

    /**
     * Metodo que inicializa los Views de la Actividad y crea el onClickListener del highscoreButton
     * el cual crea un intent que va hacia HighscoresActivity
     */
    private fun initViews(){
        movesText = findViewById(R.id.winner_moves_text)
        timeText = findViewById(R.id.winner_time_text)
        highscoresButton = findViewById(R.id.winner_highscores_button)

        highscoresButton.setOnClickListener {
            val intent = Intent(this, HighscoresActivity::class.java)
            finish()
            startActivity(intent)
        }
    }

    /**
     * Metodo que obtiene la informaciona del intent y la muestra en los views.
     */
    private fun setInfo(){
        val bundle = intent.extras
        if(bundle != null){
            val movs = bundle.get("movs") as Int
            val movsString = StringBuffer(movs.toString()).append(" Movimientos")
            movesText.text = movsString
            timeText.text = bundle.get("tiempo") as String
        }
    }
}