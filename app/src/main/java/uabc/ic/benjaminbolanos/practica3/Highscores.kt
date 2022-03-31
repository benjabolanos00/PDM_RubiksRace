package uabc.ic.benjaminbolanos.practica3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Highscores : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_highscores)
        initRecylerView()
    }

    private fun initRecylerView(){
        recyclerView = findViewById<RecyclerView>(R.id.highscores_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = HighscoreAdapter(ext.highscores)
    }

    fun crearHighscore(view: View){
        Highscore.random()
        recyclerView.adapter?.let { recyclerView.adapter!!.notifyItemInserted(it.itemCount-1) }
        Log.i("HS","Highscore creado. ${ext.highscores.size}")
    }


}