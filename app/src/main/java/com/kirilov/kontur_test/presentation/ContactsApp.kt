package com.kirilov.kontur_test.presentation

import android.app.Application
import com.kirilov.kontur_test.presentation.di.AppComponent
import com.kirilov.kontur_test.presentation.di.DaggerAppComponent
import com.kirilov.kontur_test.presentation.di.module.ContactsModule
import com.kirilov.kontur_test.presentation.di.module.RoomModule
import javax.inject.Inject

class ContactsApp @Inject constructor() : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .contactsModule(ContactsModule(this))
            .build()
    }

}