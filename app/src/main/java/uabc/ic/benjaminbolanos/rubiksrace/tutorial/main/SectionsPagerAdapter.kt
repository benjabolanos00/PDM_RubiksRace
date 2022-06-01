package uabc.ic.benjaminbolanos.rubiksrace.tutorial.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


/**
 * Adaptador que retorna un fragmento dependiendo a una de las secciones
 *
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    /**
     * Retorna un fragmento para instanciarlo, el fragmento es una de las Secciones
     */
    override fun getItem(position: Int): Fragment {
        return PlaceholderFragment.newInstance(position + 1)
    }

    /**
     * Retorna la cantidad de paginas que hay en la Actividad de Tutorial
     */
    override fun getCount(): Int = 3
}