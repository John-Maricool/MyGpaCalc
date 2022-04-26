package com.example.johnbatista.mygpacalc.ui.first_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.johnbatista.mygpacalc.R
import com.example.johnbatista.mygpacalc.databinding.IntroFragmentBinding
import com.example.johnbatista.mygpacalc.ui.create_gp.FirstScreenFragmentDirections
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}