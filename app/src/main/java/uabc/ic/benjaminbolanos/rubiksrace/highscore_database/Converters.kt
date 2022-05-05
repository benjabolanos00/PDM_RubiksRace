package uabc.ic.benjaminbolanos.rubiksrace.highscore_database

import androidx.room.TypeConverter
import uabc.ic.benjaminbolanos.rubiksrace.util.Color

class Converters {
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

    @TypeConverter
    fun colorArrayToString(colores: Array<Color>): String{
        var value = ""
        value += if(colores[0].colorBlind) "v,"
        else "f,"

        for(color in colores){
            value += color.nombre + ","
        }

        return value
    }
}