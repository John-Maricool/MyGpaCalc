package com.maricool.johnbatista.mygpacalc.ui.show_gp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShowGPViewModel
@Inject constructor(): ViewModel(){

    private val _navToAllGp = MutableLiveData<Boolean>()
     val navToAllGp: LiveData<Boolean> get() = _navToAllGp

    private val _startNewCalc = MutableLiveData<Boolean>()
    val startNewCalc: LiveData<Boolean> get() = _startNewCalc

    fun goToSavedGps(){
        _navToAllGp.value = true
    }

    fun startNewCalc(){
        _startNewCalc.value = true
    }

}