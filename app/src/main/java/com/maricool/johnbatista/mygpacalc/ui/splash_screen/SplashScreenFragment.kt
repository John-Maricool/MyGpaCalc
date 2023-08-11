package com.maricool.johnbatista.mygpacalc.ui.splash_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.maricool.johnbatista.mygpacalc.R
import com.maricool.johnbatista.mygpacalc.data.prefs.SharedPrefs
import com.maricool.johnbatista.mygpacalc.databinding.FragmentSplashScreenBinding
import com.maricool.johnbatista.mygpacalc.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.Timer
import java.util.TimerTask
import javax.inject.Inject


@AndroidEntryPoint
class SplashScreenFragment : Fragment(R.layout.fragment_splash_screen) {

    private var _binding: FragmentSplashScreenBinding? = null
    val timer = Timer()

    @Inject
    lateinit var prefs: SharedPrefs

    private val binding: FragmentSplashScreenBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSplashScreenBinding.bind(view)

        timer.schedule(object : TimerTask() {
            override fun run() {
                requireActivity().runOnUiThread {
                    if (prefs.isFirstTime() == true) {
                        navigateToInitScreen()
                    } else {
                        navigateToIntroScreen()
                    }
                }
            }
        }, 3000)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        timer.cancel()
        _binding = null
    }

    private fun navigateToIntroScreen() {
        val action = SplashScreenFragmentDirections.actionSplashScreenFragmentToIntroFragment()
        findNavController().navigate(action)
    }

    private fun navigateToInitScreen() {
        val action = SplashScreenFragmentDirections.actionSplashScreenFragmentToEnterInitialDetails()
        findNavController().navigate(action)
    }
}


