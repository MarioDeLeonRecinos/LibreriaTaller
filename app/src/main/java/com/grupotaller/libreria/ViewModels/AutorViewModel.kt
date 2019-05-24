package com.grupotaller.libreria.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.grupotaller.libreria.Entity.Autor
import com.grupotaller.libreria.Entity.Tag
import com.grupotaller.libreria.Repository.AutorRepository
import com.grupotaller.libreria.Repository.AutorXLibroRepository
import com.grupotaller.libreria.Repository.TagRepository
import com.grupotaller.libreria.Repository.TagXLibroRepository
import com.grupotaller.libreria.RoomDatabase.LibreriaRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AutorViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AutorRepository;
    private val repositoryjoin: AutorXLibroRepository;

    init {
        val autorsdao = LibreriaRoomDatabase.getDatabase(application, viewModelScope);
        repository = AutorRepository(autorsdao.AutorDAO());
        repositoryjoin = AutorXLibroRepository(autorsdao.AutorXLibroDAO())

    }

    fun getAll(): LiveData<List<Autor>> {
        return repository.allAutors
    }

    fun insert(autor: Autor) = viewModelScope.launch(Dispatchers.IO)
    {
        repository.insert(autor);
    }

    fun getAutorID(id: Int): LiveData<List<Autor>> {
        return repositoryjoin.getAutores(id)
    }

    suspend fun deleteAll(){
        repository.deleteAll();
    }
}