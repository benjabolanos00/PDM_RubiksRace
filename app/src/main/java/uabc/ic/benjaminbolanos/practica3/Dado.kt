package uabc.ic.benjaminbolanos.practica3

import kotlin.random.Random

/**
 * Clase Dado que modela un dado de seis lados. Cada lado es un color distinto.
 */
class Dado() {
    private val colores:Array<Color> = arrayOf(Color("azul"), Color("rojo"), Color("verde"),
        Color("amarillo"), Color("blanco"), Color("naranja"))
    private var caraVisible:Color
    private var caraInt:Int = 0

    /**
     * Cuando el dado se inicie, se cambia la cara visible lanzando el dado.
     */
    init {
        caraVisible = tirar()
    }

    /**
     * Función tirar que genera un numero aleatorio y ,dependiendo de ese numero, se agarra un Color
     * del array de colores. Retorna el nuevo color/cara visible.
     */
    fun tirar():Color{
        caraInt = Random.nextInt(0,6)
        caraVisible = colores[caraInt]
        return caraVisible
    }

    /**
     * Función que retorna en numero de la cara visible. El numero es el mismo que la posicion
     * en el array de colores de caraVisible.
     */
    fun getCaraInt():Int{
        return caraInt
    }

    /**
     * Funcion que retorna el color de la cara visible.
     */
    fun getCaraVisible(): Color {
        return caraVisible
    }
}