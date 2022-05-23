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
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.button.MaterialButton
import uabc.ic.benjaminbolanos.rubiksrace.highscores_view.HighscoresActivity

class MainMenu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        registerForContextMenu(findViewById(R.id.main_menu_layout))
    }

    override fun onResume() {
        super.onResume()
        val preferencias = getSharedPreferences("colores", Context.MODE_PRIVATE)
        val colorFondo = preferencias.getInt("color_secundario", R.color.fondo_turquesa)
        val colorTexto = preferencias.getInt("color_primario", R.color.white)
        cambiarColores(colorTexto, colorFondo)
    }

    private fun cambiarColores(colorPrimario:Int, colorSecundario:Int){
        val fondo = findViewById<ConstraintLayout>(R.id.main_menu_layout)
        val tituloText = findViewById<TextView>(R.id.main_menu_title_text)
        val daltonicoText = findViewById<TextView>(R.id.main_menu_colorblind_text)
        val nuevoJuegoButton = findViewById<MaterialButton>(R.id.main_menu_new_game_button)
        val highscoresButton = findViewById<MaterialButton>(R.id.main_menu_highscores_button)
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
        tituloText.setTextColor(colorPrimario)
        daltonicoText.setTextColor(colorPrimario)
        fondo.setBackgroundColor(colorSecundario)
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

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
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
}