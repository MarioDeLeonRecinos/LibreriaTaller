package com.grupotaller.libreria.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "TagXLibro",
    primaryKeys = arrayOf("IDLF", "IDTF")
    , foreignKeys = arrayOf(
        ForeignKey(
            entity = Tag::class,
            parentColumns = arrayOf("IdT"),
            childColumns = arrayOf("IDTF")
        ),
        ForeignKey(
            entity = Libro::class,
            parentColumns = arrayOf("IdL"),
            childColumns = arrayOf("IDLF")
        )
    )

)
data class TagXLibro(
    @ColumnInfo(name = "IDLF") val IDLF: Int,
    @ColumnInfo(name = "IDTF") val IDTF: Int
)