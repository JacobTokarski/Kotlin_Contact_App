package com.example.kotlin_app

import android.app.Application
import androidx.room.Room
import com.example.kotlin_app.db.LocalDatabase

class MainApplication: Application() {

    companion object {
        lateinit var todoDatabase: LocalDatabase
    }

    override fun onCreate() {
        super.onCreate()
        todoDatabase = Room.databaseBuilder(
            applicationContext,
            LocalDatabase::class.java,
            LocalDatabase.NAME
        ).build()
    }
}