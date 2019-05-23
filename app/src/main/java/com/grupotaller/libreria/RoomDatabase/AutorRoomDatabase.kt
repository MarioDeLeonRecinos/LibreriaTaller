package com.grupotaller.libreria.RoomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.grupotaller.libreria.DAO.AutorDAO
import com.grupotaller.libreria.Entity.Autor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Autor::class], version = 1, exportSchema = false)
abstract class AutorRoomDatabase : RoomDatabase() {

    abstract fun autorDAO(): AutorDAO

    companion object {
        @Volatile
        private var INSTANCE: AutorRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): AutorRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AutorRoomDatabase::class.java,
                    "Libreria"
                ).addCallback(AutorDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }

        private class AutorDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.autorDAO())
                    }
                }
            }
        }

        suspend fun populateDatabase(autorDAO: AutorDAO) {
            autorDAO.deleteAll()

            var autor = Autor(0,"Vegetta777")
            autorDAO.Insert(autor)
            autor = Autor(0,"Sthepehn King")
            autorDAO.Insert(autor)
        }
    }
}