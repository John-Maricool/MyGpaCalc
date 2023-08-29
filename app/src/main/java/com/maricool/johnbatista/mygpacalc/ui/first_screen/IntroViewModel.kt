package com.maricool.johnbatista.mygpacalc.ui.first_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maricool.johnbatista.mygpacalc.data.models.GpResultModel
import com.maricool.johnbatista.mygpacalc.data.prefs.SharedPrefs
import com.maricool.johnbatista.mygpacalc.data.repository.RetriveGpDetailsRepo
import com.maricool.johnbatista.mygpacalc.local_db.GpResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IntroViewModel @Inject constructor(
val repo: RetriveGpDetailsRepo, val prefs: SharedPrefs
) : ViewModel() {

    private val _totalCourses = MutableLiveData<String>()
    val totalCourses: LiveData<String> get() = _totalCourses

    private val _totalSemesters = MutableLiveData<String>()
    val totalSemesters: LiveData<String> get() = _totalSemesters

    private val _isListEmpty = MutableLiveData<Boolean>(false)
    val isListEmpty: LiveData<Boolean> get() = _isListEmpty

    private val _totalUL = MutableLiveData<Int>()
    val totalUL: LiveData<Int> get() = _totalUL

    private val _averageGp = MutableLiveData<String>()
    val averageGp: LiveData<String> get() = _averageGp

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> get() = _userName

    private val _gpList = MutableLiveData<List<GpResultModel>>(listOf())
    val gpList: LiveData<List<GpResultModel>> get() = _gpList

    init {
        start()
        _userName.value = "Welcome ${prefs.getUserName()}"
    }
    fun start() {
        viewModelScope.launch {
                _gpList.value = repo.getAllSavedGps()
        }
        _gpList.observeForever {
            _isListEmpty.value = it.isEmpty()
            _totalSemesters.value = repo.getTotalSemesters(it)
            _totalCourses.value = repo.getTotalCourses(it)
            _totalUL.value = repo.getTotalUnitLoads(it)
            _averageGp.value = repo.getAverageGp(it)
        }
    }
}