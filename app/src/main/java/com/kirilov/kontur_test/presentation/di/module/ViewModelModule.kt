package com.kirilov.kontur_test.presentation.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kirilov.kontur_test.presentation.di.annotation.ViewModelKey
import com.kirilov.kontur_test.presentation.viewmodel.ContactDetailsViewModel
import com.kirilov.kontur_test.presentation.viewmodel.ContactsListViewModel
import com.kirilov.kontur_test.presentation.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ContactsListViewModel::class)
    abstract fun bindContactsListViewModel(contactsListViewModel: ContactsListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ContactDetailsViewModel::class)
    abstract fun bindContactDetailsViewModel(contactsListViewModel: ContactDetailsViewModel): ViewModel

}