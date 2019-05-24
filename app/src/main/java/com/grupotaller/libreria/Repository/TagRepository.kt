package com.grupotaller.libreria.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.grupotaller.libreria.DAO.TagDAO
import com.grupotaller.libreria.Entity.Tag

class TagRepository(val tagdao : TagDAO) {
    val allTags : LiveData<List<Tag>> = tagdao.getAll();

    @WorkerThread
    suspend fun insert(tag:Tag) {
        tagdao.Insert(tag);
    }

    @WorkerThread
    suspend fun deleteAll(){
        tagdao.deleteAll();
    }

    fun getALL():LiveData<List<Tag>>{
        return tagdao.getAll()
    }

}