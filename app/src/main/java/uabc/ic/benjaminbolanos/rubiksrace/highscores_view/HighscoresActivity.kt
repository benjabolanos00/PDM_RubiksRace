package uabc.ic.benjaminbolanos.rubiksrace.highscores_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uabc.ic.benjaminbolanos.rubiksrace.R
import uabc.ic.benjaminbolanos.rubiksrace.highscore_database.HighscoresApplication

/**
 * Clase HighscoresActivity en la cual se maneja la actividad donde se muestra el recyclerview
 * con los highscores guardados en la base de datos. En esta se tiene un switch para mostrar los
 * datos ordenados de mejor a peor, o mostrarlos a como fueron insertados.
 */
class HighscoresActivity : AppCompatActivity() {

    //Instancia de HighscoreViewModel
    private val highscoreViewModel: HighscoreViewModel by viewModels {
        HighscoreViewModelFactory((application as HighscoresApplication).repository)
    }

    /**
     * onCreate que crea el recycler view, obtiene los datos de la base de datos y los muestra.
     * Tambien, crea el onCheckedChangeListener del Switch.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_highscores)
        val recyclerView = findViewById<RecyclerView>(R.id.highscores_recycler_view)
        val adapter = HighscoreAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        highscoreViewModel.orderedHighscores.observe(this){ highscores ->
            highscores.let { adapter.submitList(it) }
        }


        val orderSwitch = findViewById<SwitchCompat>(R.id.highscores_orderby_switch)
        orderSwitch.setOnCheckedChangeListener { _, b ->
            if(b){ //Si está seleccionado, se muestra la lista a como fueron insertados los datos
                highscoreViewModel.allHighscores.observe(this){ highscores ->
                    highscores.let { adapter.submitList(it) }
                }
            } else {//Si no, se muestra la lista ordenada empezando por el record actual.
                highscoreViewModel.orderedHighscores.observe(this){ highscores ->
                    highscores.let { adapter.submitList(it) }
                }
            }
        }
    }
}