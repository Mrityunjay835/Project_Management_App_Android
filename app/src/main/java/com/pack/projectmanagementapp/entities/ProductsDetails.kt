package com.pack.projectmanagementapp.entities

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class ProductsDetails(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)

@Entity(tableName = "product_table")
data class Product(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "brand")
    val brand: String,

    @ColumnInfo(name = "category")
    val category: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "discountPercentage")
    val discountPercentage: Double,

    @ColumnInfo(name = "images")
    val images: List<String>,

    @ColumnInfo(name="price")
    val price: Int,

    @ColumnInfo(name = "rating")
    val rating: Double,

    @ColumnInfo(name = "stock")
    val stock: Int,

    @ColumnInfo(name = "thumbnail")
    val thumbnail: String,

    var isSelected: Boolean = false
): Parcelable {
    @RequiresApi(Build.VERSION_CODES.Q)
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.createStringArrayList() ?: arrayListOf(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readBoolean()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(brand)
        parcel.writeString(category)
        parcel.writeString(description)
        parcel.writeDouble(discountPercentage)
        parcel.writeStringList(images)
        parcel.writeInt(price)
        parcel.writeDouble(rating)
        parcel.writeInt(stock)
        parcel.writeString(thumbnail)
        parcel.writeString(isSelected.toString())
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        @RequiresApi(Build.VERSION_CODES.Q)
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}
