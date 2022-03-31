package uabc.ic.benjaminbolanos.practica3

class ScramblerModelo() {
    private var dados: Array<Dado> = Array(9) { Dado() }
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
        dados = Array(9){ Dado() }
        val contadoresColores: Array<Int> = Array(6) { 0 }
        var i = 0
        while (i < 9) {
            dados[i].tirar()
            if (contadoresColores[dados[i].getCaraInt()] > 3) {
                i--
            }
            i++
        }
    }

    /**
     * Método que consigue la combinación actual de colores y la retorna.
     */
    fun getCombinacion(): Array<Color> {
        combinacion = Array(9){ Color() }
        for (i in 0..8) {
            combinacion[i] = dados[i].getCaraVisible()
        }
        return combinacion
    }
}