package com.example.johnbatista.mygpacalc.utils

import android.view.View
import android.widget.*
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.RecyclerView
import com.example.johnbatista.mygpacalc.data.adapter.CoursesListAdapter
import com.example.johnbatista.mygpacalc.data.adapter.PreviousGpListAdapter
import com.example.johnbatista.mygpacalc.data.models.Course
import com.example.johnbatista.mygpacalc.data.models.GpResultModel

@BindingAdapter("set_course_text")
fun TextView.setCourseText(value: Float) {
    text = value.toInt().toString()
}

@BindingAdapter("init_gp_list_recycler")
fun initialize(view: RecyclerView, adapter: CoursesListAdapter) {
    view.setHasFixedSize(true)
    view.adapter = adapter
}

@BindingAdapter("set_courses_list_item")
fun setCourseList(view: RecyclerView, list: List<Course>?) {
    if (list != null) {
        val adapter = view.adapter as CoursesListAdapter
        adapter.setCourses(list)
    }
}


@BindingAdapter("init_gp_prev_list_recycler")
fun initializePrev(view: RecyclerView, adapter: PreviousGpListAdapter) {
    view.setHasFixedSize(true)
    view.adapter = adapter
}

@BindingAdapter("set_prev_gp_list_item")
fun setPrevGoList(view: RecyclerView, list: List<GpResultModel>?) {
    if (list != null) {
        val adapter = view.adapter as PreviousGpListAdapter
        adapter.setPrevList(list)
    }
}

@BindingAdapter("set_course_text")
fun EditText.setCText(course: Course?) {
    if (course != null) {
        setText(course.name)
    }
}

@BindingAdapter("set_course_btn")
fun Button.setCBtn(course: Course?) {
    if (course != null) {
        setText("Update")
    } else {
        setText("Add")
    }
}

@BindingAdapter("set_ul_choice")
fun Spinner.setUlChoice(course: Course?) {
    if (course != null) {
        setSelection(getUnitLoadIndex(course.unitLoad.toString()))
    }
}

@BindingAdapter("set_grade_choice")
fun Spinner.setGradeChoice(course: Course?) {
    if (course != null) {
        setSelection(getGradeIndex(course.grade))
    }
}

@BindingAdapter("on_spinner_selected")
fun Spinner.onItemSelected(item: ObservableField<String>) {
    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            item.set(parent?.getItemAtPosition(position) as String)
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            return
        }

    }
}


