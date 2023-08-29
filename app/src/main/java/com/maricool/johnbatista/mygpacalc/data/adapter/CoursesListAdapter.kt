package com.maricool.johnbatista.mygpacalc.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.maricool.johnbatista.mygpacalc.R
import com.maricool.johnbatista.mygpacalc.data.interfaces.OnCourseItemClickListener
import com.maricool.johnbatista.mygpacalc.data.models.Course
import com.maricool.johnbatista.mygpacalc.data.prefs.SharedPrefs
import com.maricool.johnbatista.mygpacalc.databinding.GpCoursesListItemBinding
import javax.inject.Inject

class CoursesListAdapter
@Inject constructor(val prefs: SharedPrefs) : RecyclerView.Adapter<CoursesListAdapter.CourseListViewHolder>() {

    private var courses = listOf<Course>()
    lateinit var listener: OnCourseItemClickListener

    inner class CourseListViewHolder(private val binding: GpCoursesListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val type = prefs.getGradePoint()

        fun bind(course: Course) {
            binding.acourse = course
            binding.listener = listener
            binding.gadeUnit.text = course.getGradeDigit(type).toString()
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
        val type = prefs.getGradePoint()
        var fac = 0.0
        courses.forEach {
            fac += (it.getGradeDigit(type) * it.unitLoad)
        }
        return fac.toFloat()
    }

    fun removeCourse(course: Course) {
        courses.toMutableList().remove(course)
        notifyDataSetChanged()
    }
}








