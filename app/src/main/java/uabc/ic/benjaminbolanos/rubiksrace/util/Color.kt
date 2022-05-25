package uabc.ic.benjaminbolanos.rubiksrace.util

import android.util.Log
import kotlinx.serialization.Serializable
import uabc.ic.benjaminbolanos.rubiksrace.R
import kotlin.random.Random

@Serializable
class Color(var nombre: String) {
    private var estiloActual = EstiloCuadro.NORMAL

    fun getValor(estilo:Int): Int{
        estiloActual = estilo
        return when(estiloActual){
            EstiloCuadro.NORMAL -> getNormal()
            EstiloCuadro.DALTONICO -> getDaltonico()
            EstiloCuadro.PASTEL -> getPastel()
            else -> 0
        }
    }

    fun getSelected(): Int{
        return when(estiloActual){
            EstiloCuadro.NORMAL ->{
                when(nombre){
                    "azul" -> R.drawable.cuadro_azul_selected
                    "rojo" -> R.drawable.cuadro_rojo_selected
                    "verde" -> R.drawable.cuadro_verde_selected
                    "amarillo" ->  R.drawable.cuadro_amarillo_selected
                    "morado" -> R.drawable.cuadro_morado_selected
                    "naranja" ->  R.drawable.cuadro_naranja_selected
                    else -> R.drawable.cuadro_negro_selected
                }
            }
            EstiloCuadro.DALTONICO-> {
                when(nombre){
                    "azul" -> R.drawable.cuadro_azul_daltonico_selected
                    "rojo" -> R.drawable.cuadro_rojo_daltonico_selected
                    "verde" -> R.drawable.cuadro_verde_daltonico_selected
                    "amarillo" ->  R.drawable.cuadro_amarillo_daltonico_selected
                    "morado" -> R.drawable.cuadro_morado_daltonico_selected
                    "naranja" -> R.drawable.cuadro_naranja_daltonico_selected
                    else -> R.drawable.cuadro_negro_selected
                }
            }
            EstiloCuadro.PASTEL -> {
                return when(nombre){
                    "azul" -> R.drawable.cuadro_azul_pastel_selected
                    "rojo" -> R.drawable.cuadro_rojo_pastel_selected
                    "verde" -> R.drawable.cuadro_verde_pastel_selected
                    "amarillo" ->  R.drawable.cuadro_amarillo_pastel_selected
                    "morado" -> R.drawable.cuadro_morado_pastel_selected
                    "naranja" ->  R.drawable.cuadro_naranja_pastel_selected
                    else -> R.drawable.cuadro_negro_selected
                }
            }
            else -> 0
        }
    }

    private fun getNormal(): Int{
        return when(nombre){
            "azul" -> R.drawable.cuadro_azul
            "rojo" -> R.drawable.cuadro_rojo
            "verde" -> R.drawable.cuadro_verde
            "amarillo" ->  R.drawable.cuadro_amarillo
            "morado" -> R.drawable.cuadro_morado
            "naranja" ->  R.drawable.cuadro_naranja
            else -> R.drawable.cuadro_negro
        }
    }

    private fun getDaltonico(): Int{
        return when(nombre){
            "azul" -> R.drawable.cuadro_azul_daltonico
            "rojo" -> R.drawable.cuadro_rojo_daltonico
            "verde" -> R.drawable.cuadro_verde_daltonico
            "amarillo" ->  R.drawable.cuadro_amarillo_daltonico
            "morado" -> R.drawable.cuadro_morado_daltonico
            "naranja" -> R.drawable.cuadro_naranja_daltonico
            else -> R.drawable.cuadro_negro
        }
    }

    private fun getPastel(): Int{
        return when(nombre){
            "azul" -> R.drawable.cuadro_azul_pastel
            "rojo" -> R.drawable.cuadro_rojo_pastel
            "verde" -> R.drawable.cuadro_verde_pastel
            "amarillo" ->  R.drawable.cuadro_amarillo_pastel
            "morado" -> R.drawable.cuadro_morado_pastel
            "naranja" ->  R.drawable.cuadro_naranja_pastel
            else -> R.drawable.cuadro_negro
        }
    }

    //var valor:Int = 0
    //var colorBlind: Boolean = false;
    //var nombre:String = "black"


    companion object{
        /**
         * Método que cambia los valores del color a uno aleatorio. El color negro no puede salir.
         */
        fun randomColor(): Color{
            Log.i("HS", "EQUISDE??")
            return when(Random.nextInt(0,6)){
                0 -> Color("azul")
                1 -> Color("rojo")
                2 -> Color("verde")
                3 -> Color("amarillo")
                4 -> Color("morado")
                5 -> Color("naranja")
                else ->{
                    Color("negro")
                }
            }
        }
    }

/*
    /**
    fun getSelected(): Int{
        if(daltonismo){
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
        this.daltonismo = colorBlind

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
    */*/
}