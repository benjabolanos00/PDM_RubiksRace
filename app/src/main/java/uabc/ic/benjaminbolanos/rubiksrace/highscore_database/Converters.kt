package uabc.ic.benjaminbolanos.rubiksrace.highscore_database

import androidx.room.TypeConverter
import uabc.ic.benjaminbolanos.rubiksrace.util.Color

/**
 * Clase Converters que contiene los convertidores de datos para poder guardar los resultados
 * en la base de datos.
 */
class Converters {

    /**
     * Convierte un String en un Array de Colores
     */
    @TypeConverter
    fun fromString(value: String): Array<Color>{
        val values = value.split(",")
        val colores = Array(values.size){Color()}
        val esModoDaltonico = values[0] == "v"
        for(i in 1 until values.size){
            colores[i-1] = Color(values[i], esModoDaltonico)
        }
        return colores
    }

    /**
     * Convierte un Array de Colores en un String, al principio guarda una letra para saber si es
     * modo daltonismo
     */
    @TypeConverter
    fun colorArrayToString(colores: Array<Color>): String{
        var value = ""
        value += if(colores[0].daltonismo) "v,"
        else "f,"

        for(color in colores){
            value += color.nombre + ","
        }

        return value
    }
}