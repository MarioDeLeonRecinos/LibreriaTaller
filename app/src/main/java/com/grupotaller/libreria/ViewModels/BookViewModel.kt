package com.grupotaller.libreria.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.grupotaller.libreria.Entity.Libro
import com.grupotaller.libreria.Repository.LibroRepository
import com.grupotaller.libreria.RoomDatabase.LibreriaRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookViewModel(app:Application): AndroidViewModel(app) {

    private val repository : LibroRepository

    init {
        val librodao = LibreriaRoomDatabase.getDatabase(app, viewModelScope).LibroDAO()
        repository = LibroRepository(librodao)
    }

    fun insert(libro:Libro)= viewModelScope.launch(Dispatchers.IO){
        repository.insert(libro)
    }

    fun getsearch (nombre:String) : LiveData<List<Libro>>{
        return repository.search(nombre)
    }

    fun getFavorito(): LiveData<List<Libro>>{
        return  repository.getFavoritos()
    }

    fun getAll(): LiveData<List<Libro>>{
        return repository.getAll()
    }

    suspend fun deleteAll(){
        repository.deleteAll();
    }
}