package uabc.ic.benjaminbolanos.rubiksrace.tutorial.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import uabc.ic.benjaminbolanos.rubiksrace.databinding.FragmentTutorialBinding
import uabc.ic.benjaminbolanos.rubiksrace.tutorial.fragments.Page0
import uabc.ic.benjaminbolanos.rubiksrace.tutorial.fragments.Page1
import uabc.ic.benjaminbolanos.rubiksrace.tutorial.fragments.Page2

/**
 * Un fragmento que sirve como placeholder que contiene una view simple.
 *
 */
class PlaceholderFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private var _binding: FragmentTutorialBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this)[PageViewModel::class.java].apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTutorialBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {

        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Retorna una nueva instancia dependiendo del numero de seccion
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): Fragment {

            val fragment:Fragment = when(sectionNumber){
                1 -> Page0()
                2 -> Page1()
                else -> Page2()
            }

            return fragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}