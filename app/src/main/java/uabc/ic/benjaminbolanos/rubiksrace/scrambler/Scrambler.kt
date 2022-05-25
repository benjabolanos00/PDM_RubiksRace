package uabc.ic.benjaminbolanos.rubiksrace.scrambler

import android.widget.GridLayout
import uabc.ic.benjaminbolanos.rubiksrace.util.Color
import uabc.ic.benjaminbolanos.rubiksrace.util.EstiloCuadro

class Scrambler(scramblerGrid:GridLayout,private var estilo:Int) {
    private val scramblerView = ScramblerView(scramblerGrid)
    private val scramblerModelo = ScramblerModelo()

    init {
        scramblerGrid.setOnClickListener {
            scramble()
        }
        scramble()
    }

    fun cambiarEstilo(nuevoEstilo: Int){
        estilo = nuevoEstilo
        scramblerView.actualizar(scramblerModelo.getCombinacion(),estilo)
    }

    fun scramble(){
        scramblerModelo.scramble()
        scramblerView.actualizar(scramblerModelo.getCombinacion(), estilo)
    }

    fun getCombinacion(): Array<Color> = scramblerModelo.getCombinacion()
}