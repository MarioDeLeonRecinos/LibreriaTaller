package com.grupotaller.libreria.RoomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.grupotaller.libreria.DAO.AutorXLibroDAO
import com.grupotaller.libreria.Entity.Autor
import com.grupotaller.libreria.Entity.AutorXLibro
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [AutorXLibro::class], version = 1, exportSchema = false)
abstract class AutorXLibroRoomDatabase :RoomDatabase(){

    abstract fun AutorXLibroDAO(): AutorXLibroDAO

    companion object {
        @Volatile
        private var INSTANCE: AutorXLibroRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): AutorXLibroRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AutorXLibroRoomDatabase::class.java,
                    "Libreria"
                ).addCallback(AutorXLibroDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }

        private class AutorXLibroDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.AutorXLibroDAO())
                    }
                }
            }
        }

        suspend fun populateDatabase(autorXLibroDAO: AutorXLibroDAO) {
            autorXLibroDAO.deleteAll()

            var AxL = AutorXLibro(1,1)
            autorXLibroDAO.Insert(AxL)
            AxL = AutorXLibro(1,2)
            autorXLibroDAO.Insert(AxL)
        }
    }
}