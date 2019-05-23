package com.grupotaller.libreria.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tag")
data class Tag (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "IdT") val IdT:Int,
    @ColumnInfo(name = "Tag") val Tag:String
)