package com.grupotaller.libreria.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.grupotaller.libreria.Entity.Autor
import com.grupotaller.libreria.Entity.AutorXLibro
import com.grupotaller.libreria.Repository.AutorXLibroRepository
import com.grupotaller.libreria.RoomDatabase.LibreriaRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AutorXLibroViewModel (app: Application): AndroidViewModel(app){
    private val repository: AutorXLibroRepository;

    init {
        val autorsxlibrosdao = LibreriaRoomDatabase.getDatabase(app, viewModelScope);
        repository = AutorXLibroRepository(autorsxlibrosdao.AutorXLibroDAO());
    }

    fun insert(axl: AutorXLibro) = viewModelScope.launch(Dispatchers.IO)
    {
        repository.insert(axl);
    }

    suspend fun deleteAll(){
        repository.deleteAll();
    }

    fun getAutores(id:Int): LiveData<List<Autor>> {
        return repository.getAutores(id)
    }
}