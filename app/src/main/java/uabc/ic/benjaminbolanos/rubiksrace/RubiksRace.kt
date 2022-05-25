package uabc.ic.benjaminbolanos.rubiksrace

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.PorterDuff

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View

import android.widget.Button
import android.widget.Toast

import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout

import uabc.ic.benjaminbolanos.rubiksrace.grid.Grid
import uabc.ic.benjaminbolanos.rubiksrace.highscore_database.*
import uabc.ic.benjaminbolanos.rubiksrace.highscores_view.HighscoreViewModel
import uabc.ic.benjaminbolanos.rubiksrace.highscores_view.HighscoreViewModelFactory
import uabc.ic.benjaminbolanos.rubiksrace.scrambler.Scrambler
import uabc.ic.benjaminbolanos.rubiksrace.util.Cronometro
import uabc.ic.benjaminbolanos.rubiksrace.util.EstiloCuadro

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
        //setSupportActionBar(findViewById(R.id.rubiksrace_toolbar))
        setEstilo()
        iniciarBotones()
        crearMenus()
        cronometro.iniciar()
    }
 /**
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.toolbar_opc_reglas -> {
                //
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }*/

    private fun setEstilo(){
        val config = getSharedPreferences("config", Context.MODE_PRIVATE)
        val estilo = config.getInt("estilo", 0)
        grid = Grid(findViewById(R.id.player_grid), estilo)
        scrambler = Scrambler(findViewById(R.id.scrambler_grid), estilo)
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

    fun crearMenus(){
        registerForContextMenu(findViewById(R.id.rubiksrace_layout))
    }

    override fun onCreateContextMenu( menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        menu?.setHeaderTitle("Cambiar color de fondo:")
        inflater.inflate(R.menu.background_color_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val preferences = getSharedPreferences("colores", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        return when(item.itemId){
            R.id.bgcolor_menu_item1 -> {
                Toast.makeText(applicationContext, "Turquesa", Toast.LENGTH_SHORT).show()
                editor.putInt("color_primario", resources.getColor(R.color.white,null))
                editor.putInt("color_secundario",resources.getColor(R.color.fondo_turquesa,null)).apply()
                cambiarColores(resources.getColor(R.color.white,null),resources.getColor(R.color.fondo_turquesa,null))
                true
            }
            R.id.bgcolor_menu_item2 -> {
                Toast.makeText(applicationContext, "Blanco", Toast.LENGTH_SHORT).show()
                editor.putInt("color_primario", resources.getColor(R.color.fondo_gris_oscuro,null))
                editor.putInt("color_secundario",resources.getColor(R.color.fondo_blanco,null)).apply()
                cambiarColores(resources.getColor(R.color.fondo_gris_oscuro,null),resources.getColor(R.color.fondo_blanco,null))
                true
            }
            R.id.bgcolor_menu_item3 -> {
                Toast.makeText(applicationContext, "Gris Oscuro", Toast.LENGTH_SHORT).show()
                editor.putInt("color_primario", resources.getColor(R.color.white,null))
                editor.putInt("color_secundario",resources.getColor(R.color.fondo_gris_oscuro,null)).apply()
                cambiarColores(resources.getColor(R.color.fondo_blanco,null),resources.getColor(R.color.fondo_gris_oscuro,null))
                true
            }
            else -> false
        }
    }

    override fun onResume() {
        super.onResume()
        val preferencias = getSharedPreferences("colores", Context.MODE_PRIVATE)
        if(preferencias.contains("color_secundario")) {
            val colorSecundario = preferencias.getInt("color_secundario", R.color.fondo_turquesa)
            val colorPrimario = preferencias.getInt("color_primario", R.color.white)
            cambiarColores(colorPrimario, colorSecundario)
        }
    }

    private fun cambiarColores(colorPrimario:Int, colorSecundario:Int){
        val fondo = findViewById<ConstraintLayout>(R.id.rubiksrace_layout)
        fondo.setBackgroundColor(colorSecundario)

        scrambleButton.apply {
            setTextColor(colorSecundario)
            backgroundTintList = ColorStateList.valueOf(colorPrimario)
            backgroundTintMode = PorterDuff.Mode.SRC_ATOP
        }

        slamButton.apply {
            setTextColor(colorSecundario)
            backgroundTintList = ColorStateList.valueOf(colorPrimario)
            backgroundTintMode = PorterDuff.Mode.SRC_ATOP
        }
    }

}

