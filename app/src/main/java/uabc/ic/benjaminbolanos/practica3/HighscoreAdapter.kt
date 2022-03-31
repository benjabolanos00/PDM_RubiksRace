package uabc.ic.benjaminbolanos.practica3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class HighscoreAdapter(val highscoreList:List<Highscore>): RecyclerView.Adapter<HighscoreViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighscoreViewHolder {
        
        val layoutInflater =LayoutInflater.from(parent.context)
        return HighscoreViewHolder(layoutInflater.inflate(R.layout.highscore_item,parent,false))
    }

    override fun onBindViewHolder(holder: HighscoreViewHolder, position: Int) {
        val item = highscoreList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = highscoreList.size
}