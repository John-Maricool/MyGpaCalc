package com.example.johnbatista.mygpacalc.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.johnbatista.mygpacalc.R
import com.example.johnbatista.mygpacalc.data.interfaces.OnCourseItemClickListener
import com.example.johnbatista.mygpacalc.data.models.Course
import com.example.johnbatista.mygpacalc.databinding.GpCoursesListItemBinding
import javax.inject.Inject

class CoursesListAdapter
@Inject constructor() : RecyclerView.Adapter<CoursesListAdapter.CourseListViewHolder>() {

    private var courses = listOf<Course>()
    lateinit var listener: OnCourseItemClickListener

    inner class CourseListViewHolder(private val binding: GpCoursesListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(course: Course) {
            binding.acourse = course
            binding.listener = listener
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseListViewHolder {
        val binding = DataBindingUtil.inflate<GpCoursesListItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.gp_courses_list_item,
            parent,
            false
        )
        return CourseListViewHolder(binding)
    }

    fun setOnCourseItemClickListener(mListener: OnCourseItemClickListener) {
        listener = mListener
    }

    override fun onBindViewHolder(holder: CourseListViewHolder, position: Int) {
        holder.bind(courses[position])
    }

    override fun getItemCount(): Int {
        return courses.size
    }

    fun setCourses(newCourses: List<Course>) {
        courses = newCourses
        notifyDataSetChanged()
    }

    fun getTotalUnitLoads(): Float {
        var ul = 0
        courses.forEach {
            ul += it.unitLoad
        }
        return ul.toFloat()
    }

    fun getMultiFactor(): Float {
        var fac = 0.0
        courses.forEach {
            fac += (it.gradeDigit * it.unitLoad)
        }
        return fac.toFloat()
    }

    fun removeCourse(course: Course) {
        courses.toMutableList().remove(course)
        notifyDataSetChanged()
    }
}








