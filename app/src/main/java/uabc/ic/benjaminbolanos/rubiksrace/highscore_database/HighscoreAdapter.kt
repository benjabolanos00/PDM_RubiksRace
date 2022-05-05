package uabc.ic.benjaminbolanos.rubiksrace.highscore_database

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        holder.bind(current.tiempoString())
    }

    class HighscoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val wordItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            wordItemView.text = text
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
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Highscore, newItem: Highscore): Boolean {
            return oldItem.id == newItem.id
        }
    }
}