package uabc.ic.benjaminbolanos.practica3

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView

class Scrambler : AppCompatActivity() {
    //Modelo
    private val modelo = ScramblerModelo()

    //View
    private lateinit var cuadrosScrambler:Array<Int>
    private lateinit var btnScramble:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(savedInstanceState != null){

        }

        setContentView(R.layout.scrambler_view)

        cuadrosScrambler = arrayOf(R.id.cuadro0, R.id.cuadro1, R.id.cuadro2, R.id.cuadro3,
            R.id.cuadro4, R.id.cuadro5, R.id.cuadro6, R.id.cuadro7, R.id.cuadro8)
        btnScramble = findViewById(R.id.btnScramble)
        setButtonListener()
    }

    private fun setButtonListener(){
        btnScramble.setOnClickListener{
            onBtnScrambleClick(btnScramble)
        }
    }

    fun onBtnScrambleClick(view : View){
        modelo.scramble()
        val combinacion = modelo.getCombinacion()
        for(i in 0..8){
            findViewById<ImageView>(cuadrosScrambler[i]).setImageResource(getImgFromName(combinacion[i].getNombre()))
        }
    }

    private fun getImgFromName(name:String):Int{
        return when(name){
            "azul" -> R.drawable.cuadro_azul
            "rojo" -> R.drawable.cuadro_rojo
            "verde" -> R.drawable.cuadro_verde
            "amarillo" -> R.drawable.cuadro_amarillo
            "blanco" -> R.drawable.cuadro_blanco
            "naranja" -> R.drawable.cuadro_naranja
            else -> R.drawable.cuadro_negro
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val coloresCuadros:Array<String> = Array(9){String()}
        val combinacion = modelo.getCombinacion()
        for(i in 1..8){
            coloresCuadros[i] = combinacion[i].getNombre()
        }

        super.onSaveInstanceState(outState)
    }
}