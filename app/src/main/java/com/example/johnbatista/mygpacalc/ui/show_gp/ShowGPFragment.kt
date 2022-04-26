package com.example.johnbatista.mygpacalc.ui.show_gp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.johnbatista.mygpacalc.R
import com.example.johnbatista.mygpacalc.databinding.FragmentShowGpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowGPFragment : DialogFragment(R.layout.fragment_show_gp) {

    private var _binding: FragmentShowGpBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ShowGPViewModel by viewModels()

    private val args: ShowGPFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentShowGpBinding.bind(view)
        isCancelable = false

        binding.result = args.result
        binding.viewModel = viewModel

        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.executePendingBindings()
        observeLiveData()
    }


    private fun observeLiveData() {
        binding.close.setOnClickListener {
           // isCancelable = true
            dismiss()
        }
        viewModel.navToAllGp.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(R.id.previousGpsFragment)
            }
        }
        viewModel.startNewCalc.observe(viewLifecycleOwner) {
            if (it)
                findNavController().navigate(R.id.firstScreenFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}