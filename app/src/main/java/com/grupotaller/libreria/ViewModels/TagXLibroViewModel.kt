package com.grupotaller.libreria.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.grupotaller.libreria.Entity.Tag
import com.grupotaller.libreria.Entity.TagXLibro
import com.grupotaller.libreria.Repository.TagXLibroRepository
import com.grupotaller.libreria.RoomDatabase.LibreriaRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TagXLibroViewModel(app: Application): AndroidViewModel(app) {

    private val repository: TagXLibroRepository

    init {
        val tagsxlibrosdao = LibreriaRoomDatabase.getDatabase(app, viewModelScope)
        repository = TagXLibroRepository(tagsxlibrosdao.TagXLibroDAO())
    }

    fun insert(txl: TagXLibro) = viewModelScope.launch(Dispatchers.IO)
    {
        repository.insert(txl)
    }

    suspend fun deleteAll(){
        repository.deleteAll()
    }

    fun getTags(id:Int): LiveData<List<Tag>> {
        return repository.getTags(id)
    }
}