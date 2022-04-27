package com.maricool.johnbatista.mygpacalc.ui.add_course

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maricool.johnbatista.mygpacalc.data.models.Course
import com.maricool.johnbatista.mygpacalc.data.repository.AddCourseRepository
import com.maricool.johnbatista.mygpacalc.utils.isInputValid
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCourseViewModel
@Inject constructor(val repo: AddCourseRepository) : ViewModel() {

    private val _added = MutableLiveData<Boolean>()
    val added: LiveData<Boolean> get() = _added

    val ulValue = ObservableField<String>()
    val gradeValue = ObservableField<String>()
    val courseText = MutableLiveData<String>()

    var course: Course? = null
    lateinit var details: String

    private fun add(co: Course, details: String) {
        viewModelScope.launch {
            val job = viewModelScope.launch {
                repo.addCourse(co, details)
            }
            job.join()
            if (job.isCompleted)
                _added.postValue(true)
        }
    }

    private fun update(co: Course, details: String) {
        viewModelScope.launch {
            val job = viewModelScope.launch {
                repo.updateCourse(co, details)
            }
            job.join()
            if (job.isCompleted)
                _added.postValue(true)
        }
    }

    fun onButtonClick() {
        if (!isInputValid(courseText.value)) return
        val ul = ulValue.get()?.toInt()
        val grade = gradeValue.get()
        val cou = Course(0, courseText.value!!, ul!!, grade!!)
        if (course != null) {
            cou.id = course!!.id
            update(cou, details)
        } else
            add(cou, details)
    }
}