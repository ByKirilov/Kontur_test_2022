package com.kirilov.kontur_test.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ContactDBEntity::class], version = 1, exportSchema = false)
abstract class ContactRoomDatabase : RoomDatabase() {

    abstract fun contactDao(): ContactDao

//    companion object {
//        @Volatile
//        private var INSTANCE: ContactRoomDatabase? = null
//
//        fun getDatabase(context: Context): ContactRoomDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    ContactRoomDatabase::class.java,
//                    "contacts_database"
//                ).build()
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
}