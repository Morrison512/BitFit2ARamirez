package com.example.bitfit2aramirez

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BitFitDAO {
    @Query("SELECT * FROM bitfit_table")
    fun getAll(): Flow<List<BitFitEntity>>

    @Query("SELECT AVG(hoursSlept) as averageMood FROM bitfit_table")
    fun averageSleep(): Int

    @Query("SELECT COUNT(*) FROM bitfit_table")
    fun getRowCount(): Int

    @Insert
    fun insert(bitFit: BitFitEntity)

    @Query("DELETE FROM bitfit_table")
    fun deleteAll()
}