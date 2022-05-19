package uabc.ic.benjaminbolanos.rubiksrace.highscores_view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uabc.ic.benjaminbolanos.rubiksrace.R
import uabc.ic.benjaminbolanos.rubiksrace.highscore_database.Highscore

class HighscoreAdapter : ListAdapter<Highscore, HighscoreAdapter.HighscoreViewHolder>(
    HighscoreComparator()
) {

    var isOrderedList = false

    override fun getItemViewType(position: Int): Int {
        return if(position == 0) 1 else 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighscoreViewHolder {
        return HighscoreViewHolder.create(parent, viewType, isOrderedList)
    }

    override fun onBindViewHolder(holder: HighscoreViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class HighscoreViewHolder(val view: View, val viewType: Int, val isOrderedList:Boolean) : RecyclerView.ViewHolder(view) {
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
        val highscoreLayout = view.findViewById<ConstraintLayout>(R.id.highscore_item_layout)

        fun bind(highscoreItem: Highscore) {
            for(i in 0..8){
                highscoreItem.combinacionColores[i].let { highscoreCombinacion[i].setImageResource(it.valor) }
            }
            highscoreTiempo.text = highscoreItem.tiempoString()
            highscoreMovimientos.text = StringBuffer("${highscoreItem.movimientos} Movimientos")
            Log.i("HS", "highscore i: ${highscoreItem.id} para ${highscoreItem.recordActual}")
            if(highscoreItem.recordActual or (isOrderedList and (viewType == 1))){
                Log.i("HS", "El id es ${highscoreItem.id} para $highscoreItem")
                highscoreLayout.setBackgroundColor(view.resources.getColor(R.color.highscore_record, null))
            }
        }

        companion object {
            fun create(parent: ViewGroup, viewType:Int, isOrderedList: Boolean): HighscoreViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.highscore_item, parent, false)
                return HighscoreViewHolder(view, viewType, isOrderedList)
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