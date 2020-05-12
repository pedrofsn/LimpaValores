package br.com.redcode.limpavalores

import android.app.Application
import com.google.firebase.FirebaseApp

/*
    CREATED BY @PEDROFSN IN 05/05/20 09:33
*/

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }

}