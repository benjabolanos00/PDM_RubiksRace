package uabc.ic.benjaminbolanos.practica3

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View

class MainActivity : AppCompatActivity() {
    private lateinit var startBtn: View
    private val scrambler = Scrambler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startBtn = findViewById(R.id.button)
        startBtn.setBackgroundColor(Color.TRANSPARENT)
        startBtn.setOnClickListener{
            intentScrambler(startBtn)
        }
    }

    fun intentScrambler(view: View){
        val intent = Intent(this, scrambler.javaClass)

        startActivity(intent)
    }
}