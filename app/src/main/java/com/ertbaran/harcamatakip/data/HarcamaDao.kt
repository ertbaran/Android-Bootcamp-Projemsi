package com.ertbaran.harcamatakip.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ertbaran.harcamatakip.model.Harcama

@Dao
interface HarcamaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addHarcama(harcama: Harcama)

    @Update
    suspend fun updateHarcama(harcama: Harcama)

    @Delete
    suspend fun deleteHarcama(harcama: Harcama)

    @Query("SELECT * FROM harcama_table ORDER BY id ASC")
    fun  readAllData(): LiveData<List<Harcama>>

}