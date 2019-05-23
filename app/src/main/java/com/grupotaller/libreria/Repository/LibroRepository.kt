package com.grupotaller.libreria.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.grupotaller.libreria.DAO.LibroDAO
import com.grupotaller.libreria.Entity.Libro

class LibroRepository(val librodao: LibroDAO) {
    
    val allLibros: LiveData<List<Libro>> = librodao.getAll();
    
    @WorkerThread
    suspend fun insert(libro: Libro){
        librodao.Insert(libro);
    }

    @WorkerThread
    suspend fun deleteAll(){
        librodao.deleteAll();
    }

    @WorkerThread
    suspend fun search(nombre: String){
        allLibros=librodao.getSearch(nombre);
    }
    
    @WorkerThread
    suspend fun getFavoritos(){
        allLibros=librodao.getFavorito();
    }
    
    @WorkerThread
    suspend fun getAll(){
        allLibros=librodao.getAll();
    }
}
