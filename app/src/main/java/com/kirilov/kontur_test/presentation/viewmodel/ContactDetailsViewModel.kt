package com.kirilov.kontur_test.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kirilov.kontur_test.domain.interactor.GetContactByIdUseCase
import com.kirilov.kontur_test.presentation.mapper.ContactModelMapper
import com.kirilov.kontur_test.presentation.model.ContactModel
import javax.inject.Inject

class ContactDetailsViewModel @Inject constructor(
    private val getContactByIdUseCase: GetContactByIdUseCase
) : ViewModel() {

    val contact = MutableLiveData<ContactModel>()

    fun getContactById(id: String) {
        getContactByIdUseCase.execute(
            id,
            onSuccess = {
                contact.value = ContactModelMapper().transformFromDomain(it)
            },
            onError = {
                it.printStackTrace()
            }
        )
    }
}