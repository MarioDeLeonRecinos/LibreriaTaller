package com.grupotaller.libreria

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.AndroidViewModel
import com.grupotaller.libreria.Entity.Libro

class NewLibroActivity : AppCompatActivity() {

    private lateinit var editLibroView : EditText
    private lateinit var BookViewModel: AndroidViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_libro)
        editLibroView = findViewById(R.id.edit_libro)

        val button= findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            //val NewLibro =  Libro()
        }
    }

    companion object {
        const val EXTRA_REPLY ="com.example.android.librolistsql.REPLY"
    }
}
