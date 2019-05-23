package com.grupotaller.libreria.RoomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.grupotaller.libreria.DAO.LibroDAO
import com.grupotaller.libreria.Entity.Libro
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Libro::class], version = 1, exportSchema = false)
abstract class LibroRoomDatabase : RoomDatabase() {

    abstract fun LibroDAO(): LibroDAO

    companion object {
        @Volatile
        private var INSTANCE: LibroRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): LibroRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LibroRoomDatabase::class.java,
                    "Libreria"
                ).addCallback(LibroDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }

        private class LibroDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.LibroDAO())
                    }
                }
            }
        }

        suspend fun populateDatabase(libroDAO: LibroDAO) {
            libroDAO.deleteAll()

            var libro = Libro(0, "Luna de Pluton",1548,"Mancos","direccion a imagen","Una pendejada",false)
            libroDAO.Insert(libro)
            libro = Libro(0, "Lobos del calla",1482,"Debolsillo","direccion a imagen","Otro libro",false)
            libroDAO.Insert(libro)
        }
    }
}