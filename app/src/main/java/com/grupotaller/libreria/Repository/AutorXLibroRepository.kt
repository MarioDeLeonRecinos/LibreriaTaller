package com.grupotaller.libreria.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.grupotaller.libreria.DAO.AutorXLibroDAO
import com.grupotaller.libreria.Entity.Autor
import com.grupotaller.libreria.Entity.AutorXLibro

class AutorXLibroRepository(private val autorxlibro: AutorXLibroDAO) {

    var allAutorXLibros : LiveData<List<Autor>> = autorxlibro.getAutores();

    fun getAutores(id:Int):LiveData<List<Autor>>{
        return autorxlibro.getAutores(id)
    }

    @WorkerThread
    suspend fun insert(AxL:AutorXLibro){
        autorxlibro.Insert(AxL);
    }
    @WorkerThread
    suspend fun deleteAll(){
        autorxlibro.deleteAll();
    }




}