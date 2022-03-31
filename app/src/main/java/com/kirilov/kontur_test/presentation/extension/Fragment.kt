package com.kirilov.kontur_test.presentation.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kirilov.kontur_test.presentation.ContactsApp
import com.kirilov.kontur_test.presentation.di.AppComponent

inline fun <reified T: ViewModel> Fragment.viewModel(factory: ViewModelProvider.Factory, body: T.() -> Unit): T {
    val vm = ViewModelProvider(this, factory)[T::class.java]
    vm.body()
    return vm
}

fun Fragment.getAppComponent(): AppComponent =
    (requireActivity().application as ContactsApp).appComponent