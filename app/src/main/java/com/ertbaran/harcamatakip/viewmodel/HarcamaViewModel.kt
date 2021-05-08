package com.ertbaran.harcamatakip.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ertbaran.harcamatakip.data.HarcamaDatabase
import com.ertbaran.harcamatakip.model.Harcama
import com.ertbaran.harcamatakip.repository.HarcamaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HarcamaViewModel(application: Application):AndroidViewModel(application) {
    val readAllData:LiveData<List<Harcama>>
    private val repository: HarcamaRepository

    init {
        val harcamaDao = HarcamaDatabase.getDatabase(application).harcamaDao()
        repository = HarcamaRepository(harcamaDao)
        readAllData = repository.readAllData
    }

    fun harcamaEkle(harcama: Harcama){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addHarcama(harcama)
        }
    }
    fun harcamaGuncelle(harcama: Harcama){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateHarcama(harcama)
        }
    }
    fun harcamaSil(harcama: Harcama){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteHarcama(harcama)
        }
    }
}