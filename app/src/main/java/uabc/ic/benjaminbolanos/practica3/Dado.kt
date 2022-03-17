package uabc.ic.benjaminbolanos.practica3

import kotlin.random.Random

class Dado() {
    private var colores:Array<Color> = arrayOf(Color("azul"), Color("rojo"), Color("verde"),
        Color("amarillo"), Color("blanco"), Color("naranja"))
    private var caraVisible:Color

    init {
        caraVisible = tirar()
    }

    fun tirar():Color{
        caraVisible = colores[Random.nextInt(0,6)]
        return caraVisible
    }

    fun cambiarCara(color:String){
        when(color.lowercase()){
            "azul" -> caraVisible = colores[0]
            "rojo" -> caraVisible = colores[1]
            "verde" -> caraVisible = colores[2]
            "amarillo" -> caraVisible = colores[3]
            "blanco" -> caraVisible = colores[4]
            "naranja" -> caraVisible = colores[5]
        }
    }

    fun getCaraVisible(): Color {
        return caraVisible
    }

}