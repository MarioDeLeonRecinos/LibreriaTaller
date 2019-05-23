package com.grupotaller.libreria.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Autor")
data class Autor(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "IdA") val IdA:Int,
    @ColumnInfo(name = "Nombre") val Nombre:String
)