package com.example.johnbatista.mygpacalc.ui.add_course

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.johnbatista.mygpacalc.R
import com.example.johnbatista.mygpacalc.databinding.AddCourseFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCourseFragment : Fragment(R.layout.add_course_fragment) {

    private var _binding: AddCourseFragmentBinding? = null
    private val binding: AddCourseFragmentBinding get() = _binding!!

    private val args: AddCourseFragmentArgs by navArgs()
    private val viewModel: AddCourseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val action =
                        AddCourseFragmentDirections.actionAddCourseFragmentToCalcGpFragment(args.details)
                    findNavController().navigate(action)

                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            callback
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = AddCourseFragmentBinding.bind(view)
        viewModel.course = args.course
        viewModel.details = args.details
        if (args.course != null) {
            viewModel.courseText.value = args.course!!.name
        } else
            viewModel.courseText.value = "Course"
        binding.viewModel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.executePendingBindings()

        viewModel.added.observe(viewLifecycleOwner) {
            if (it != null)
                Toast.makeText(activity, "Added", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}