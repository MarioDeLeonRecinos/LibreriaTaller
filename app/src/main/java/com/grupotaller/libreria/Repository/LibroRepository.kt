package com.grupotaller.libreria.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.grupotaller.libreria.DAO.LibroDAO
import com.grupotaller.libreria.Entity.Libro

class LibroRepository(val librodao: LibroDAO) {
    val allLibros: LiveData<List<Libro>> = librodao.getAll();
    val allfavorites: LiveData<List<Libro>> = librodao.getFavorito();
    @WorkerThread
    suspend fun insert(libro: Libro){
        librodao.Insert(libro);
    }

    @WorkerThread
    suspend fun deleteAll(){
        librodao.deleteAll();
    }

    @WorkerThread
    suspend fun search(nombre: String): List<Libro> {
        return librodao.getSearch(nombre);
    }

}