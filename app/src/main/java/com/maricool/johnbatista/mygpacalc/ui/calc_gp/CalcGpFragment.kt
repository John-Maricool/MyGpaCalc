package com.maricool.johnbatista.mygpacalc.ui.calc_gp

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.maricool.johnbatista.mygpacalc.R
import com.maricool.johnbatista.mygpacalc.data.adapter.CoursesListAdapter
import com.maricool.johnbatista.mygpacalc.data.interfaces.OnCourseItemClickListener
import com.maricool.johnbatista.mygpacalc.data.models.Course
import com.maricool.johnbatista.mygpacalc.data.models.GpResultModel
import com.maricool.johnbatista.mygpacalc.databinding.FragmentCalcGpBinding
import com.maricool.johnbatista.mygpacalc.utils.calcGp
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CalcGpFragment : Fragment(R.layout.fragment_calc_gp),
    OnCourseItemClickListener {

    private var _binding: FragmentCalcGpBinding? = null
    private val binding get() = _binding!!

    private val args: CalcGpFragmentArgs by navArgs()
    private val model: CalcGpViewModel by viewModels()

    @Inject
    lateinit var adapter: CoursesListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCalcGpBinding.bind(view)
        setHasOptionsMenu(true)
        model.details = args.details
        model.getAllCourses()
        binding.adapter = adapter
        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.viewModel = model
        binding.executePendingBindings()
        adapter.setOnCourseItemClickListener(this)
        observeLiveData()
    }

    private fun observeLiveData() {
        model.add.observe(viewLifecycleOwner) {
            if (it) {
                val action = CalcGpFragmentDirections.actionCalcGpFragmentToAddCourseFragment(
                    details = args.details,
                    course = null
                )
                findNavController().navigate(action)
            }
        }
        model.delete.observe(viewLifecycleOwner) {
            if (it != null && it)
                model.getAllCourses()
        }
        model.calc.observe(viewLifecycleOwner) {
            if (it) {
                binding.calculate.isEnabled = false
                val fac = adapter.getMultiFactor()
                val totUl = adapter.getTotalUnitLoads()
                val gp = calcGp(totUl, fac)
                val res = GpResultModel(
                    name = args.details,
                    gp = gp.toString(),
                    totalUl = totUl.toInt(),
                    numOfCourses = adapter.itemCount
                )
                model.addToResult(res)
            }
        }
        model.viewResult.observe(viewLifecycleOwner) {
            if (it != null && it.numOfCourses != 0) {
                binding.calculate.isEnabled = true
                val action = CalcGpFragmentDirections.actionCalcGpFragmentToShowGPFragment(it)
                findNavController().navigate(action)
            } else {
                return@observe
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_delete, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete_all -> {
                model.deleteAllCourse()
            }
            R.id.new_calc -> {
                findNavController().navigate(R.id.firstScreenFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCourseItemClick(course: Course) {
        val action = CalcGpFragmentDirections.actionCalcGpFragmentToAddCourseFragment(
            details = args.details,
            course = course
        )
        findNavController().navigate(action)
    }

    override fun onCourseLongClick(course: Course): Boolean {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete")
            .setMessage("Do you want to delete selected course details?")
            .setPositiveButton("Delete") { dialog, _ ->
                model.deleteCourse(course)
                dialog.dismiss()
            }.setNegativeButton("Exit") { dialog, _ ->
                dialog.dismiss()
            }.show()
        return true
    }
}