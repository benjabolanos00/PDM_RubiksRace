package uabc.ic.benjaminbolanos.rubiksrace.highscores

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uabc.ic.benjaminbolanos.rubiksrace.R

class HighscoreAdapter(val highscoreList:ArrayList<Highscore>): RecyclerView.Adapter<HighscoreViewHolder>() {

    /**
     * Método que se ejecuta onCreate y crea una instancia de LayoutInflater para instanciar un
     * layout.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighscoreViewHolder {
        val layoutInflater =LayoutInflater.from(parent.context)
        return HighscoreViewHolder(layoutInflater.inflate(R.layout.highscore_item,parent,false))
    }

    /**
     * Método que por cada dato guardado en la lista de highscores, crea un nuevo
     */
    override fun onBindViewHolder(holder: HighscoreViewHolder, position: Int) {
        val item = highscoreList[position]
        holder.render(item)
        holder.eliminarButton.setOnClickListener {
            val highscoreToRemove = highscoreList.get(position)
            highscoreList.remove(highscoreToRemove)
            notifyItemRangeRemoved(0, itemCount+1)
            Log.i("HS","Highscore eliminado. ${ext.highscores.size}")
        }
    }

    /**
     * Método que retorna el tamaño de la lista de highscores para saber cuantos highscores se mostraran
     * en el recycler view
     */
    override fun getItemCount(): Int = highscoreList.size
}