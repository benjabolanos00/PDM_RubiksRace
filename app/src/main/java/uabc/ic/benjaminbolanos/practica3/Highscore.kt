package uabc.ic.benjaminbolanos.practica3

data class Highscore(val tiempo:Double){

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
}
object ext{val highscores: ArrayList<Highscore> = ArrayList()}