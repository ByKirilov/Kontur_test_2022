package com.kirilov.kontur_test.presentation.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.kirilov.kontur_test.data.db.ContactRoomDatabase
import com.kirilov.kontur_test.presentation.ContactsApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(context: Context): ContactRoomDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            ContactRoomDatabase::class.java,
            "contacts_database"
        ).build()

    @Singleton
    @Provides
    fun provideContactDao(database: ContactRoomDatabase) = database.contactDao()
}