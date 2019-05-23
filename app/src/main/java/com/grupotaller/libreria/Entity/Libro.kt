package com.grupotaller.libreria.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Libro")
data class Libro(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "IdL") val IdL: Int,
    @ColumnInfo(name = "Titulo") val Titulo: String,
    @ColumnInfo(name = "ISVN") val ISVN: Int,
    @ColumnInfo(name = "Editorial") val Editorial: String,
    @ColumnInfo(name = "Caratula") val Caratula: String?,
    @ColumnInfo(name = "Resumen") val Resumen: String,
    @ColumnInfo(name = "Favorito") var Favorito: Boolean=false
)