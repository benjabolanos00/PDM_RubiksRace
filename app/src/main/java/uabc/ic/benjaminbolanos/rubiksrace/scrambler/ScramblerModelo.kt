package uabc.ic.benjaminbolanos.rubiksrace.scrambler

import uabc.ic.benjaminbolanos.rubiksrace.util.Color
import uabc.ic.benjaminbolanos.rubiksrace.util.Dado

class ScramblerModelo(var modoDaltonico: Boolean) {
    private lateinit var dados: Array<Dado>
    private var combinacion: Array<Color> = Array(9) { Color() }


    /**
     * Constructor que manda a llamar al método scramble() para crear una nueva combinación.
     */
    init {
        scramble()
    }

    /**
     * Método scramble que lanza los 9 dados. No permite que las caras visibles de los dados
     * tengan más de cuatro colores iguales.
     */
    fun scramble() {
        dados = Array(9) { Dado(modoDaltonico) }
        val contadoresColores: Array<Int> = Array(6) { 0 }
        var i = 0
        while (i < 9) {
            dados[i].tirar()
            if (contadoresColores[dados[i].getCaraInt()] >= 4) {
                i--
            } else {
                contadoresColores[dados[i].getCaraInt()]++
                combinacion[i] = dados[i].getCaraVisible()
            }
            i++
        }
    }

    /**
     * Método que consigue la combinación actual de colores y la retorna.
     */
    fun getCombinacion(): Array<Color> {
        return combinacion
    }
}