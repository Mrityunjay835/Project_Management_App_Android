package com.pack.projectmanagementapp.services.local

import com.pack.projectmanagementapp.entities.Product
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pack.projectmanagementapp.utils.Convertor

@Database(entities = [Product::class],
    version = 1,
    exportSchema = true)
@TypeConverters(Convertor::class)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun getProductDao():ProductDao
    companion object{

        private var INSTANCE:ProductDatabase ?= null

        fun getDatabase(context: Context):ProductDatabase{
            if(INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        ProductDatabase::class.java,
                        "product_database").build()
                }
            }
            return INSTANCE!!
        }
    }
}

