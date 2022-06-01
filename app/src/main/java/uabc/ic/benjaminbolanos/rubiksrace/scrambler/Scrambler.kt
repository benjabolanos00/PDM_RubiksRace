package uabc.ic.benjaminbolanos.rubiksrace.scrambler

import android.widget.GridLayout
import uabc.ic.benjaminbolanos.rubiksrace.util.Cuadro

class Scrambler(scramblerGrid:GridLayout,private var estilo:Int) {
    private val scramblerView = ScramblerView(scramblerGrid)
    private val scramblerModelo = ScramblerModelo()

    init {
        scramblerGrid.setOnClickListener {
            scramble()
        }
        scramble()
    }

    /**
     * Método para cambiar el estilo de los cuadros
     */
    fun cambiarEstilo(nuevoEstilo: Int){
        estilo = nuevoEstilo
        scramblerView.actualizar(scramblerModelo.getCombinacion(),estilo)
    }

    /**
     * Método que realiza el scramble en el modelo y lo actualiza en el view
     */
    fun scramble(){
        scramblerModelo.scramble()
        scramblerView.actualizar(scramblerModelo.getCombinacion(), estilo)
    }

    /**
     * Getter para la combinación
     */
    fun getCombinacion(): Array<Cuadro> = scramblerModelo.getCombinacion()
}