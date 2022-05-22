package uabc.ic.benjaminbolanos.rubiksrace.grid

import android.widget.GridLayout
import android.widget.ImageView
import androidx.core.view.get
import uabc.ic.benjaminbolanos.rubiksrace.util.Color

class GridView(val gridLayout: GridLayout) {


    fun actualizar(nuevosColores: Array<Color>){
        val tamGrid = gridLayout.columnCount*gridLayout.rowCount
        for(i in 0 until tamGrid){
            (gridLayout[i] as ImageView).setImageResource(nuevosColores[i].valor)
        }
    }

    fun seleccionar(posicion: Int, imgID: Int){
        (gridLayout[posicion] as ImageView).setImageResource(imgID)
    }
}