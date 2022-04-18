package uabc.ic.benjaminbolanos.rubiksrace.util

import java.util.*
import kotlin.math.roundToInt

/**
 * Clase que modela un Cronometro para contar el tiempo.
 */
class Cronometro {
    private val timer: Timer = Timer()
    private lateinit var timerTask:TimerTask
    private var timerInicidado:Boolean = false
    private var tiempo:Double = 0.0

    /**
     * Método para parar el cronometro. No reinicia el tiempo.
     * @return True si el cronometro fue parado con exito
     */
    fun parar():Boolean{
        if(timerTask != null) {
            timerTask.cancel()
            timerInicidado = false
            return true
        }
        return false
    }

    /**
     * Método para iniciar el cronometro. Reinicia el tiempo.
     * @return True si el cronometro fue iniciado con exito.
     */
    fun iniciar():Boolean{
        if(!timerInicidado) {
            tiempo = 0.0
            timerInicidado = true
            iniciarTimer()
            return true
        }
        return false
    }

    /**
     * Método para crear un TimerTask que se ejecutará cada 1000 milisegundos. Se debe crear
     * cada que se quiera iniciar el timer/cronometro, ya que al pararlos se borra.
     */
    private fun iniciarTimer() {
        timerTask = object : TimerTask(){
            override fun run() {
                tiempo++
            }
        }
        timer.scheduleAtFixedRate(timerTask, 0, 1000)
    }

    /**
     * Método para calcular horas, minutos y segundos del tiempo. Retorna un string con el formato hh/mm/ss.
     * @return string con el tiempo en formato hh/mm/ss
     */
    override fun toString():String{
        val tiempoInt = tiempo.roundToInt()

        val segundos = tiempoInt % 86400 % 3600 % 60
        val minutos = tiempoInt % 86400 % 3600 / 60
        val horas = tiempoInt % 86400 / 3600

        return String.format("%02d",horas) + " : " + String.format("%02d",minutos) + " : " + String.format("%02d",segundos);
    }

    /**
     * Método para retornar el tiempo.
     * @return Double del tiempo.
     */
    fun getTiempo():Double{
        return tiempo
    }
}