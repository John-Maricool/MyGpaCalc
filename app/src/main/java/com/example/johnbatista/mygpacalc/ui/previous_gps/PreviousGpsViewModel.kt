package com.example.johnbatista.mygpacalc.ui.previous_gps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.johnbatista.mygpacalc.data.models.GpResultModel
import com.example.johnbatista.mygpacalc.data.repository.PreviousGpsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreviousGpsViewModel
@Inject constructor(val repo: PreviousGpsRepository) : ViewModel() {

    private val _result = MutableLiveData<List<GpResultModel>>()
    val result: LiveData<List<GpResultModel>> get() = _result

    private val _delete = MutableLiveData<Boolean>()
    val delete: LiveData<Boolean> get() = _delete

    init {
        getSavedGps()
    }

    fun getSavedGps() {
        viewModelScope.launch(Main) {
            val job = viewModelScope.async {
                repo.getAllSavedGps()
            }
            val done = job.await()
            if (job.isCompleted) {
                _result.postValue(done)
            }
        }
    }

    fun deleteGp(details: String) {
        viewModelScope.launch {
            val job = viewModelScope.launch {
                repo.deleteOne(details)
            }
            job.join()
            if (job.isCompleted) {
                _delete.postValue(true)
            }
        }
    }

    fun deleteAllGp() {
        viewModelScope.launch {
            val job = viewModelScope.launch {
                repo.deleteAll()
            }
            job.join()
            if (job.isCompleted) {
                _delete.postValue(true)
            }
        }
    }
}