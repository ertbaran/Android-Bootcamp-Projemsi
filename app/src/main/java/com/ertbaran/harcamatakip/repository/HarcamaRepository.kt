package com.ertbaran.harcamatakip.repository

import androidx.lifecycle.LiveData
import com.ertbaran.harcamatakip.data.HarcamaDao
import com.ertbaran.harcamatakip.model.Harcama

class HarcamaRepository(private val harcamaDao: HarcamaDao) {
    val readAllData: LiveData<List<Harcama>> = harcamaDao.readAllData()
    
    suspend fun addHarcama(harcama: Harcama){
        harcamaDao.addHarcama(harcama)
    }
    suspend fun updateHarcama(harcama: Harcama){
        harcamaDao.updateHarcama(harcama)
    }
    suspend fun deleteHarcama(harcama: Harcama){
        harcamaDao.deleteHarcama(harcama)
    }
}