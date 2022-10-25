package com.example.bitfit2aramirez

import android.app.Application

class BitFItApplication : Application() {
    val db by lazy { BitFitDataBase.getInstance(this) }
}