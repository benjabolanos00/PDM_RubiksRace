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

class HighscoresActivity : AppCompatActivity() {


    private val highscoreViewModel: HighscoreViewModel by viewModels {
        HighscoreViewModelFactory((application as HighscoresApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_highscores)
        val recyclerView = findViewById<RecyclerView>(R.id.highscores_recycler_view)
        val adapter = HighscoreAdapter()
        setHighestScore()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        highscoreViewModel.orderedHighscores.observe(this){ highscores ->
            highscores.let { adapter.submitList(it) }
        }
        val orderSwitch = findViewById<SwitchCompat>(R.id.highscores_orderby_switch)

        orderSwitch.setOnCheckedChangeListener { _, b ->
            if(b){
                recyclerView.adapter = adapter
                highscoreViewModel.allHighscores.observe(this){ highscores ->
                    highscores.let { adapter.submitList(it) }
                }
            } else {
                recyclerView.adapter = adapter
                highscoreViewModel.orderedHighscores.observe(this){ highscores ->
                    highscores.let { adapter.submitList(it) }
                }
            }
        }
    }

    private fun setHighestScore(){
        highscoreViewModel.orderedHighscores.observe(this){ highscores ->
            if(highscores.isNotEmpty()) {
                for(h in highscores) h.recordActual = false
            }
        }
        highscoreViewModel.orderedHighscores.observe(this){ highscores ->
            if(highscores.isNotEmpty()) {
                for(h in highscores){
                    h.recordActual = false
                }
                val best = highscores[0]
                best.recordActual = true
            }

            for(h in highscores){
                Log.i("HS", "ID: ${h.id} es ${h.recordActual}")
            }
        }
    }
}