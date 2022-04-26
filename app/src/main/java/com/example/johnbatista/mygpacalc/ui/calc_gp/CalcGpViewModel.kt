package com.example.johnbatista.mygpacalc.ui.calc_gp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.johnbatista.mygpacalc.data.models.Course
import com.example.johnbatista.mygpacalc.data.models.GpResultModel
import com.example.johnbatista.mygpacalc.data.repository.CalcGpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalcGpViewModel
@Inject constructor(
    val repo: CalcGpRepository
) : ViewModel() {

    var details: String? = null
    private val _gps = MutableLiveData<List<Course>>()
    val gps: LiveData<List<Course>> get() = _gps

    private val _viewResult = MutableLiveData<GpResultModel>()
    val viewResult: LiveData<GpResultModel> get() = _viewResult

    private val _add = MutableLiveData<Boolean>()
    val add: LiveData<Boolean> get() = _add

    private val _delete = MutableLiveData<Boolean>()
    val delete: LiveData<Boolean> get() = _delete

    private val _calc = MutableLiveData<Boolean>()
    val calc: LiveData<Boolean> get() = _calc

    fun getAllCourses() {
        viewModelScope.launch {
            val job = viewModelScope.async {
                repo.getAllCourses(details!!)
            }
            val res = job.await()
            if (job.isCompleted)
                _gps.postValue(res)
        }
    }

    fun calculate() {
        _calc.value = true
    }

    fun addToResult(gp: GpResultModel) {
        viewModelScope.launch {
            val job = viewModelScope.launch {
                repo.addGpToDb(gp)
            }
            job.join()
            if (job.isCompleted) {
                _viewResult.postValue(gp)
            }
        }
    }

    fun deleteCourse(course: Course) {
        viewModelScope.launch {
            val job = viewModelScope.launch {
                repo.deleteCourse(course, details!!)
            }
            job.join()
            if (job.isCompleted) {
                _delete.postValue(true)
            }
        }
    }

    fun deleteAllCourse() {
        viewModelScope.launch {
            val job = viewModelScope.launch {
                repo.deleteAllCourse()
            }
            job.join()
            if (job.isCompleted) {
                _delete.postValue(true)
            }
        }
    }

    fun addCourse() {
        _add.value = true
    }
}