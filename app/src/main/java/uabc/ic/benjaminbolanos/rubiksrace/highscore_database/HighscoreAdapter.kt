package uabc.ic.benjaminbolanos.rubiksrace.highscore_database

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uabc.ic.benjaminbolanos.rubiksrace.R

class HighscoreAdapter : ListAdapter<Highscore, HighscoreAdapter.HighscoreViewHolder>(HighscoreComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighscoreViewHolder {
        return HighscoreViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: HighscoreViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class HighscoreViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val highscoreCombinacion = arrayOf<ImageView>(view.findViewById(R.id.highscore_item_combinacion_1), view.findViewById(
            R.id.highscore_item_combinacion_2
        ), view.findViewById(R.id.highscore_item_combinacion_3),
            view.findViewById(R.id.highscore_item_combinacion_4), view.findViewById(R.id.highscore_item_combinacion_5), view.findViewById(
                R.id.highscore_item_combinacion_6
            ),
            view.findViewById(R.id.highscore_item_combinacion_7), view.findViewById(R.id.highscore_item_combinacion_8), view.findViewById(
                R.id.highscore_item_combinacion_9
            ))
        val highscoreTiempo = view.findViewById<TextView>(R.id.highscore_item_tiempo)
        val highscoreMovimientos = view.findViewById<TextView>(R.id.highscore_item_movimientos)

        fun bind(highscoreItem: Highscore) {
            for(i in 0..8){
                highscoreItem.combinacionColores[i].let { highscoreCombinacion[i].setImageResource(it.valor) }
            }
            highscoreTiempo.text = highscoreItem.tiempoString()
            highscoreMovimientos.text = StringBuffer("${highscoreItem.movimientos} Movimientos")
        }

        companion object {
            fun create(parent: ViewGroup): HighscoreViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.highscore_item, parent, false)
                return HighscoreViewHolder(view)
            }
        }
    }

    class HighscoreComparator : DiffUtil.ItemCallback<Highscore>() {
        override fun areItemsTheSame(oldItem: Highscore, newItem: Highscore): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Highscore, newItem: Highscore): Boolean {
            return oldItem.id == newItem.id
        }
    }
}