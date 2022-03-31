package uabc.ic.benjaminbolanos.practica3

import kotlin.math.roundToInt

data class Highscore(val tiempo:Double, val movimientos:Int,val combinacion: Array<Color>?){

    /**
     * Método que compara el highscore con los demas del ArrayList de highscores para saber si es
     * el más alto.
     */
    fun isHighestScore():Boolean{
        var highestScore = Double.MAX_VALUE
        for(hs in ext.highscores){
            if(hs.tiempo < highestScore){
                highestScore = hs.tiempo
            }
        }
        return this.tiempo <= highestScore
    }

    fun tiempoString(): String{
        val tiempoInt = tiempo.roundToInt()

        val segundos = tiempoInt % 86400 % 3600 % 60
        val minutos = tiempoInt % 86400 % 3600 / 60
        val horas = tiempoInt % 86400 / 3600

        return String.format("%02d",horas) + " : " + String.format("%02d",minutos) + " : " + String.format("%02d",segundos);
    }
}
object ext{val highscores: ArrayList<Highscore> = ArrayList()}