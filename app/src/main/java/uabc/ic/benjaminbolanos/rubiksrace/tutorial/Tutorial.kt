package uabc.ic.benjaminbolanos.rubiksrace.tutorial

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import uabc.ic.benjaminbolanos.rubiksrace.databinding.ActivityTutorialBinding
import uabc.ic.benjaminbolanos.rubiksrace.tutorial.main.SectionsPagerAdapter

class Tutorial : AppCompatActivity() {

    private lateinit var binding: ActivityTutorialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Crea el adaptador de secciones y lo conecta a la view para ver secciones
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
    }
}