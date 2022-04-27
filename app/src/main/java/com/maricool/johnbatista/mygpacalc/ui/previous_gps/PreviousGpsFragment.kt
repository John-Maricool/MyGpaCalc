package com.maricool.johnbatista.mygpacalc.ui.previous_gps

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.maricool.johnbatista.mygpacalc.R
import com.maricool.johnbatista.mygpacalc.data.adapter.PreviousGpListAdapter
import com.maricool.johnbatista.mygpacalc.data.interfaces.OnItemClickListener
import com.maricool.johnbatista.mygpacalc.databinding.PreviousGpsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PreviousGpsFragment : Fragment(R.layout.previous_gps_fragment), OnItemClickListener {

    private var _binding: PreviousGpsFragmentBinding? = null
    private val binding: PreviousGpsFragmentBinding get() = _binding!!
    private val viewModel: PreviousGpsViewModel by viewModels()

    @Inject
    lateinit var adapter: PreviousGpListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val action =
                        PreviousGpsFragmentDirections.actionPreviousGpsFragmentToIntroFragment()
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
        _binding = PreviousGpsFragmentBinding.bind(view)
        setHasOptionsMenu(true)
        binding.adapter = adapter
        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.viewModel = viewModel
        binding.executePendingBindings()
        adapter.setOnItemClickListener(this)

        viewModel.delete.observe(viewLifecycleOwner) {
            if (it)
                viewModel.getSavedGps()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(id: String) {
        val action = PreviousGpsFragmentDirections.actionPreviousGpsFragmentToCalcGpFragment(id)
        findNavController().navigate(action)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete_all -> {
                viewModel.deleteAllGp()
            }
            R.id.new_calc -> {
                findNavController().navigate(R.id.firstScreenFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemLongClick(details: String): Boolean {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete")
            .setMessage("Do you want to delete GP results")
            .setPositiveButton("Delete") { dialog, _ ->
                viewModel.deleteGp(details)
                dialog.dismiss()
            }.setNegativeButton("Exit") { dialog, _ ->
                dialog.dismiss()
            }.show()
        return true
    }
}