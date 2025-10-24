package com.example.sqlite.data
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productos")
data class Producto (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val nombre: String,
    val precio: Double,
    val stock: Int,
    val tipo: String
)
