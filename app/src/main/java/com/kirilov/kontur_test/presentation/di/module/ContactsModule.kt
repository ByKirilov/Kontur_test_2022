package com.kirilov.kontur_test.presentation.di.module

import android.app.Application
import android.content.Context
import com.kirilov.kontur_test.data.db.ContactDao
import com.kirilov.kontur_test.data.repository.ContactsApi
import com.kirilov.kontur_test.data.repository.ContactsStorage
import com.kirilov.kontur_test.domain.repository.ContactsRepository
import com.kirilov.kontur_test.presentation.ContactsApp
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ContactsModule(private val application: Application) {

    val BASE_URL = "https://raw.githubusercontent.com/"

    @Singleton
    @Provides
    fun provideApplicationContext(): Context = application

    @Provides
    fun provideContactsRepository(storage: ContactsStorage): ContactsRepository = storage

//    @Provides
//    fun provideContactsStorage(contactsApi: ContactsApi, contactsDao: ContactDao, context: Context) =
//        ContactsStorage(contactsApi, contactsDao, context)

    @Provides
    fun provideContactsApi(retrofit: Retrofit) : ContactsApi {
        return retrofit.create(ContactsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }
}