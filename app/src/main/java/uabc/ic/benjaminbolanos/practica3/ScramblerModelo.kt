package uabc.ic.benjaminbolanos.practica3

class ScramblerModelo(){
    private var dados: Array<Dado> = Array(9){Dado()}
    private val combinacion : Array<Color> = Array(9){Color()}

    init {
        scramble()
    }

    fun scramble(){
        for(dado in dados){
            dado.tirar()
        }
    }

    fun getCombinacion():Array<Color>{
        for(i in 0..8){
            combinacion[i] = dados[i].getCaraVisible()
        }
        return combinacion
    }


}