package com.grupotaller.libreria.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.grupotaller.libreria.DAO.LibroDAO
import com.grupotaller.libreria.Entity.Libro

class LibroRepository(val librodao: LibroDAO) {
    val allLibros: LiveData<List<Libro>> = librodao.getAll();
    //val allfavorites: LiveData<List<Libro>> = librodao.getFavorito();

    fun getALL():LiveData<List<Libro>> {
        return librodao.getAll()
    }

    fun getFavoritos(): LiveData<List<Libro>>{
        return librodao.getFavorito()
    }

    @WorkerThread
    suspend fun insert(libro: Libro){
        librodao.Insert(libro);
    }

    @WorkerThread
    suspend fun deleteAll(){
        librodao.deleteAll();
    }

    @WorkerThread
    fun search(nombre: String): LiveData<List<Libro>> {
        return librodao.getSearch(nombre);
    }

}