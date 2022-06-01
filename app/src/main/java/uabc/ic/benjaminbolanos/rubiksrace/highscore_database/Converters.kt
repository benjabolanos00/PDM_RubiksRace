package uabc.ic.benjaminbolanos.rubiksrace.highscore_database

import androidx.room.TypeConverter
import uabc.ic.benjaminbolanos.rubiksrace.util.Cuadro

/**
 * Clase Converters que contiene los convertidores de datos para poder guardar los resultados
 * en la base de datos.
 */
class Converters {

    /**
     * Convierte un String en un Array de Colores
     */
    @TypeConverter
    fun fromString(value: String): Array<Cuadro>{
        val values = value.split(",")
        val colores = Array(values.size){Cuadro("negro")}
        for(i in values.indices){
            colores[i] = Cuadro(values[i])
        }
        return colores
    }

    /**
     * Convierte un Array de Colores en un String
     */
    @TypeConverter
    fun colorArrayToString(colores: Array<Cuadro>): String{
        var value = ""

        for(color in colores){
            value += color.nombre + ","
        }

        return value
    }
}