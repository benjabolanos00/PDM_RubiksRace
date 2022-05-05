package uabc.ic.benjaminbolanos.rubiksrace.highscore_database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uabc.ic.benjaminbolanos.rubiksrace.R

class HighscoresActivity : AppCompatActivity() {


    private  val highscoreViewModel: HighscoreViewModel by viewModels {
        HighscoreViewModelFactory((application as HighscoresApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_highscores)

        val recyclerView = findViewById<RecyclerView>(R.id.highscores_recycler_view)
        val adapter = HighscoreAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        highscoreViewModel.allHighscores.observe(this){ highscores ->
            highscores.let { adapter.submitList(it) }
        }
    }

    private fun agregarExtras(){

    }


}