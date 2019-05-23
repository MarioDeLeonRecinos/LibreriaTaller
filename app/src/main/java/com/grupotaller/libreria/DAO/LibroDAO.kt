package com.grupotaller.libreria.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.grupotaller.libreria.Entity.Libro

@Dao
interface LibroDAO {

    @Query("SELECT * FROM Libro ORDER BY Titulo ASC")
    fun getAll(): LiveData<List<Libro>>

    @Insert
    fun Insert(vararg libro: Libro)

    @Query("SELECT * FROM Libro where Favorito=0")
    fun getFavorito(): LiveData<List<Libro>>

    @Query("SELECT * FROM Libro where Titulo LIKE :nombre")
    fun getSearch(vararg nombre: String)

    @Query("DELETE FROM Libro")
    fun deleteAll()
}