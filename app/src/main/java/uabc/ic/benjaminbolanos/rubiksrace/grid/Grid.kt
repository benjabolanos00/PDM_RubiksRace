package uabc.ic.benjaminbolanos.rubiksrace.grid

import android.widget.GridLayout
import android.widget.ImageView
import androidx.core.view.get
import uabc.ic.benjaminbolanos.rubiksrace.util.Cuadro

class Grid(private val gridLayout: GridLayout, private var estilo: Int) {

    private val gridModelo = GridModelo()
    private val gridView = GridView(gridLayout)

    private var seleccionesGrid = arrayOf(-1,-1)
    private var cantidadSelecciones = 0

    /**
     * Al iniciar se crean los listeners, se crea una grid y se actualiza la vista.
     */
    init {
        iniciarListeners()
        gridModelo.crearGrid()
        gridView.actualizar(gridModelo.cuadros, estilo)
    }

    /**
     * Método que crea los listeners de todos los cuadros del grid.
     */
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

    /**
     * Método que recibe un nuevo estilo y actualiza la vista para mostrarlo.
     */
    fun cambiarEstilo(nuevoEstilo: Int){
        estilo = nuevoEstilo
        gridView.actualizar(gridModelo.cuadros, estilo)
    }

    /**
     * Método para reiniciar el grid al crear uno nuevo y actualizarlo.
     */
    fun reiniciarGrid(){
        gridModelo.crearGrid()
        gridView.actualizar(gridModelo.cuadros, estilo)
    }

    /**
     * Método para realizar un intercambio entre dos cuadros.
     */
    private fun realizarIntercambio(){
        gridModelo.intercambiar(seleccionesGrid[0],seleccionesGrid[1])
        gridView.actualizar(gridModelo.cuadros, estilo)
    }

    /**
     * Método para saber si hay ganador comparando el centro del grid con una combinación que recibe
     * del Scrambler.
     */
    fun hayGanador(combinacionScrambler: Array<Cuadro>): Boolean{
        val gridCentro = gridModelo.getCentro()
        for(i in gridCentro.indices){
            if(gridCentro[i].nombre != combinacionScrambler[i].nombre){
                return false
            }
        }
        return true
    }

    /**
     * Getter para los movimientos
     */
    fun getMovimientos():Int = gridModelo.movimientos
}