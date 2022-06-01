package uabc.ic.benjaminbolanos.rubiksrace

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import androidx.fragment.app.DialogFragment
import uabc.ic.benjaminbolanos.rubiksrace.util.Cuadro
import java.util.*

/**
 * DialogFragment para representar como carga un juego. Contiene una pequeña animación de como se
 * hace scramble varias veces.
 */
class LoadingGame : DialogFragment() {

    lateinit var scramblerGrid: GridLayout
    lateinit var loadingScreenLayout:ConstraintLayout
    private lateinit var loadingScreenTitle: TextView

    private lateinit var combinacion: Array<Cuadro>
    private var estilo: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullscreenDialogTheme)
        estilo = getEstilo()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla el layout y obtiene los views para cambiar los colores
        val view = inflater.inflate(R.layout.fragment_loading_game, container, false)
        scramblerGrid = view.findViewById(R.id.loading_screen_scrambler)
        loadingScreenLayout = view.findViewById(R.id.loading_screen_layout)
        loadingScreenTitle = view.findViewById(R.id.loading_screen_title)
        setColores()
        return view
    }

    companion object {
        /**
         * Crea una nueva instancia recibiendo la combinacion del Scramble.
         */
        @JvmStatic
        fun newInstance(combinacion: Array<Cuadro>) =
            LoadingGame().apply {
                this.combinacion = combinacion
            }
    }

    /**
     * Timer que dura 3000milisegundos, cada 300milisegundos crea una combinacion falsa.
     * Al final muestra la combinación real del scrambler y desvanece este dialogo.
     */
    fun countDown(){
       object : CountDownTimer(3000, 300){
            override fun onTick(p0: Long) {
                setScrambler(Array(9){Cuadro.randomColor()})
            }

            override fun onFinish() {
                setScrambler(combinacion)
                esconder()
            }
        }.start()
    }

    /**
     * Realiza una animación de como el dialogo se desvanece y luego cierra el dialogo
     */
    fun esconder(){
        loadingScreenLayout.animate()
            .alpha(0f)
            .setDuration(500)
            .setListener(object :AnimatorListenerAdapter(){
                override fun onAnimationEnd(animation: Animator?) {
                    loadingScreenLayout.visibility = View.GONE
                    dismiss()
                }
            })
    }

    /**
     * Obtiene el estilo de cuadros para mostrarlos en la animacion
     */
    private fun getEstilo(): Int? {
        val config = context?.getSharedPreferences("config", Context.MODE_PRIVATE)
        return config?.getInt("estilo", 0)
    }

    /**
     * Obtiene los colores de fondo y textos para mostrarlo
     */
    private fun setColores(){
        val preferencias = context?.getSharedPreferences("colores", Context.MODE_PRIVATE)
        if (preferencias != null) {
            if(preferencias.contains("color_secundario")) {
                val colorSecundario = preferencias.getInt("color_secundario", R.color.fondo_turquesa)
                val colorPrimario = preferencias.getInt("color_primario", R.color.white)
                cambiarColores(colorPrimario, colorSecundario)
            }
        }
    }

    /**
     * Cambia color de fonod y textos
     */
    private fun cambiarColores(colorPrimario: Int, colorSecundario: Int){
        loadingScreenLayout.setBackgroundColor(colorSecundario)
        loadingScreenTitle.setTextColor(colorPrimario)

    }

    /**
     * Cambia los colores del scrambler mostrado.
     */
    fun setScrambler(comb: Array<Cuadro>){
        for(i in 0 until 9){
            estilo?.let { comb[i].getValor(it) }
                ?.let { (scramblerGrid[i] as ImageView).setImageResource(it) }
        }
    }
}