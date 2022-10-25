package com.example.bitfit2aramirez

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BitFitEntity::class], version = 1)
abstract class BitFitDataBase : RoomDatabase() {

    abstract fun articleDao(): BitFitDAO

    companion object {

        @Volatile
        private var INSTANCE: BitFitDataBase? = null

        fun getInstance(context: Context): BitFitDataBase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                BitFitDataBase::class.java, "BitFit-db"
            ).build()
    }
}