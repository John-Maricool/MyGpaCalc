package com.example.johnbatista.mygpacalc.ui.first_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class IntroViewModel : ViewModel() {
    private val _navigateToStart = MutableLiveData<Boolean>()
    val navigateToStart: LiveData<Boolean> get() = _navigateToStart

    private val _navigateToPrevious = MutableLiveData<Boolean>()
    val navigateToPrevious: LiveData<Boolean> get() = _navigateToPrevious

    fun start() {
        _navigateToStart.value = true
    }

    fun previous() {
        _navigateToPrevious.value = true
    }
}