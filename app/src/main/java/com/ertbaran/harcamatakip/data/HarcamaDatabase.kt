package com.ertbaran.harcamatakip.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ertbaran.harcamatakip.model.Harcama

@Database(entities = [Harcama::class], version = 1, exportSchema = false)
abstract class HarcamaDatabase : RoomDatabase() {
    abstract fun harcamaDao(): HarcamaDao

    companion object {
        @Volatile
        private var INSTANCE: HarcamaDatabase? = null

        fun getDatabase(context:Context): HarcamaDatabase{
            val tempInstance = INSTANCE
            if (tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    HarcamaDatabase::class.java,
                    "harcama_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}