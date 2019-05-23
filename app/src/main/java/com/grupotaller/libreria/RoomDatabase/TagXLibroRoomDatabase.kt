package com.grupotaller.libreria.RoomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.grupotaller.libreria.DAO.TagXLibroDAO
import com.grupotaller.libreria.Entity.Tag
import com.grupotaller.libreria.Entity.TagXLibro
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [TagXLibro::class], version = 1, exportSchema = false)
abstract class TagXLibroRoomDatabase :RoomDatabase(){

    abstract fun TagXLibroDAO(): TagXLibroDAO

    companion object {
        @Volatile
        private var INSTANCE: TagXLibroRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): TagXLibroRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TagXLibroRoomDatabase::class.java,
                    "Libreria"
                ).addCallback(TagDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }

        private class TagDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.TagXLibroDAO())
                    }
                }
            }
        }

        suspend fun populateDatabase(tagXLibroDAO: TagXLibroDAO) {
            tagXLibroDAO.deleteAll()

            var TxL = TagXLibro(1,2)
            tagXLibroDAO.Insert(TxL)
            TxL = TagXLibro(1, 3)
            tagXLibroDAO.Insert(TxL)
        }
    }
}