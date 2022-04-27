package com.maricool.johnbatista.mygpacalc.ui.create_gp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.maricool.johnbatista.mygpacalc.R
import com.maricool.johnbatista.mygpacalc.databinding.FirstScreenFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstScreenFragment : DialogFragment(R.layout.first_screen_fragment) {

    private var _binding: FirstScreenFragmentBinding? = null
    private val binding: FirstScreenFragmentBinding get() = _binding!!
    private val viewModel: FirstScreenViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FirstScreenFragmentBinding.bind(view)
        binding.viewModel = viewModel
        binding.executePendingBindings()

        viewModel.navigateToStart.observe(viewLifecycleOwner) {
            if (it != null) {
                val action =
                    FirstScreenFragmentDirections.actionFirstScreenFragmentToCalcGpFragment(it)
                findNavController().navigate(action)
                dismiss()
            } else {
                Toast.makeText(activity, "You have used this name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}