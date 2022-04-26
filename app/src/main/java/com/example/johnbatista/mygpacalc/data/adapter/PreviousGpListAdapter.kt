package com.example.johnbatista.mygpacalc.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.johnbatista.mygpacalc.R
import com.example.johnbatista.mygpacalc.data.interfaces.OnItemClickListener
import com.example.johnbatista.mygpacalc.data.models.GpResultModel
import com.example.johnbatista.mygpacalc.databinding.PreviousGpListItemBinding
import javax.inject.Inject

class PreviousGpListAdapter
@Inject constructor() : RecyclerView.Adapter<PreviousGpListAdapter.PreviousGpListViewHolder>() {

    private var prevGps = listOf<GpResultModel>()
    lateinit var listener: OnItemClickListener

    inner class PreviousGpListViewHolder(private val binding: PreviousGpListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(gp: GpResultModel) {
            binding.gpResult = gp
            binding.listener = listener
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreviousGpListViewHolder {
        val binding = DataBindingUtil.inflate<PreviousGpListItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.previous_gp_list_item,
            parent,
            false
        )
        return PreviousGpListViewHolder(binding)
    }

    fun setOnItemClickListener(mListener: OnItemClickListener) {
        listener = mListener
    }

    override fun onBindViewHolder(holder: PreviousGpListViewHolder, position: Int) {
        holder.bind(prevGps[position])
    }

    override fun getItemCount(): Int {
        return prevGps.size
    }

    fun setPrevList(newList: List<GpResultModel>) {
        prevGps = newList
        notifyDataSetChanged()
    }
}








