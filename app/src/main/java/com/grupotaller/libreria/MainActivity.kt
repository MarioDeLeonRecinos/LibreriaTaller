package com.grupotaller.libreria

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var BookViewModel: AndroidViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setSupportActionBar(toolbar)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_libro_list)
        val adapter =LibroListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        BookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)

        //rv_libro_list.layoutManager = LinearLayoutManager(this)

        //rv_libro_list.adapter = LibroListAdapter(libro, this)

        /*fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }*/
    }

    /*companion object {
        const val newLibroActivityRequest = 1
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == newLibroActivityRequest && resultCode == Activity.RESULT_OK){
            data.let {
                val Libro = Libro(it!!.getStringExtra(NewLibroActivity.EXTRA_REPLY))
                BookViewModel.insert(Libro)
            }
        }else{
            Toast.makeText(
                    applicationContext,
                    "Book no saved because is empyt.",
                    Toast.LENGTH_LONG).show()

        }
    }*/
}