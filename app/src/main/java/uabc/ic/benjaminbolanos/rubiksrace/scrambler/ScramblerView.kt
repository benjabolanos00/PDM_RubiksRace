package uabc.ic.benjaminbolanos.rubiksrace.scrambler

import android.widget.GridLayout
import android.widget.ImageView
import androidx.core.view.get
import uabc.ic.benjaminbolanos.rubiksrace.util.Color

class ScramblerView(private val scramblerGrid: GridLayout) {

    fun actualizar(nuevosColores:Array<Color>, estilo: Int){
        for(i in 0 until 9){
            (scramblerGrid[i] as ImageView).setImageResource(nuevosColores[i].getValor(estilo))
        }
    }
}