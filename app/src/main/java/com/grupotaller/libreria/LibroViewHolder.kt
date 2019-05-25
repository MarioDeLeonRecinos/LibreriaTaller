package com.grupotaller.libreria

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.grupotaller.libreria.Entity.Libro
import com.grupotaller.libreria.Repository.LibroRepository
import com.grupotaller.libreria.RoomDatabase.LibreriaRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LibroViewHolder (application: Application) : AndroidViewModel(application) {

    private val repository : LibroRepository
    val allLibro: LiveData<List<Libro>>

    init {
        val LibroDAO = LibreriaRoomDatabase.getDatabase(application, viewModelScope).LibroDAO()
        repository = LibroRepository(LibroDAO)
        allLibro = repository.allLibros
    }

    fun insert(libro: Libro) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(libro)
    }
}