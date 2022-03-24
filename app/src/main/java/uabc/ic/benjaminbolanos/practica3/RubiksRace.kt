package uabc.ic.benjaminbolanos.practica3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class RubiksRace : AppCompatActivity() {

    //ScramblerModel
    private var scramblerModel = ScramblerModelo()

    //ScramblerView
    private var scramblerView: ArrayList<ImageView> = ArrayList(9)
    private lateinit var scrambleButton: Button

    //GridModel
    private var gridModelo: GridModelo = GridModelo()

    //GridView
    private var gridView: ArrayList<ImageView> = ArrayList(25)
    private var grid1:Int = -1
    private var grid2:Int = -1

    //Cronometro
    val cronometro = Cronometro()

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("EXITO", "MAIN ACTIVADO CON EXITO")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iniciarScramblerView()
        iniciarGridView()
        updateScrambler()
        updateGrid()
        cronometro.iniciar()
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
            updateScramblerView(scramblerModel.getCombinacion())
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
            gridView[i].setImageResource(getImgFromName(newGrid[i].getNombre()))
        }
    }

    /**
     * Método que recibe un array de objetos tipo Color y dependiendo del nombre del color, cambia
     * las imagenes de los ImageView del Scrambler.
     */
    fun updateScramblerView(newScramble:Array<Color>){
        for(i in 0..8){
            scramblerView[i].setImageResource(getImgFromName(newScramble[i].getNombre()))
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
     * Método que recibe el nombre de un color y retorna la imagen del cuadro de ese color.
     * Si no recibe un nombre de los 6 colores disponibles, retorna el cuadro negro.
     */
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

    /**
     * Método que recibe el nombre de un color y retorna la imagen del cuadro seleccionado de ese color.
     * Si no recibe un nombre de los 6 colores disponibles, retorna el cuadro seleccionado negro.
     */
    fun selectGrid(i:Int){
        val id = when(gridModelo.getGrid()[i].getNombre()){
            "azul" -> R.drawable.cuadro_azul_selected
            "rojo" -> R.drawable.cuadro_rojo_selected
            "verde" -> R.drawable.cuadro_verde_selected
            "amarillo" -> R.drawable.cuadro_amarillo_selected
            "blanco" -> R.drawable.cuadro_blanco_selected
            "naranja" -> R.drawable.cuadro_naranja_selected
            else -> R.drawable.cuadro_negro_selected
        }
        gridView[i].setImageResource(id)
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
            if(gridCentro[i].getNombre() != scramblerComb[i].getNombre()){
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
            ext.highscores.add(Highscore(cronometro.getTiempo()))
            if(ext.highscores[ext.highscores.size-1].isHighestScore()){
                Toast.makeText(applicationContext,"FELICIDADES! HAS GANADO EN $cronometro! NUEVO RECORD!", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext,"FELICIDADES! HAS GANADO EN $cronometro!", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(applicationContext,"Vaya, parece que no has ganado aun :(", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Método para el boton Nuevo Juego. Reinicia el cronometro, el scrambler y el grid.
     */
    fun crearNuevoJuego(view: View){
        cronometro.parar()
        scramblerModel.scramble()
        updateScrambler()
        gridModelo.crearGrid()
        updateGrid()
        cronometro.iniciar()
    }
}