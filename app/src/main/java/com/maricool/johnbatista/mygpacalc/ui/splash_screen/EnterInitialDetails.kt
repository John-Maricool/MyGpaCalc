package com.maricool.johnbatista.mygpacalc.ui.splash_screen

import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.maricool.johnbatista.mygpacalc.R
import com.maricool.johnbatista.mygpacalc.data.prefs.SharedPrefs
import com.maricool.johnbatista.mygpacalc.databinding.FragmentEnterInitialDetailsBinding
import com.maricool.johnbatista.mygpacalc.ui.MainActivity
import com.maricool.johnbatista.mygpacalc.ui.add_course.AddCourseFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EnterInitialDetails : Fragment(R.layout.fragment_enter_initial_details) {

    private var _binding: FragmentEnterInitialDetailsBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentEnterInitialDetailsBinding.bind(view)

        binding.submitButton.setOnClickListener {
            val name = binding.name.text.toString()

            if (name.isBlank()) {
                showToast("Name field cannot be empty")
                return@setOnClickListener
            }else{
                prefs.setUserName(name)
                prefs.setFirstTimeStatus(false)
                val action = EnterInitialDetailsDirections.actionEnterInitialDetailsToIntroFragment()
                findNavController().navigate(action)
            }
        }

        binding.radioGroup.setOnCheckedChangeListener { group: RadioGroup, checkedId: Int ->
            val selectedScaleIndex = group.indexOfChild(group.findViewById(checkedId))
            if (selectedScaleIndex == 0){
                prefs.setGradePoint(5)
            }else{
                prefs.setGradePoint(4)
            }

            showToast("Selected Scale Index: $selectedScaleIndex")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
