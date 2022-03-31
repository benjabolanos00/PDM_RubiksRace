package uabc.ic.benjaminbolanos.practica3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Highscores : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_highscores)
        initRecylerView()
    }

    private fun initRecylerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.highscores_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = HighscoreAdapter(ext.highscores)
    }


}