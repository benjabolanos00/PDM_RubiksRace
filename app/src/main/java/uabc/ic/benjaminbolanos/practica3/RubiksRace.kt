package uabc.ic.benjaminbolanos.practica3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import uabc.ic.benjaminbolanos.practica3.grid.GridModelo
import uabc.ic.benjaminbolanos.practica3.highscores.Highscores
import uabc.ic.benjaminbolanos.practica3.scrambler.ScramblerModelo
import uabc.ic.benjaminbolanos.practica3.util.Color
import uabc.ic.benjaminbolanos.practica3.util.Cronometro
import uabc.ic.benjaminbolanos.practica3.highscores.Highscore
import uabc.ic.benjaminbolanos.practica3.highscores.ext

class RubiksRace() : AppCompatActivity() {

    //ScramblerModel
    private lateinit var scramblerModel: ScramblerModelo

    //ScramblerView
    private var scramblerView: ArrayList<ImageView> = ArrayList(9)
    private lateinit var scrambleButton: Button

    //GridModel
    private lateinit var gridModelo: GridModelo

    //GridView
    private var gridView: ArrayList<ImageView> = ArrayList(25)
    private var grid1:Int = -1
    private var grid2:Int = -1

    //Cronometro
    private val cronometro = Cronometro()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rubiksrace)
        setModoDaltonico()
        iniciarScramblerView()
        iniciarGridView()
        updateScrambler()
        updateGrid()
        cronometro.iniciar()
    }

    fun setModoDaltonico(){
        val bundle = intent.extras
        var modoDaltonico = false
        if(bundle != null){
            modoDaltonico = bundle.getBoolean("ColorBlindMode")
        }
        scramblerModel = ScramblerModelo(modoDaltonico)
        gridModelo = GridModelo(modoDaltonico)
    }

    /**
     * Método que inicializa todos los Views que tengan que ver con el Grid del jugador. También,
     * crea los onClickListeners de las ImageViews que corresponden a los cuadros del grid.
     */
    fun iniciarGridView(){
        val gridIDs: Array<Int> = arrayOf(R.id.grid_0,R.id.grid_1,R.id.grid_2,R.id.grid_3,R.id.grid_4,
            R.id.grid_5,R.id.grid_6,R.id.grid_7,R.id.grid_8,R.id.grid_9,
            R.id.grid_10, R.id.grid_11,R.id.grid_12,R.id.grid_13,R.id.grid_14,
            R.id.grid_15,R.id.grid_16,R.id.grid_17,R.id.grid_18,R.id.grid_19,
            R.id.grid_20,R.id.grid_21,R.id.grid_22,R.id.grid_23,R.id.grid_24)

        for((i,id) in gridIDs.withIndex()){
            gridView.add(findViewById(id))
            gridView[i].setOnClickListener {
                if(grid1 == -1){
                    grid1 = i
                    selectGrid(i)
                } else if(grid1 != i){
                    grid2 = i
                    realizarIntercambio()
                    grid1 = -1
                    grid2 = -1
                }
            }
        }
        updateGridView(gridModelo.getGrid())
    }

    /**
     * Método que inicializa todos los Views que tengan que ver con el Scrambler. También, crea
     * el onClickListener del boton para hacer Scramble. Al hacer Scramble, el cronómetro se reinicia.
     */
    fun iniciarScramblerView(){
        val scramblerIDs: Array<Int> = arrayOf(R.id.scrambler_0, R.id.scrambler_1, R.id.scrambler_2,
            R.id.scrambler_3, R.id.scrambler_4,R.id.scrambler_5, R.id.scrambler_6, R.id.scrambler_7, R.id.scrambler_8)
        for(id in scramblerIDs){
            scramblerView.add(findViewById(id))
        }

        scrambleButton = findViewById(R.id.scrambleButton)
        scrambleButton.setOnClickListener{
            cronometro.parar()
            scramblerModel.scramble()
            updateScrambler()
            Log.i("SCRAMBLER","SCRAMBLE!!")
            cronometro.iniciar()
        }
    }

    /**
     * Método que recibe un array de objetos tipo Color y dependiendo del nombre del color, cambia
     * las imagenes de los ImageView del Grid del jugador.
     */
    fun updateGridView(newGrid:Array<Color>){
        for(i in 0..24){
            gridView[i].setImageResource(newGrid[i].valor)
        }
    }

    /**
     * Método que recibe un array de objetos tipo Color y dependiendo del nombre del color, cambia
     * las imagenes de los ImageView del Scrambler.
     */
    fun updateScramblerView(newScramble:Array<Color>){
        for(i in 0..8){
            scramblerView[i].setImageResource(newScramble[i].valor)
        }
    }

    /**
     * Método que toma el grid de GridModelo y lo manda al método updateGridView para actualizar
     * el grid del jugador visualmente.
     */
    fun updateGrid(){
        updateGridView(gridModelo.getGrid())
    }

    /**
     * Método que toma la combinacion de ScramblerModelo y lo manda al método updateScramblerView para actualizar
     * el scrambler visualmente.
     */
    fun updateScrambler(){
        updateScramblerView(scramblerModel.getCombinacion())
    }

    /**
     * Método que recibe el nombre de un color y retorna la imagen del cuadro seleccionado de ese color.
     * Si no recibe un nombre de los 6 colores disponibles, retorna el cuadro seleccionado negro.
     */
    fun selectGrid(i:Int){
        gridView[i].setImageResource(gridModelo.selectCuadro(i))
    }

    /**
     * Método para realizar un intercambio de dos cuadros del grid. Toma los valores de grid1 y grid2
     * y los manda a GridModelo para realizar el intercambio, despues, actualiza el grid.
     */
    fun realizarIntercambio(){
        Log.i("INTERCAMBIO", "INTENTO DE GRID 1: $grid1 -> GRID 2: $grid2")
        if(gridModelo.intercambiar(grid1, grid2)){
            Log.i("INTERCAMBIO", "INTERCAMBIO EXITOSO")
        } else {
            Log.i("INTERCAMBIO", "INTERCAMBIO FALLIDO")
        }
        updateGrid()
    }

    /**
     * Método que compara el centro del grid con la combinación del Scrambler. Si los 9 colores son
     * iguales, significa que hay ganador.
     */
    fun checkWinner():Boolean{
        val gridCentro = gridModelo.getCentro()
        val scramblerComb = scramblerModel.getCombinacion()
        for(i in 0..8){
            if(gridCentro[i].nombre != scramblerComb[i].nombre){
                return false
            }
        }
        return true
    }

    /**
     * Método para el boton Slam!. Si hay ganador, para el cronometro, crea el highscore y busca
     * si es nuevo record. En ese caso, muestra un toast mostrando que es nuevo record.
     */
    fun slamFrame(view:View){
        if(checkWinner()){
            cronometro.parar()
            ext.highscores.add(Highscore(cronometro.getTiempo(), gridModelo.movimientos, scramblerModel.getCombinacion()))
            val intent = Intent(this, Winner::class.java)
            if(ext.highscores[ext.highscores.size-1].isHighestScore()){
                intent.putExtra("NewRecord", true)
                Toast.makeText(applicationContext,"FELICIDADES! HAS GANADO EN $cronometro! NUEVO RECORD!", Toast.LENGTH_LONG).show()
            } else {
                intent.putExtra("NewRecord", false)
                Toast.makeText(applicationContext,"FELICIDADES! HAS GANADO EN $cronometro!", Toast.LENGTH_LONG).show()
            }
            finish()
            startActivity(intent)
        } else {
            Toast.makeText(applicationContext,"Vaya, parece que no has ganado aun :(", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Método para el boton Nuevo Juego. Reinicia el cronometro, el scrambler y el grid.
     */
    fun crearNuevoJuego(view: View?){
        cronometro.parar()
        scramblerModel.scramble()
        updateScrambler()
        gridModelo.crearGrid()
        updateGrid()
        cronometro.iniciar()
    }

    fun irHaciaHighscores(view: View){
        val intent = Intent(this, Highscores::class.java)
        startActivity(intent)
    }
}