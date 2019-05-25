package com.grupotaller.libreria

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.grupotaller.libreria.Entity.Libro


class LibroListAdapter internal constructor(
        context: Context
) : RecyclerView.Adapter<LibroListAdapter.LibroViewHolder>(){

    private val inflater : LayoutInflater = LayoutInflater.from(context)
    private var libros = emptyList<Libro>()

    inner class LibroViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val libroItemView: TextView = itemView.findViewById(R.id.tv_libro_list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibroViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val itemView = inflater.inflate(R.layout.libro_list_item, parent, false)
    }

    override fun onBindViewHolder(holder: LibroViewHolder, position: Int) {
        val current = libros[position]
        holder.libroItemView.text = current.Titulo
    }

    internal fun setLibro(Libro: List<Libro>){
        this.libros = libros
        notifyDataSetChanged()
    }

    override fun getItemCount() = libros.size

}