package uabc.ic.benjaminbolanos.rubiksrace.grid

import android.widget.GridLayout
import android.widget.ImageView
import androidx.core.view.get
import uabc.ic.benjaminbolanos.rubiksrace.util.Color

class Grid(private val gridLayout: GridLayout, modoDaltonico: Boolean) {

    private val gridModelo = GridModelo(modoDaltonico)
    private val gridView = GridView(gridLayout)

    private var seleccionesGrid = arrayOf(-1,-1)
    private var cantidadSelecciones = 0

    init {
        iniciarListeners()
        gridModelo.crearGrid()
        gridView.actualizar(gridModelo.cuadros)
    }

    private fun iniciarListeners(){
        val tamGrid = gridLayout.columnCount*gridLayout.rowCount
        for(i in 0 until tamGrid){
            (gridLayout[i] as ImageView).setOnClickListener {
                gridView.seleccionar(i, gridModelo.cuadros[i].getSelected())
                seleccionesGrid[cantidadSelecciones++] = i

                if(cantidadSelecciones == 2){
                    realizarIntercambio()
                    cantidadSelecciones = 0
                }
            }
        }
    }

    private fun realizarIntercambio(){
        gridModelo.intercambiar(seleccionesGrid[0],seleccionesGrid[1])
        gridView.actualizar(gridModelo.cuadros)
    }

    fun hayGanador(combinacionScrambler: Array<Color>): Boolean{
        val gridCentro = gridModelo.getCentro()
        for(i in gridCentro.indices){
            if(gridCentro[i].nombre != combinacionScrambler[i].nombre){
                return false
            }
        }
        return true
    }

    fun getMovimientos():Int = gridModelo.movimientos
}