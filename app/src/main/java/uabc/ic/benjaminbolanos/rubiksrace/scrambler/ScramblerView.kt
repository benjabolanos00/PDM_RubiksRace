package uabc.ic.benjaminbolanos.rubiksrace.scrambler

import android.widget.GridLayout
import android.widget.ImageView
import androidx.core.view.get
import uabc.ic.benjaminbolanos.rubiksrace.util.Cuadro

class ScramblerView(private val scramblerGrid: GridLayout) {

    /**
     * Método que recibe la nueva combinación del scrambler y la actualiza.
     */
    fun actualizar(nuevosColores:Array<Cuadro>, estilo: Int){
        for(i in 0 until 9){
            (scramblerGrid[i] as ImageView).setImageResource(nuevosColores[i].getValor(estilo))
        }
    }
}