package com.grupotaller.libreria.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.grupotaller.libreria.DAO.AutorDAO
import com.grupotaller.libreria.Entity.Autor


class AutorRepository (private val autorDao: AutorDAO){
    val allAutors: LiveData<List<Autor>> = autorDao.getAll();

    @WorkerThread
    suspend fun insert(autor: Autor){
        autorDao.Insert(autor);
    }
    @WorkerThread
    suspend fun deleteAll(){
        autorDao.deleteAll();
    }


}