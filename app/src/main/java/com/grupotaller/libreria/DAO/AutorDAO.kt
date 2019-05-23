package com.grupotaller.libreria.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.grupotaller.libreria.Entity.Autor

@Dao
interface AutorDAO {

    @Query("SELECT * FROM Autor ORDER BY Nombre ASC")
    fun getAll(): LiveData<List<Autor>>

    @Insert
    fun Insert(vararg autores: Autor)

    @Query("DELETE FROM Autor")
    fun deleteAll()

}