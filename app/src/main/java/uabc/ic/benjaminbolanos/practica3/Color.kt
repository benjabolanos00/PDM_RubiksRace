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
                this.valor = R.drawable.cuadro_azul
            }
            1 -> {
                this.nombre = "rojo"
                this.valor = R.drawable.cuadro_rojo
            }
            2 -> {
                this.nombre = "verde"
                this.valor = R.drawable.cuadro_verde
            }
            3 -> {
                this.nombre = "amarillo"
                this.valor = R.drawable.cuadro_amarillo
            }
            4 -> {
                this.nombre = "blanco"
                this.valor = R.drawable.cuadro_blanco
            }
            5 -> {
                this.nombre = "naranja"
                this.valor = R.drawable.cuadro_naranja
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
            "azul" -> this.valor = R.drawable.cuadro_azul
            "rojo" -> this.valor = R.drawable.cuadro_rojo
            "verde" -> this.valor = R.drawable.cuadro_verde
            "amarillo" ->  this.valor = R.drawable.cuadro_amarillo
            "blanco" -> this.valor = R.drawable.cuadro_blanco
            "naranja" ->  this.valor = R.drawable.cuadro_naranja
            "negro" -> this.valor = R.drawable.cuadro_negro
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