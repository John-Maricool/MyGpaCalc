package com.maricool.johnbatista.mygpacalc.data.prefs

import android.content.SharedPreferences
import com.maricool.johnbatista.mygpacalc.utils.Constants
import javax.inject.Inject

class SharedPrefs
    @Inject constructor(val prefs: SharedPreferences) {

        fun isFirstTime(): Boolean{
            return prefs.getBoolean(Constants.isFirstTime, true)
        }

        fun setFirstTimeStatus(status: Boolean){
            prefs.edit().putBoolean(Constants.isFirstTime, status).apply()
        }

        fun resetData(){
            prefs.edit().clear().apply()
        }
        fun setUserName(name: String){
            prefs.edit().putString(Constants.username, name).apply()
        }

        fun getUserName(): String?{
            return prefs.getString(Constants.username, "")
        }

        fun setGradePoint(point: Int){
            prefs.edit().putInt(Constants.gradePoint, point).apply()
        }

        fun getGradePoint(): Int{
            return prefs.getInt(Constants.gradePoint, 5)
        }
}