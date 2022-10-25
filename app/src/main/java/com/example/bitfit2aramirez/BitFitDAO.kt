package com.example.bitfit2aramirez

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BitFitDAO {
    @Query("SELECT * FROM bitfit_table")
    fun getAll(): Flow<List<BitFitEntity>>

    @Insert
    fun insert(bitFit: BitFitEntity)

    @Query("DELETE FROM bitfit_table")
    fun deleteAll()
}