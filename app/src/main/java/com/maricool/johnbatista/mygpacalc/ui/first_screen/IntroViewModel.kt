package com.maricool.johnbatista.mygpacalc.ui.first_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class IntroViewModel : ViewModel() {
    private val _navigateToStart = MutableLiveData<Boolean>()
    val navigateToStart: LiveData<Boolean> get() = _navigateToStart

    private val _navigateToPrevious = MutableLiveData<Boolean>()
    val navigateToPrevious: LiveData<Boolean> get() = _navigateToPrevious

    private val _navigateToAbout = MutableLiveData<Boolean>()
    val navigateToAbout: LiveData<Boolean> get() = _navigateToAbout

    fun start() {
        _navigateToStart.value = true
    }

    fun previous() {
        _navigateToPrevious.value = true
    }

    fun about(){
        _navigateToAbout.value = true
    }
}