package com.grupotaller.libreria.RoomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.grupotaller.libreria.DAO.TagDAO
import com.grupotaller.libreria.Entity.Tag
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Tag::class], version = 1, exportSchema = false)
abstract class TagRoomDatabase :RoomDatabase(){

    abstract fun TagDAO(): TagDAO

    companion object {
        @Volatile
        private var INSTANCE: TagRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): TagRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TagRoomDatabase::class.java,
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
                        populateDatabase(database.TagDAO())
                    }
                }
            }
        }

        suspend fun populateDatabase(tagDAO: TagDAO) {
            tagDAO.deleteAll()

            var tag = Tag(0, "comedia")
            tagDAO.Insert(tag)
            tag = Tag(0, "misterio")
            tagDAO.Insert(tag)
        }
    }
}