package uabc.ic.benjaminbolanos.rubiksrace

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
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

    override fun onResume() {
        super.onResume()
        val preferencias = getSharedPreferences("colores", Context.MODE_PRIVATE)
        if(preferencias.contains("color_secundario")){
            val colorSecundario = preferencias.getInt("color_secundario", R.color.fondo_turquesa)
            val colorPrimario = preferencias.getInt("color_primario", R.color.white)
            cambiarColores(colorPrimario, colorSecundario)
        }
    }

    fun cambiarColores(colorPrimario: Int, colorSecundario:Int){
        movesText.setTextColor(colorPrimario)
        timeText.setTextColor(colorPrimario)
        val fondo = findViewById<ConstraintLayout>(R.id.winner_layout)
        fondo.setBackgroundColor(colorSecundario)
        val felicidadesText = findViewById<TextView>(R.id.winner_title_text)
        felicidadesText.setTextColor(colorPrimario)

        highscoresButton.apply {
            setTextColor(colorSecundario)
            backgroundTintList = ColorStateList.valueOf(colorPrimario)
            backgroundTintMode = PorterDuff.Mode.SRC_ATOP
        }
    }

    /**
     * Metodo que inicializa los Views de la Actividad y crea el onClickListener del highscoreButton
     * el cual crea un intent que va hacia HighscoresActivity
     */
    private fun initViews(){
        movesText = findViewById(R.id.winner_moves_text)
        timeText = findViewById(R.id.winner_time_text)

        highscoresButton = findViewById<Button?>(R.id.winner_highscores_button).apply {
            setOnClickListener {
                val intent = Intent(applicationContext, HighscoresActivity::class.java)
                finish()
                startActivity(intent)
            }
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