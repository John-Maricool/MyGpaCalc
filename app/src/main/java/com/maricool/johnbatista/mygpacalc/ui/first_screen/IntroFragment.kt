package com.maricool.johnbatista.mygpacalc.ui.first_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.maricool.johnbatista.mygpacalc.R
import com.maricool.johnbatista.mygpacalc.databinding.IntroFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IntroFragment : Fragment(R.layout.intro_fragment) {

    private var _binding: IntroFragmentBinding? = null
    private val binding: IntroFragmentBinding get() = _binding!!
    private val viewModel: IntroViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = IntroFragmentBinding.bind(view)
        binding.viewModel = viewModel
        binding.executePendingBindings()
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.navigateToStart.observe(viewLifecycleOwner) {
            if (it != null) {
                findNavController().navigate(R.id.firstScreenFragment)
            }
        }
        viewModel.navigateToPrevious.observe(viewLifecycleOwner) {
            if (it != null) {
                val action =
                    IntroFragmentDirections.actionIntroFragmentToPreviousGpsFragment()
                findNavController().navigate(action)
            }
        }
        viewModel.navigateToAbout.observe(viewLifecycleOwner) {
            if (it != null) {
                val action =
                    IntroFragmentDirections.actionIntroFragmentToAboutFragment()
                findNavController().navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

