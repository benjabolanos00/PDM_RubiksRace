package uabc.ic.benjaminbolanos.rubiksrace

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import uabc.ic.benjaminbolanos.rubiksrace.highscore_database.ext

class Winner : AppCompatActivity() {
    private lateinit var newRecordText:TextView
    private lateinit var movesText: TextView
    private lateinit var timeText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_winner)
        initViews()
        setInfo()
    }

    private fun initViews(){
        newRecordText = findViewById(R.id.winner_newrecord_text)
        movesText = findViewById(R.id.winner_moves_text)
        timeText = findViewById(R.id.winner_time_text)
    }

    private fun setInfo(){
        val bundle = intent.extras
        if(bundle != null){
            if(!bundle.getBoolean("record"))
                newRecordText.visibility = View.INVISIBLE
            val movsString = StringBuffer(bundle.get("movs") as Int)
            movesText.text = movsString.append(" Movimientos")
            timeText.text = bundle.get("tiempo") as String
        }

        ////StringBuilder(ext.highscores[ext.highscores.size-1].movimientos)
        //
    }
}