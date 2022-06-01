package uabc.ic.benjaminbolanos.rubiksrace.highscores_view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uabc.ic.benjaminbolanos.rubiksrace.R
import uabc.ic.benjaminbolanos.rubiksrace.highscore_database.Highscore

/**
 * Clase HighscoreAdapter que crea un HighscoreViewHolder y lo vincula.
 */
class HighscoreAdapter : ListAdapter<Highscore, HighscoreAdapter.HighscoreViewHolder>(
    HighscoreComparator()
) {
    /**
     * La variable estilo va a indicar con que estilo se mostrarán los Highscores (normal, daltonico
     * o postel)
     */
    var estilo:Int = 2

    /**
     * Metodo en el cual se crea el Holder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighscoreViewHolder {
        return HighscoreViewHolder.create(parent, estilo)
    }

    /**
     * Metodo en el cual se vincula el holder a los datos
     */
    override fun onBindViewHolder(holder: HighscoreViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    /**
     * Clase para obtener un Highscore y a partir de sus datos cambiar la informacion de los
     * views del RecyclerView.
     */
    class HighscoreViewHolder(view: View, val estilo: Int) : RecyclerView.ViewHolder(view) {
        /**
         * Componentes que se encuentran en highscore_item.xml
         */
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

        /**
         * Método que obtiene el highscore y configura los componentes para mostrar sus datos.
         */
        fun bind(highscoreItem: Highscore) {
            for(i in 0..8){
                //Al obtener el cuadro, se obtendrá el cuadro con el estilo recibido en el adapter.
                highscoreItem.combinacionColores[i].let { highscoreCombinacion[i].setImageResource(it.getValor(estilo)) }
                Log.i("HS", "Valor es ${highscoreItem.combinacionColores[i].getValor(estilo)}")
            }
            highscoreTiempo.text = highscoreItem.tiempoString()
            highscoreMovimientos.text = StringBuffer("${highscoreItem.movimientos} Movimientos")
        }

        /**
         * Companion object que contiene un metodo para crear un holder tomando como View el
         * layout de highscore_item.xml
         */
        companion object {
            fun create(parent: ViewGroup, estilo:Int): HighscoreViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.highscore_item, parent, false)
                return HighscoreViewHolder(view, estilo)
            }
        }
    }

    /**
     * Clase HighscoreComparator que define como calcular si dos elementos son iguales o si
     * los contenidos son los mismos.
     */
    class HighscoreComparator : DiffUtil.ItemCallback<Highscore>() {
        override fun areItemsTheSame(oldItem: Highscore, newItem: Highscore): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Highscore, newItem: Highscore): Boolean {
            return oldItem.id == newItem.id
        }
    }
}