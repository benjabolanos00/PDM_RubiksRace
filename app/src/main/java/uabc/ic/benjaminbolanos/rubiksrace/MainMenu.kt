package uabc.ic.benjaminbolanos.rubiksrace

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import androidx.core.view.iterator
import androidx.core.view.size
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonToggleGroup
import uabc.ic.benjaminbolanos.rubiksrace.highscores_view.HighscoresActivity
import uabc.ic.benjaminbolanos.rubiksrace.tutorial.Tutorial

class MainMenu : AppCompatActivity() {

    lateinit var toggleGroup: MaterialButtonToggleGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        //Se registra el context menu
        registerForContextMenu(findViewById(R.id.main_menu_layout))
    }

    /**
     * Método que se ejecuta al resumir la actividad. Carga configuraciones y colores.
     */
    override fun onResume() {
        super.onResume()
        setToggleGroup()
        setConfig()
        setColores()
    }

    /**
     * Método que obtiene el estilo actual y selecciona el boton del grupo de ToggleButtons
     */
    private fun setConfig(){
        val config = getSharedPreferences("config", Context.MODE_PRIVATE)
        if(config.contains("estilo")){
            val estilo = config.getInt("estilo",0)
            toggleGroup.check(toggleGroup[estilo].id)
        }
    }

    /**
     * Método que toma el estilo actual y selecciona el boton de los ToggleButton
     */
    private fun setToggleGroup(){
        toggleGroup = findViewById(R.id.main_menu_estilo_toggle_group)
        for(i in 0 until toggleGroup.size){
            toggleGroup[i].setOnClickListener {
                val config = getSharedPreferences("config", Context.MODE_PRIVATE)
                config.edit().putInt("estilo",i).apply()
            }
        }
    }

    /**
     * Método que tomar los colores de las preferencias y manda a cambiar los colores de la actividad
     */
    private fun setColores(){
        val preferencias = getSharedPreferences("colores", Context.MODE_PRIVATE)
        if(preferencias.contains("color_secundario")) {
            val colorFondo = preferencias.getInt("color_secundario", R.color.fondo_turquesa)
            val colorTexto = preferencias.getInt("color_primario", R.color.white)
            cambiarColores(colorTexto, colorFondo)
        }
    }

    /**
     * Metodo que cambia los colores del fondo y texto.
     */
    private fun cambiarColores(colorPrimario:Int, colorSecundario:Int){
        val fondo = findViewById<ConstraintLayout>(R.id.main_menu_layout)
        val nuevoJuegoButton = findViewById<MaterialButton>(R.id.main_menu_new_game_button)
        val highscoresButton = findViewById<MaterialButton>(R.id.main_menu_highscores_button)
        val tutorialButton = findViewById<MaterialButton>(R.id.main_menu_tutorial_button)

        for(b in toggleGroup){
            b as Button
            b.setTextColor(colorPrimario)
        }

        nuevoJuegoButton.apply {
            setTextColor(colorSecundario)
            backgroundTintList = ColorStateList.valueOf(colorPrimario)
            backgroundTintMode = PorterDuff.Mode.SRC_ATOP
        }
        highscoresButton.apply {
            setTextColor(colorSecundario)
            backgroundTintList = ColorStateList.valueOf(colorPrimario)
            backgroundTintMode = PorterDuff.Mode.SRC_ATOP
        }
        tutorialButton.apply {
            setTextColor(colorSecundario)
            backgroundTintList = ColorStateList.valueOf(colorPrimario)
            backgroundTintMode = PorterDuff.Mode.SRC_ATOP
        }
        fondo.setBackgroundColor(colorSecundario)
    }

    /**
     * Metodo del boton Nuevo Juego que crea un intent hacia RubiksRace y manda si es en modo
     * daltonico.
     */
    fun newGame(view: View){
        val newGameIntent = Intent(applicationContext, RubiksRace::class.java)
        startActivity(newGameIntent)
    }

    /**
     * Metodo del boton Highscores que crea un intent hacia HighscoresActivity
     */
    fun goToHighscores(view: View){
        val highscoresIntent = Intent(applicationContext, HighscoresActivity::class.java)
        startActivity(highscoresIntent)
    }

    /**
     * Método al crearse el context menu, utilizando el layout del 'background_color_menu'
     */
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        menu?.setHeaderTitle("Cambiar color de fondo:")
        inflater.inflate(R.menu.background_color_menu, menu)
    }

    /**
     * Método al escoger una opcion del menu contextual. Este cambia los colores de las preferencias
     * y cambia los colores de la actividad
     */
    override fun onContextItemSelected(item: MenuItem): Boolean {
        val preferences = getSharedPreferences("colores", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        return when(item.itemId){
            R.id.bgcolor_menu_item1 -> {
                editor.putInt("color_primario", resources.getColor(R.color.white,null))
                editor.putInt("color_secundario",resources.getColor(R.color.fondo_turquesa,null)).apply()
                cambiarColores(resources.getColor(R.color.white,null),resources.getColor(R.color.fondo_turquesa,null))
                true
            }
            R.id.bgcolor_menu_item2 -> {
                editor.putInt("color_primario", resources.getColor(R.color.fondo_gris_oscuro,null))
                editor.putInt("color_secundario",resources.getColor(R.color.fondo_blanco,null)).apply()
                cambiarColores(resources.getColor(R.color.fondo_gris_oscuro,null),resources.getColor(R.color.fondo_blanco,null))
                true
            }
            R.id.bgcolor_menu_item3 -> {
                editor.putInt("color_primario", resources.getColor(R.color.white,null))
                editor.putInt("color_secundario",resources.getColor(R.color.fondo_gris_oscuro,null)).apply()
                cambiarColores(resources.getColor(R.color.fondo_blanco,null),resources.getColor(R.color.fondo_gris_oscuro,null))
                true
            }
            else -> false
        }
    }

    /**
     * Realiza un intent a la actividad Tutorial
     */
    fun goToTutorial(view: View){
        val intent = Intent(this, Tutorial::class.java)
        startActivity(intent)
    }
}