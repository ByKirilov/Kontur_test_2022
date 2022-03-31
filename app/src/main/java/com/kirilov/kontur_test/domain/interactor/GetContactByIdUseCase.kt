package com.kirilov.kontur_test.domain.interactor

import com.kirilov.kontur_test.domain.interactor.base.SingleUseCase
import com.kirilov.kontur_test.domain.model.Contact
import com.kirilov.kontur_test.domain.repository.ContactsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetContactByIdUseCase @Inject constructor(
    private val repository: ContactsRepository
) : SingleUseCase<Contact, String>() {

    override fun buildUseCaseSingle(params: String): Single<Contact> =
        repository.getContactById(params)
}