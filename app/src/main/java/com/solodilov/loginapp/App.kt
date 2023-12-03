package com.solodilov.loginapp

import android.app.Application
import com.solodilov.loginapp.di.DaggerApplicationComponent

class App : Application() {

    val appComponent = DaggerApplicationComponent.factory().create(this)
}