package uabc.ic.benjaminbolanos.rubiksrace.scrambler

import android.widget.GridLayout
import uabc.ic.benjaminbolanos.rubiksrace.util.Color

class Scrambler(scramblerGrid:GridLayout, modoDaltonico:Boolean) {
    private val scramblerView = ScramblerView(scramblerGrid)
    private val scramblerModelo = ScramblerModelo(modoDaltonico)

    init {
        scramblerGrid.setOnClickListener {
            scramble()
        }
        scramble()
    }

    fun scramble(){
        scramblerModelo.scramble()
        scramblerView.actualizar(scramblerModelo.getCombinacion())
    }

    fun getCombinacion(): Array<Color> = scramblerModelo.getCombinacion()
}