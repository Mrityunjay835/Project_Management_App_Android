package com.pack.projectmanagementapp.services.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.pack.projectmanagementapp.entities.Product

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(dataEntity: Product)

    @Delete
    suspend fun deleteProduct(dataEntity: Product)
    //    @Update(onConflict = OnConflictStrategy.IGNORE)
    @Update
    suspend fun updateProduct(dataEntity: Product)

    @Query("SELECT * FROM product_table ORDER BY category DESC")
    fun getAllProduct(): LiveData<List<Product>>

    @Query("SELECT * FROM product_table WHERE id = :id")
    fun getProductById(id:Int):LiveData<Product>

    @Query("DELETE FROM product_table WHERE id = :id")
    fun deleteProductById(id: Int)




}