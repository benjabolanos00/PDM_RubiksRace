package uabc.ic.benjaminbolanos.rubiksrace.util

import uabc.ic.benjaminbolanos.rubiksrace.R
import kotlin.random.Random

class Color() {
    var valor:Int = 0
    private var colorBlind: Boolean = false;
    var nombre:String = "black"

    /**
     * Constructor que inicializa el color con un color aleatorio.
     */
    init {
        randomColor()
    }

    /**
     * Constructor que inicializa el color a partir de su nombre, si esta seleccionado, y si es para daltonicos
     */
    constructor(name: String, colorBlind: Boolean) : this() {
        setColor(name, colorBlind)
    }

    /**
     * Método que cambia los valores del color a uno aleatorio. El color negro no puede salir.
     */
    private fun randomColor(){
        when(Random.nextInt(0,6)){
            0 -> setColor("azul", colorBlind = false)
            1 -> setColor("rojo", colorBlind = false)
            2 -> setColor("verde", colorBlind = false)
            3 -> setColor("amarillo", colorBlind = false)
            4 -> setColor("blanco", colorBlind = false)
            5 -> setColor("naranja", colorBlind = false)
        }
    }

    fun getSelected(): Int{
        if(colorBlind){
            return when(nombre){
                "azul" -> R.drawable.cuadro_azul_daltonico_selected
                "rojo" -> R.drawable.cuadro_rojo_daltonico_selected
                "verde" -> R.drawable.cuadro_verde_daltonico_selected
                "amarillo" -> R.drawable.cuadro_amarillo_daltonico_selected
                "blanco" -> R.drawable.cuadro_blanco_daltonico_selected
                "naranja" -> R.drawable.cuadro_naranja_daltonico_selected
                else -> R.drawable.cuadro_negro_selected
            }
        } else {
            return when(nombre){
                "azul" -> R.drawable.cuadro_azul_selected
                "rojo" -> R.drawable.cuadro_rojo_selected
                "verde" -> R.drawable.cuadro_verde_selected
                "amarillo" -> R.drawable.cuadro_amarillo_selected
                "blanco" -> R.drawable.cuadro_blanco_selected
                "naranja" -> R.drawable.cuadro_naranja_selected
                else -> R.drawable.cuadro_negro_selected
            }
        }
    }

    private fun setColor(name: String, colorBlind: Boolean){
        this.nombre = name
        this.colorBlind = colorBlind

        if(colorBlind){
            when(name){
                "azul" -> this.valor = R.drawable.cuadro_azul_daltonico
                "rojo" -> this.valor = R.drawable.cuadro_rojo_daltonico
                "verde" -> this.valor = R.drawable.cuadro_verde_daltonico
                "amarillo" ->  this.valor = R.drawable.cuadro_amarillo_daltonico
                "blanco" -> this.valor = R.drawable.cuadro_blanco_daltonico
                "naranja" ->  this.valor = R.drawable.cuadro_naranja_daltonico
                else -> this.valor = R.drawable.cuadro_negro
            }
        } else {
            when(name){
                "azul" -> this.valor = R.drawable.cuadro_azul
                "rojo" -> this.valor = R.drawable.cuadro_rojo
                "verde" -> this.valor = R.drawable.cuadro_verde
                "amarillo" ->  this.valor = R.drawable.cuadro_amarillo
                "blanco" -> this.valor = R.drawable.cuadro_blanco
                "naranja" ->  this.valor = R.drawable.cuadro_naranja
                else -> this.valor = R.drawable.cuadro_negro
            }
        }
    }
}