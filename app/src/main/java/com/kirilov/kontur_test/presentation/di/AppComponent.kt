package com.kirilov.kontur_test.presentation.di

//import com.kirilov.kontur_test.data.repository.ContactsApi
//import com.kirilov.kontur_test.data.repository.ContactsStorage
//import com.kirilov.kontur_test.domain.interactor.GetContactsUseCase
//import com.kirilov.kontur_test.domain.repository.ContactsRepository
//import com.kirilov.kontur_test.presentation.viewmodel.ContactDetailsViewModel
//import com.kirilov.kontur_test.presentation.viewmodel.ContactsListViewModel
import android.content.Context
import com.kirilov.kontur_test.data.db.ContactDao
import com.kirilov.kontur_test.data.db.ContactRoomDatabase
import com.kirilov.kontur_test.presentation.di.module.ContactsModule
import com.kirilov.kontur_test.presentation.di.module.RoomModule
import com.kirilov.kontur_test.presentation.di.module.ViewModelModule
import com.kirilov.kontur_test.presentation.view.fragment.ContactsListFragment
import com.kirilov.kontur_test.presentation.viewmodel.ViewModelFactory
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ContactsModule::class,
    ViewModelModule::class,
    RoomModule::class,
    AndroidSupportInjectionModule::class
])
interface AppComponent {

    fun inject(contactsListFragment: ContactsListFragment)

    fun viewModelFactory(): ViewModelFactory

    fun getContactDao(): ContactDao
    fun getContactRoomDatabase(): ContactRoomDatabase

}