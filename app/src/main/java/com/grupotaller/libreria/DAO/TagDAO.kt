package com.grupotaller.libreria.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.grupotaller.libreria.Entity.Tag

@Dao
interface TagDAO {

    @Query("SELECT * FROM Tag")
    fun getAll(): LiveData<List<Tag>>

    @Insert
    fun Insert(vararg Tags: Tag)

    @Query("DELETE FROM Tag")
    fun deleteAll()
}