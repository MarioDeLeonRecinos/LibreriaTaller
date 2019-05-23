package com.grupotaller.libreria.Repository

import androidx.lifecycle.LiveData
import com.grupotaller.libreria.DAO.AutorXLibroDAO
import com.grupotaller.libreria.Entity.Autor
import com.grupotaller.libreria.Entity.AutorXLibro

class AutorXLibroRepository(private val autorxlibro: AutorXLibroDAO) {

    var allAutorXLibros : LiveData<List<Autor>> = autorxlibro.getAutores();





}