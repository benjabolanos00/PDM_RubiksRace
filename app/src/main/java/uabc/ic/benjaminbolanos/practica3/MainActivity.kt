package uabc.ic.benjaminbolanos.practica3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("EXITO", "MAIN ACTIVADO CON EXITO")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iniciarScramblerView()
        iniciarGridView()
        updateScrambler()
        updateGrid()
    }

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
                } else if(grid1 != i){
                    grid2 = i
                    Log.i("INTERCAMBIO", "INTENTO DE GRID 1: $grid1 -> GRID 2: $grid2")
                    if(gridModelo.intercambiar(grid1, grid2)){
                        updateGrid()
                        Log.i("INTERCAMBIO", "INTERCAMBIO EXITOSO")
                        if(checkWinner()){
                            Log.i("WINNER", "GANADOR ENCONTRADO")
                        }
                    } else {
                        Log.i("INTERCAMBIO", "INTERCAMBIO FALLIDO")
                    }
                    grid1 = -1
                    grid2 = -1
                }
            }
        }
        updateGridView(gridModelo.getGrid())
    }

    fun iniciarScramblerView(){
        val scramblerIDs: Array<Int> = arrayOf(R.id.scrambler_0, R.id.scrambler_1, R.id.scrambler_2,
            R.id.scrambler_3, R.id.scrambler_4,R.id.scrambler_5, R.id.scrambler_6, R.id.scrambler_7, R.id.scrambler_8)
        for(id in scramblerIDs){
            scramblerView.add(findViewById(id))
        }

        scrambleButton = findViewById(R.id.scrambleButton)
        scrambleButton.setOnClickListener{
            scramblerModel.scramble()
            updateScramblerView(scramblerModel.getCombinacion())
            Log.i("SCRAMBLER","SCRAMBLE!!")
        }


    }

    fun updateGridView(newGrid:Array<Color>){
        for(i in 0..24){
            gridView[i].setImageResource(getImgFromName(newGrid[i].getNombre()))
        }
    }

    fun updateScramblerView(newScramble:Array<Color>){
        for(i in 0..8){
            scramblerView[i].setImageResource(getImgFromName(newScramble[i].getNombre()))
        }
    }

    fun updateGrid(){
        updateGridView(gridModelo.getGrid())
    }

    fun updateScrambler(){
        updateScramblerView(scramblerModel.getCombinacion())
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

    fun checkWinner():Boolean{
        val gridCentro = gridModelo.getCentro()
        val scramblerComb = scramblerModel.getCombinacion()
        for(i in 0..8){
            if(gridCentro[i].getNombre() != scramblerComb[i].getNombre()){
                return false
            }
        }
        Toast.makeText(applicationContext,"FELICIDADES! HAS GANADO!", Toast.LENGTH_LONG).show()
        return true
    }
}