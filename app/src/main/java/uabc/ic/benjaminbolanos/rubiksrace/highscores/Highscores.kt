package uabc.ic.benjaminbolanos.rubiksrace.highscores

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uabc.ic.benjaminbolanos.rubiksrace.R

class Highscores : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView

    /**
     * Método onCreate que inicializa la Actividad y sus componentes.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_highscores)
        initRecylerView()
    }

    /**
     * Método que inicializa el RecyclerView, su layout manager, y define su adapter con el adapter
     * que se creó en HighscoreAdapter y el dataset de la lista de highscores
     */
    private fun initRecylerView(){
        recyclerView = findViewById<RecyclerView>(R.id.highscores_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = HighscoreAdapter(ext.highscores)
    }

    /**
     * Método para crear un Highscore random y refrescar el view para mostrarlo.
     */
    fun crearHighscore(view: View){
        Highscore.random()
        recyclerView.adapter?.let { recyclerView.adapter!!.notifyItemInserted(it.itemCount-1) }
        Log.i("HS","Highscore creado. ${ext.highscores.size}")
    }
}