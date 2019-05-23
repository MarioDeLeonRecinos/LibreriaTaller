package com.grupotaller.libreria.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "AutorXLibro",
    primaryKeys = arrayOf("IDLF", "IDAF")
    , foreignKeys = arrayOf(
        ForeignKey(
            entity = Autor::class,
            parentColumns = arrayOf("IdA"),
            childColumns = arrayOf("IDAF")
        ),
        ForeignKey(
            entity = Libro::class,
            parentColumns = arrayOf("IdL"),
            childColumns = arrayOf("IDLF")
        )
    )

)
data class AutorXLibro(
    @ColumnInfo(name = "IDLF") val IDLF: Int,
    @ColumnInfo(name = "IDAF") val IDAF: Int
)