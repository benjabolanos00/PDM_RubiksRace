package uabc.ic.benjaminbolanos.practica3

import android.graphics.Color.*
import androidx.core.graphics.toColorInt
import kotlin.random.Random

class Color() {
    private var valor:Int = 0
    private lateinit var nombre:String

    /**
     * Constructor que inicializa el color con un color aleatorio.
     */
    init {
        randomColor()
    }

    /**
     * Constructor que inicializa el color a partir de su nombre
     */
    constructor(name: String) : this() {
        fromName(name)
    }

    /**
     * Método que cambia los valores del color a uno aleatorio. El color negro no puede salir.
     */
    fun randomColor():Color{
        when(Random.nextInt(0,6)){
            0 -> {
                this.nombre = "azul"
                this.valor = BLUE
            }
            1 -> {
                this.nombre = "rojo"
                this.valor = RED
            }
            2 -> {
                this.nombre = "verde"
                this.valor = GREEN
            }
            3 -> {
                this.nombre = "amarillo"
                this.valor = YELLOW
            }
            4 -> {
                this.nombre = "blanco"
                this.valor = WHITE
            }
            5 -> {
                this.nombre = "naranja"
                this.valor = rgb(255,155,0)
            }

        }
        return this
    }

    /**
     * Método para que el color actual se cambie por otro color dependiendo de su nombre.
     */
    private fun fromName(name:String) : Color {
        this.nombre = name.lowercase()
        when(name){
            "azul" -> this.valor = BLUE
            "rojo" -> this.valor = RED
            "verde" -> this.valor = GREEN
            "amarillo" ->  this.valor = YELLOW
            "blanco" -> this.valor = WHITE
            "naranja" ->  this.valor = rgb(255,155,0)
            "negro" -> this.valor = BLACK
        }
        return this
    }

    /**
     * Método que retorna el valor del color.
     */
    fun getValor():Int{
        return valor
    }

    /**
     * Método que retorna el nombre del color.
     */
    fun getNombre():String{
        return nombre
    }
}