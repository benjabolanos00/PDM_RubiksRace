package uabc.ic.benjaminbolanos.practica3

import kotlin.random.Random

class GridModelo {
    private val cuadros:Array<Color> = Array(25){Color()}
    private val colores:Array<Color> = arrayOf(Color("azul"), Color("rojo"), Color("verde"),
            Color("amarillo"), Color("blanco"), Color("naranja"))

    init{
        crearGrid()
    }

    fun intercambiar(desde:Int, hacia:Int):Boolean{
        if(cuadros[desde].getValor() == Color("negro").getValor()){
            val aux = cuadros[hacia]
            cuadros[hacia] = cuadros[desde]
            cuadros[desde] = aux
        }
        return false
    }

    fun crearGrid(){
        var i = 0
        val contadoresColores:Array<Int> = Array(6){0}

        while(i < 25){
            val intRandom = Random.nextInt(0,6)
            if(contadoresColores[intRandom] == 4){
                i--
            } else {
                cuadros[i] = colores[intRandom]
                contadoresColores[intRandom]++
            }
            i++
        }
    }

    fun setColorIn(posCuadro:Int, color: Color){
        cuadros[posCuadro] = color
    }

    fun getGrid():Array<Color>{
        return cuadros
    }

}