package com.grupotaller.libreria.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.grupotaller.libreria.DAO.TagXLibroDAO
import com.grupotaller.libreria.Entity.Tag
import com.grupotaller.libreria.Entity.TagXLibro

class TagXLibroRepository(val tagxlibro : TagXLibroDAO) {
    var allTagXLibros : LiveData<List<Tag>> = tagxlibro.getTags();

    fun allTagXLibros() : LiveData<List<Tag>> = tagxlibro.getTags();

    fun getTags(id:Int):LiveData<List<Tag>>{
        return tagxlibro.getTags(id)
    }

    @WorkerThread
    suspend fun insert(TxL:TagXLibro){
        tagxlibro.Insert(TxL);
    }
    @WorkerThread
    suspend fun deleteAll(){
        tagxlibro.deleteAll();
    }

}