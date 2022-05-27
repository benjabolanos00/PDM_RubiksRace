package uabc.ic.benjaminbolanos.rubiksrace.grid

import android.widget.GridLayout
import android.widget.ImageView
import androidx.core.view.get
import uabc.ic.benjaminbolanos.rubiksrace.util.Color
import uabc.ic.benjaminbolanos.rubiksrace.util.EstiloCuadro

class Grid(private val gridLayout: GridLayout, private var estilo: Int) {

    private val gridModelo = GridModelo()
    private val gridView = GridView(gridLayout)

    private var seleccionesGrid = arrayOf(-1,-1)
    private var cantidadSelecciones = 0

    init {
        iniciarListeners()
        gridModelo.crearGrid()
        gridView.actualizar(gridModelo.cuadros, estilo)
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

    fun cambiarEstilo(nuevoEstilo: Int){
        estilo = nuevoEstilo
        gridView.actualizar(gridModelo.cuadros, estilo)
    }

    fun reiniciarGrid(){
        gridModelo.crearGrid()
        gridView.actualizar(gridModelo.cuadros, estilo)
    }

    private fun realizarIntercambio(){
        gridModelo.intercambiar(seleccionesGrid[0],seleccionesGrid[1])
        gridView.actualizar(gridModelo.cuadros, estilo)
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