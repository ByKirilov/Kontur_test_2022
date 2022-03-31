package com.kirilov.kontur_test.domain.interactor

import com.kirilov.kontur_test.domain.interactor.base.SingleUseCase
import com.kirilov.kontur_test.domain.model.ContactsList
import com.kirilov.kontur_test.domain.repository.ContactsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetContactsUseCase @Inject constructor(
    private val repository: ContactsRepository
) : SingleUseCase<ContactsList, Boolean>(){

    override fun buildUseCaseSingle(params: Boolean): Single<ContactsList> =
        repository.getContacts(params)
}