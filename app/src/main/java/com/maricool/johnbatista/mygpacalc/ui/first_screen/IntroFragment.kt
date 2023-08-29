package com.maricool.johnbatista.mygpacalc.ui.first_screen

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.maricool.johnbatista.mygpacalc.R
import com.maricool.johnbatista.mygpacalc.data.adapter.PreviousGpListAdapter
import com.maricool.johnbatista.mygpacalc.data.interfaces.OnItemClickListener
import com.maricool.johnbatista.mygpacalc.data.prefs.SharedPrefs
import com.maricool.johnbatista.mygpacalc.databinding.IntroFragmentBinding
import com.maricool.johnbatista.mygpacalc.local_db.CourseDao
import com.maricool.johnbatista.mygpacalc.ui.MainActivity
import com.maricool.johnbatista.mygpacalc.ui.previous_gps.PreviousGpsFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class IntroFragment : Fragment(R.layout.intro_fragment), OnItemClickListener {

    private var _binding: IntroFragmentBinding? = null
    private val binding: IntroFragmentBinding get() = _binding!!

    private val viewModel: IntroViewModel by viewModels()
    @Inject
    lateinit var adapter: PreviousGpListAdapter

    @Inject
    lateinit var dao: CourseDao


    @Inject
    lateinit var prefs: SharedPrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    (activity as MainActivity).finish()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            callback
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = IntroFragmentBinding.bind(view)
        binding.adapter = adapter
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        adapter.setOnItemClickListener(this)

        binding.viewAll.setOnClickListener {
            val action = IntroFragmentDirections.actionIntroFragmentToPreviousGpsFragment()
            findNavController().navigate(action)
        }
        binding.calculate.setOnClickListener{
            val action = IntroFragmentDirections.actionIntroFragmentToFirstScreenFragment()
            findNavController().navigate(action)
        }
        binding.startCalculating.setOnClickListener{
            val action = IntroFragmentDirections.actionIntroFragmentToFirstScreenFragment()
            findNavController().navigate(action)
        }
        binding.settings.setOnClickListener{
            showPopupMenu(it)
        }
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(requireActivity(), view)
        popupMenu.menuInflater.inflate(R.menu.menu_settings, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.action_about -> {
                    val action = IntroFragmentDirections.actionIntroFragmentToAboutFragment()
                    findNavController().navigate(action)
                    true
                }
                R.id.action_reset -> {
                    prefs.resetData()
                    lifecycleScope.launch{
                        dao.deleteAllGpWithCascade()
                        navigateToSplashScreen()
                    }
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }

    private fun navigateToSplashScreen() {
        findNavController().navigate(R.id.splashScreenFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(id: String) {
        val action = IntroFragmentDirections.actionIntroFragmentToCalcGpFragment(id)
        findNavController().navigate(action)
    }

    override fun onItemLongClick(details: String): Boolean {
     return false
    }
}

