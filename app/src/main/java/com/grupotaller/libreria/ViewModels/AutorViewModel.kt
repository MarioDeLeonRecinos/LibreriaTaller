package com.grupotaller.libreria.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.grupotaller.libreria.Entity.Autor
import com.grupotaller.libreria.Entity.Libro
import com.grupotaller.libreria.Repository.AutorRepository
import com.grupotaller.libreria.Repository.AutorXLibroRepository
import com.grupotaller.libreria.RoomDatabase.AutorRoomDatabase
import com.grupotaller.libreria.RoomDatabase.AutorXLibroRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AutorViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: AutorRepository;
    private val repositoryjoin:AutorXLibroRepository;

    init{
        val autorsdao = AutorRoomDatabase.getDatabase(application,viewModelScope).autorDAO();
        repository = AutorRepository(autorsdao);
        val autorxlibrodao = AutorXLibroRoomDatabase.getDatabase(application, viewModelScope).AutorXLibroDAO();
        repositoryjoin = AutorXLibroRepository(autorxlibrodao)

    }
    fun getAll(): LiveData<List<Autor>> {
        return repository.allAutors
    }

    fun insert(autor: Autor) = viewModelScope.launch(Dispatchers.IO)
    {
        repository.insert(autor);
    }

    fun getAutorID(id: Int):LiveData<List<Autor>>{
        return repositoryjoin.getAutores(id)
    }


}