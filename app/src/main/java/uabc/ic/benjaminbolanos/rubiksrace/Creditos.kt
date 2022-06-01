package uabc.ic.benjaminbolanos.rubiksrace

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import androidx.core.view.size
import org.w3c.dom.Text

/**
 * Actividad Creditos que muestra los credito de la aplicación.
 */
class Creditos : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creditos)
        obtenerColoresConfig()
    }

    /**
     * Método que obtiene los colores del fondo y texto y los manda a cambiar.
     */
    fun obtenerColoresConfig(){
        val colores = getSharedPreferences("colores", Context.MODE_PRIVATE)
        if(colores.contains("color_primario")){
            val colorPrimario = colores.getInt("color_primario", 0)
            val colorSecundario = colores.getInt("color_secundario",0)
            setColores(colorPrimario, colorSecundario)
        }

    }

    /**
     * Método que obtiene los dos colores para fondo y texto y actualiza las views.
     */
    fun setColores(colorPrimario: Int, colorSecundario: Int){
        val layout = findViewById<ConstraintLayout>(R.id.creditos_layout)
        layout.setBackgroundColor(colorSecundario)

        val textosID = arrayOf(R.id.creditos_programador_text, R.id.creditos_nombre_text, R.id.creditos_version_text,
            R.id.creditos_uabc_text, R.id.creditos_ic_text, R.id.creditos_titulo)
        for(textID in textosID){
            (findViewById<TextView>(textID)).apply {
                setTextColor(colorPrimario)
            }
        }

        val programadorImg = findViewById<ImageView>(R.id.creditos_programador_img)
        programadorImg.setBackgroundColor(colorPrimario)

        val versionText = findViewById<TextView>(R.id.creditos_version_text)
        versionText.text = "Versión: ${BuildConfig.VERSION_NAME}"

    }
}