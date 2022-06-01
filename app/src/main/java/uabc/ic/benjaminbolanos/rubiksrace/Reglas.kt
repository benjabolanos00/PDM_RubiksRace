package uabc.ic.benjaminbolanos.rubiksrace

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import androidx.core.view.size

/**
 * Actividad que muestra las reglas del juego.
 */
class Reglas : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reglas_view)
        obtenerColoresConfig()
    }

    /**
     * Método que obtiene la configuracion de los colores y manda a cambiar los colores de la
     * actividad
     */
    fun obtenerColoresConfig(){
        val colores = getSharedPreferences("colores",Context.MODE_PRIVATE)
        if(colores.contains("color_primario")){
            val colorPrimario = colores.getInt("color_primario", 0)
            val colorSecundario = colores.getInt("color_secundario",0)
            setColores(colorPrimario, colorSecundario)
        }

    }

    /**
     * Método que cambia los colores del fondo y textos
     */
    fun setColores(colorPrimario: Int, colorSecundario: Int){
        val layout = findViewById<ConstraintLayout>(R.id.reglas_layout)
        for(i in 0 until layout.size){
            (layout[i] as TextView).apply {
                setTextColor(colorPrimario)
            }
        }
        layout.setBackgroundColor(colorSecundario)
    }
}