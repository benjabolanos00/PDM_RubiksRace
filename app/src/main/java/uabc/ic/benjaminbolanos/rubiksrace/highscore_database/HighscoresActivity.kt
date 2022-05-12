package uabc.ic.benjaminbolanos.rubiksrace.highscore_database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import uabc.ic.benjaminbolanos.rubiksrace.R
import uabc.ic.benjaminbolanos.rubiksrace.util.Color

class HighscoresActivity : AppCompatActivity() {


    private  val highscoreViewModel: HighscoreViewModel by viewModels {
        HighscoreViewModelFactory((application as HighscoresApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_highscores)
        //agregarExtras()
        val recyclerView = findViewById<RecyclerView>(R.id.highscores_recycler_view)
        val adapter = HighscoreAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        highscoreViewModel.allHighscores.observe(this){ highscores ->
            highscores.let { adapter.submitList(it) }
        }

    }

    private fun agregarExtras(){
        val datos = intent.extras
        if(datos != null){
            val hsString = datos.get("hs") as String
            Log.i("HS", hsString)
            val hs = Json.decodeFromString(hsString) as Highscore
            highscoreViewModel.insert(hs)
        }
    }


}