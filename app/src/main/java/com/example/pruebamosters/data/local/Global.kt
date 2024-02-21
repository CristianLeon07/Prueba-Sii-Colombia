package com.example.pruebamosters.data.local

import android.app.Application

class Global : Application() {
    companion object {
        @JvmField

        var monsterSelect: Monster? = null

    }
}