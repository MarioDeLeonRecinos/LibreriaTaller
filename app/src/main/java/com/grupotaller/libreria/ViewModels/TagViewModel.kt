package com.grupotaller.libreria.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.grupotaller.libreria.Entity.Tag
import com.grupotaller.libreria.Repository.TagRepository
import com.grupotaller.libreria.Repository.TagXLibroRepository
import com.grupotaller.libreria.RoomDatabase.TagRoomDatabase
import com.grupotaller.libreria.RoomDatabase.TagXLibroRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TagViewModel(application: Application) : AndroidViewModel(application)  {
    private val repository: TagRepository;
    private val repositoryjoin: TagXLibroRepository;

    init{
        val tagsdao = TagRoomDatabase.getDatabase(application,viewModelScope).TagDAO();
        repository = TagRepository(tagsdao);
        val autorxlibrodao = TagXLibroRoomDatabase.getDatabase(application, viewModelScope).TagXLibroDAO();
        repositoryjoin = TagXLibroRepository(autorxlibrodao)

    }
    fun getAll(): LiveData<List<Tag>> {
        return repository.allTags
    }

    fun insert(tag: Tag) = viewModelScope.launch(Dispatchers.IO)
    {
        repository.insert(tag);
    }

    fun getAutorID(id: Int):LiveData<List<Tag>>{
        return repositoryjoin.getTags(id)
    }
}