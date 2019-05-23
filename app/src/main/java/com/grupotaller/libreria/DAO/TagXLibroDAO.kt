package com.grupotaller.libreria.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.grupotaller.libreria.Entity.Tag
import com.grupotaller.libreria.Entity.TagXLibro

@Dao
interface TagXLibroDAO {

    @Query(
        """
                SELECT * FROM Tag
                INNER JOIN TagXLibro
                ON Tag.IdT=TagXLibro.IDTF
                WHERE TagXLibro.IDLF=:libroId
                """
    )
    fun getTags(vararg libroId: Int): LiveData<List<Tag>>

    @Insert
    fun Insert(vararg TxL: TagXLibro)

    @Query("DELETE FROM TagXLibro")
    fun deleteAll()
}