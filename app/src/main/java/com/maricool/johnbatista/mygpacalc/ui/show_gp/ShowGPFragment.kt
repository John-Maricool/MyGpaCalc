package com.maricool.johnbatista.mygpacalc.ui.show_gp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.maricool.johnbatista.mygpacalc.R
import com.maricool.johnbatista.mygpacalc.data.prefs.SharedPrefs
import com.maricool.johnbatista.mygpacalc.databinding.FragmentShowGpBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ShowGPFragment : DialogFragment(R.layout.fragment_show_gp) {

    private var _binding: FragmentShowGpBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ShowGPViewModel by viewModels()

    private val args: ShowGPFragmentArgs by navArgs()

    @Inject
    lateinit var prefs: SharedPrefs

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentShowGpBinding.bind(view)

        binding.result = args.result
        binding.viewModel = viewModel

        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.executePendingBindings()
        observeLiveData()

        val type = prefs.getGradePoint()

        val comment = args.result.getComment(type)

        binding.commen.text = comment
    }


    private fun observeLiveData() {
        binding.close.setOnClickListener {
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