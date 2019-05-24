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

    @Query("SELECT * FROM Libro where Favorito=1")
    fun getFavorito(): LiveData<List<Libro>>

    @Query("SELECT * FROM Libro where Titulo LIKE :nombre")
    fun getSearch(vararg nombre: String): LiveData<List<Libro>>

    //Como Sqlite maneja booleanos con un int siendo 0 false y 1 true se va a alternar entre estos
    @Query("UPDATE Libro SET Favorito=:Boolean WHERE IdL = :idL")
    fun updateFav(idL: Int, Boolean: Int)

    @Query("DELETE FROM Libro")
    fun deleteAll()
}