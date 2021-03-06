package com.grupotaller.libreria.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.grupotaller.libreria.Entity.Tag
import com.grupotaller.libreria.Repository.TagRepository
import com.grupotaller.libreria.Repository.TagXLibroRepository
import com.grupotaller.libreria.RoomDatabase.LibreriaRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TagViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TagRepository

    init {
        val tagsdao = LibreriaRoomDatabase.getDatabase(application, viewModelScope)
        repository = TagRepository(tagsdao.TagDAO())

    }

    fun getAll(): LiveData<List<Tag>> {
        return repository.allTags
    }

    fun insert(tag: Tag) = viewModelScope.launch(Dispatchers.IO)
    {
        repository.insert(tag)
    }

    suspend fun deleteAll(){
        repository.deleteAll()
    }
}