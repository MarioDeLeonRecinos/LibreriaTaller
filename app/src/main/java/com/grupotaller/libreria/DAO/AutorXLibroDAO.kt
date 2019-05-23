package com.grupotaller.libreria.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.grupotaller.libreria.Entity.Autor
import com.grupotaller.libreria.Entity.AutorXLibro


@Dao
interface AutorXLibroDAO {

    @Query(
        """
                SELECT * FROM Autor
                INNER JOIN AutorXLibro
                ON Autor.IdA=AutorXLibro.IDAF
                WHERE AutorXLibro.IDLF=:libroId
                """
    )
    fun getAutores(vararg libroId: Int): LiveData<List<Autor>>

    @Insert
    fun Insert(vararg AxL:AutorXLibro)

    @Query("DELETE FROM AutorXLibro")
    fun deleteAll()
}