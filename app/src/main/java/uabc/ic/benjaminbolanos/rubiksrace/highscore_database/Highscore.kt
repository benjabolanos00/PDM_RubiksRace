package uabc.ic.benjaminbolanos.rubiksrace.highscore_database

import androidx.room.ColumnInfo
import uabc.ic.benjaminbolanos.rubiksrace.util.Color
import kotlin.math.roundToInt
import kotlin.random.Random
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "highscore_table")
data class Highscore(
    @ColumnInfo(name = "tiempo") val tiempo:Double,
    @ColumnInfo(name = "movimientos") val movimientos:Int,
    @ColumnInfo(name = "combinacion") val combinacionColores: Array<Color>){
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id = 0


    /**
     * Método que compara el highscore con los demas del ArrayList de highscores para saber si es
     * el más alto.
     */
    fun isHighestScore():Boolean{
        if(ext.highscores.size == 1){
            return true
        }

        var recordActual = ext.highscores[0]
        var menorTiempoActual = recordActual.tiempo
        val cantidadHighscores = ext.highscores.size-1
        for(i in 1 until cantidadHighscores){
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Highscore

        if (id != other.id) return false
        return true
    }

    override fun hashCode(): Int {
        var result = tiempo.hashCode()
        result = 31 * result + movimientos
        result = 31 * result + id
        return result
    }

    /**
     * Companion object que contiene un método para crear Highscore randoms
     * y los agrega a la lista de highscores
     */
    companion object{
        fun random(): Highscore {
            val comb = Array(9){ Color() }
            val t:Double = Random.nextDouble(360.0)
            val movs:Int = Random.nextInt(250)
            ext.highscores.add(Highscore(t, movs, comb))
            return Highscore(t, movs, comb)
        }
    }
}
object ext{val highscores: ArrayList<Highscore> = ArrayList()}