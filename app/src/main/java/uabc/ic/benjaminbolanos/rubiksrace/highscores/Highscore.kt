package uabc.ic.benjaminbolanos.rubiksrace.highscores

import uabc.ic.benjaminbolanos.rubiksrace.util.Color
import kotlin.math.roundToInt
import kotlin.random.Random

data class Highscore(val tiempo:Double, val movimientos:Int,val combinacion: Array<Color>?){

    /**
     * Método que compara el highscore con los demas del ArrayList de highscores para saber si es
     * el más alto.
     */
    fun isHighestScore():Boolean{
        if(ext.highscores.size == 1){
            return true
        }
        var menorTiempoActual = Double.MAX_VALUE
        var recordActual = ext.highscores[0]
        val cantidadHighscores = ext.highscores.size-1
        for(i in 1..cantidadHighscores){
            if(ext.highscores[i].tiempo < menorTiempoActual){
                menorTiempoActual = ext.highscores[i].tiempo
                recordActual = ext.highscores[i]
            }
        }
        return this.tiempo < menorTiempoActual && this.movimientos < recordActual.movimientos
    }

    /**
     * Método que formatea el tiempo en formato hh:mm:ss
     */
    fun tiempoString(): String{
        val tiempoInt = tiempo.roundToInt()

        val segundos = tiempoInt % 86400 % 3600 % 60
        val minutos = tiempoInt % 86400 % 3600 / 60
        val horas = tiempoInt % 86400 / 3600

        return String.format("%02d",horas) + " : " + String.format("%02d",minutos) + " : " + String.format("%02d",segundos);
    }

    /**
     * Companion object que contiene un método para crear Highscore randoms
     * y los agrega a la lista de highscores
     */
    companion object{
        fun random(){
            val comb = Array(9){ Color() }
            val t:Double = Random.nextDouble(360.0)
            val movs:Int = Random.nextInt(250)
            ext.highscores.add(Highscore(t, movs, comb))
        }
    }
}
object ext{val highscores: ArrayList<Highscore> = ArrayList()}