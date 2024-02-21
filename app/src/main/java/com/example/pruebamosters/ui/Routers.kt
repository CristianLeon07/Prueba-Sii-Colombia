package com.example.pruebamosters.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebamosters.ui.detail.DetailActivity
import com.example.pruebamosters.ui.home.HomeActivity

enum class screenChange() {

    HOME,
    DETAIL
}

class Router : AppCompatActivity() {

    /**
     * ir a pantallas posibles screen home, records,perfil
     */
    fun goToScreens(screen: screenChange, activity: Activity, finishActivity: Boolean) {

        when (screen) {

            screenChange.HOME -> {
                activity.startActivity(Intent(activity, HomeActivity::class.java))
            }
            screenChange.DETAIL -> {
                activity.startActivity(Intent(activity, DetailActivity::class.java))
            }


        }
        if (finishActivity) {
            activity.finish()
        }
    }

}