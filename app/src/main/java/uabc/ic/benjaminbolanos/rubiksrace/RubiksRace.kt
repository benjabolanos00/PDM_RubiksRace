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
import android.widget.GridLayout
import android.widget.Toast

import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.DialogFragment

import uabc.ic.benjaminbolanos.rubiksrace.grid.Grid
import uabc.ic.benjaminbolanos.rubiksrace.highscore_database.*
import uabc.ic.benjaminbolanos.rubiksrace.highscores_view.HighscoreViewModel
import uabc.ic.benjaminbolanos.rubiksrace.highscores_view.HighscoreViewModelFactory
import uabc.ic.benjaminbolanos.rubiksrace.highscores_view.HighscoresActivity
import uabc.ic.benjaminbolanos.rubiksrace.scrambler.Scrambler
import uabc.ic.benjaminbolanos.rubiksrace.util.Cronometro
import uabc.ic.benjaminbolanos.rubiksrace.util.Cuadro

class RubiksRace : AppCompatActivity() {

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
    //private lateinit var scrambleButton:Button
    private lateinit var slamButton: Button

    //Toolbar
    private lateinit var toolbar:Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rubiksrace)
        toolbar = findViewById(R.id.rubiksrace_toolbar)
        setSupportActionBar(toolbar)
        getEstilo()
        iniciarBotones()
        crearDialog()
        registerForContextMenu(findViewById(R.id.rubiksrace_layout))
        cronometro.iniciar()
    }

    fun crearDialog(){
        val dialog: LoadingGame = LoadingGame.newInstance(scrambler.getCombinacion())
        dialog.show(supportFragmentManager, "tag")
        dialog.countDown()
    }

    /**
     * Crea el menú del toolbar
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    /**
     * Método que indica las acciones a realizar dependiendo del item seleccionado del menú
     * de opciones (menú del toolbar)
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.toolbar_opc_reglas -> {
                //Intent a la Activity de reglas
                val reglasIntent = Intent(this, Reglas::class.java)
                startActivity(reglasIntent)
                true
            }
            //Reinicia el juego
            R.id.toolbar_opc_reiniciar -> {
                reiniciarJuego()
                true
            }
            //Se cambia el estilo de los cuadros
            R.id.toolbar_opc_color_1 -> {
                setEstilo(Cuadro.ESTILO_NORMAL)
                true
            }
            R.id.toolbar_opc_color_2 -> {
                setEstilo(Cuadro.ESTILO_PASTEL)
                true
            }
            R.id.toolbar_opc_color_3 -> {
                setEstilo(Cuadro.ESTILO_DALTONICO)
                true
            }
            //Intent a Activity de Highscores
            R.id.toolbar_opc_historial -> {
                val highscoresIntent = Intent(applicationContext, HighscoresActivity::class.java)
                startActivity(highscoresIntent)
                true
            }
            //Intent a Activity de Creditos
            R.id.toolbar_opc_creditos -> {
                val creditosIntent = Intent(applicationContext, Creditos::class.java)
                startActivity(creditosIntent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getEstilo(){
        val config = getSharedPreferences("config", Context.MODE_PRIVATE)
        val estilo = config.getInt("estilo", 0)
        grid = Grid(findViewById(R.id.player_grid), estilo)
        scrambler = Scrambler(findViewById(R.id.scrambler_grid), estilo)
        scrambler.scramble()
    }

    /**
     * Método que a partir del nuevo estilo, cambia los estilos del Scrambler y del Grid. También,
     * guarda los estilos en las preferencias compartidas.
     */
    private fun setEstilo(nuevoEstilo: Int){
        grid.cambiarEstilo(nuevoEstilo)
        scrambler.cambiarEstilo(nuevoEstilo)
        val config = getSharedPreferences("config", Context.MODE_PRIVATE)
        val edit = config.edit()
        edit.putInt("estilo", nuevoEstilo).apply()
    }

    private fun iniciarBotones(){
        slamButton = findViewById(R.id.slam_button)

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

    /**
     * Método que reinicia el juego al darle una vez al scramble y reiniciar la grid.
     */
    private fun reiniciarJuego(){
        scrambler.scramble()
        grid.reiniciarGrid()
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

    override fun onCreateContextMenu( menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        menu?.setHeaderTitle("Cambiar color de fondo:")
        inflater.inflate(R.menu.background_color_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){
            R.id.bgcolor_menu_item1 -> {
                cambiarColores(resources.getColor(R.color.white,null),resources.getColor(R.color.fondo_turquesa,null))
                true
            }
            R.id.bgcolor_menu_item2 -> {
                cambiarColores(resources.getColor(R.color.fondo_gris_oscuro,null),resources.getColor(R.color.fondo_blanco,null))
                true
            }
            R.id.bgcolor_menu_item3 -> {
                cambiarColores(resources.getColor(R.color.fondo_blanco,null),resources.getColor(R.color.fondo_gris_oscuro,null))
                true
            }
            else -> false
        }
    }

    override fun onResume() {
        super.onResume()
        getColores()
    }

    private fun getColores(){
        val preferencias = getSharedPreferences("colores", Context.MODE_PRIVATE)
        if(preferencias.contains("color_secundario")) {
            val colorSecundario = preferencias.getInt("color_secundario", R.color.fondo_turquesa)
            val colorPrimario = preferencias.getInt("color_primario", R.color.white)
            cambiarColores(colorPrimario, colorSecundario)
        }
    }

    private fun cambiarColores(colorPrimario:Int, colorSecundario:Int){
        val preferences = getSharedPreferences("colores", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putInt("color_primario", colorPrimario)
        editor.putInt("color_secundario",colorSecundario).apply()

        val fondo = findViewById<ConstraintLayout>(R.id.rubiksrace_layout)
        fondo.setBackgroundColor(colorSecundario)

        toolbar.apply {
            setBackgroundColor(colorPrimario)
            title = ""
            setTitleTextColor(colorPrimario)
            ContextCompat.getDrawable(applicationContext,R.drawable.ic_options_menu_blanco)?.apply{
                var newIcon = this.mutate()
                newIcon = DrawableCompat.wrap(newIcon)
                DrawableCompat.setTint(newIcon, colorSecundario)
                DrawableCompat.setTintMode(newIcon, PorterDuff.Mode.SRC_IN)
                overflowIcon = newIcon
            }

        }

        slamButton.apply {
            setTextColor(colorSecundario)
            backgroundTintList = ColorStateList.valueOf(colorPrimario)
            backgroundTintMode = PorterDuff.Mode.SRC_ATOP
        }
    }

}

