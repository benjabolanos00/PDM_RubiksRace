package uabc.ic.benjaminbolanos.practica3

import android.util.Log
import kotlin.random.Random

class GridModelo {
    private val cuadros:Array<Color> = Array(25){Color()}
    private val colores:Array<Color> = arrayOf(Color("azul"), Color("rojo"), Color("verde"),
            Color("amarillo"), Color("blanco"), Color("naranja"))

    init{
        crearGrid()
    }

    fun intercambiar(desde:Int, hacia:Int):Boolean{
        var intercambiable = false
        if(cuadros[hacia].getValor() == Color("negro").getValor()){
            if(desde % 5 == 0) {
                if (hacia == (desde - 5) || hacia == (desde + 1) || hacia == (desde + 5)) {
                    intercambiable = true
                }
            }
            else if((desde+1) % 5 == 0){
                if(hacia == (desde - 5) || hacia == (desde - 1) || hacia == (desde + 5)) {
                    intercambiable = true
                }
            } else {
                if (hacia == (desde - 5) || hacia == (desde - 1) || hacia == (desde + 1) || hacia == (desde + 5)) {
                    intercambiable = true
                }
            }
        }
        if(intercambiable) {
            val aux = cuadros[hacia]
            cuadros[hacia] = cuadros[desde]
            cuadros[desde] = aux
        }

        return intercambiable
    }

    fun crearGrid(){
        var i = 0
        val contadoresColores:Array<Int> = Array(6){0}

        while(i < 24){
            val intRandom = Random.nextInt(0,6)
            if(contadoresColores[intRandom] >= 4){
                i--
            } else {
                cuadros[i] = colores[intRandom]
                contadoresColores[intRandom]++
            }
            i++
            Log.i("EXITO", "index: $i / ")
        }

        cuadros[i] = Color("negro")
    }

    fun getGrid():Array<Color>{
        return cuadros
    }

    fun getCentro():Array<Color>{
        var j = 0
        val centro:Array<Color> = Array(9){Color()}
        for(i in 0..24){
            if(i in 6..18 && i%5!=0 && (i+1)%5!=0){
                centro[j] = cuadros[i]
                j++
            }
        }
        return centro
    }

}