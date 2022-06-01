package uabc.ic.benjaminbolanos.rubiksrace.util

import kotlinx.serialization.Serializable
import uabc.ic.benjaminbolanos.rubiksrace.R
import kotlin.random.Random

@Serializable
class Cuadro(var nombre: String) {
    //Determina el estilo del cuadro a mostrar
    private var estiloActual = ESTILO_NORMAL

    /**
     * Retorna el valor/id de la imagen del cuadro dependiendo del estilo deseado.
     * @param estilo Estilo con el que se mostrará el cuadro (Vea constantes de estilo)
     * @return ID de Drawable del Cuadro con color y estilo indicado.
     */
    fun getValor(estilo:Int): Int{
        estiloActual = estilo
        return when(estiloActual){
            ESTILO_NORMAL -> getNormal()
            ESTILO_DALTONICO -> getDaltonico()
            ESTILO_PASTEL -> getPastel()
            else -> 0
        }
    }

    /**
     * Retorna el valor/id del cuadro en su forma seleccionada (marco amarillo).
     * @return ID de Drawable del Cuadro en su forma seleccionado.
     */
    fun getSelected(): Int{
        return when(estiloActual){
            ESTILO_NORMAL ->{
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
            ESTILO_DALTONICO-> {
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
            ESTILO_PASTEL -> {
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

    /**
     * Método para obtener el ID del Drawable de cuadro en estilo normal dependiendo del nombre.
     * @return ID de drawable de cuadro en estilo normal.
     */
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

    /**
     * Método para obtener el ID del Drawable de cuadro en estilo para daltonicos dependiendo del nombre.
     * @return ID de drawable de cuadro en estilo para daltonicos.
     */
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

    /**
     * Método para obtener el ID del Drawable de cuadro en estilo pastel dependiendo del nombre.
     * @return ID de drawable de cuadro en estilo pastel.
     */
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

    companion object{
        /**
         * Método que retorna un cuadro aleatorio. El color negro no puede salir.
         */
        fun randomColor(): Cuadro{
            return when(Random.nextInt(0,6)){
                0 -> Cuadro("azul")
                1 -> Cuadro("rojo")
                2 -> Cuadro("verde")
                3 -> Cuadro("amarillo")
                4 -> Cuadro("morado")
                5 -> Cuadro("naranja")
                else ->{
                    Cuadro("negro")
                }
            }
        }

        // Valores de los tres diferentes estilos de cuadro
        const val ESTILO_NORMAL = 0
        const val ESTILO_PASTEL = 1
        const val ESTILO_DALTONICO = 2
    }

}