package com.example.johnbatista.mygpacalc.ui.create_gp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.johnbatista.mygpacalc.data.repository.FirstScreenRepository
import com.example.johnbatista.mygpacalc.local_db.GpResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstScreenViewModel
@Inject constructor(val repo: FirstScreenRepository) : ViewModel() {

    val details = MutableLiveData<String>()
    private val _navigateToStart = MutableLiveData<String?>()
    val navigateToStart: LiveData<String?> get() = _navigateToStart

    fun start() {
        when {
            details.value == null -> {
                return
            }
            details.value!!.trim().isNotEmpty() -> {
                val gp = GpResult(details.value!!, "", 0, 0)
                viewModelScope.launch {
                    val job = viewModelScope.async {
                        repo.insertGpDetails(gp)
                    }
                    val res = job.await()
                    if (job.isCompleted) {
                        if (res)
                            _navigateToStart.postValue(details.value)
                        else {
                            _navigateToStart.postValue(null)
                        }
                    }
                }
            }
            else -> {
                return
            }
        }
    }
}