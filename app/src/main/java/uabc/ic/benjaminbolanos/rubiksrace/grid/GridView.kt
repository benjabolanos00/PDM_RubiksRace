package uabc.ic.benjaminbolanos.rubiksrace.grid

import android.widget.GridLayout
import android.widget.ImageView
import androidx.core.view.get
import uabc.ic.benjaminbolanos.rubiksrace.util.Cuadro

class GridView(val gridLayout: GridLayout) {

    /**
     * Método que actualiza los colores de los cuadros del gridview. Recibe un array con los Cuadros
     * y el estilo con el cual mostrarlo
     */
    fun actualizar(nuevosColores: Array<Cuadro>, estiloCuadro: Int){
        val tamGrid = gridLayout.columnCount*gridLayout.rowCount
        for(i in 0 until tamGrid){
            (gridLayout[i] as ImageView).setImageResource(nuevosColores[i].getValor(estiloCuadro))
        }
    }

    /**
     * Método que muestra el cuadro indicado como seleccionado
     */
    fun seleccionar(posicion: Int, imgID: Int){
        (gridLayout[posicion] as ImageView).setImageResource(imgID)
    }
}