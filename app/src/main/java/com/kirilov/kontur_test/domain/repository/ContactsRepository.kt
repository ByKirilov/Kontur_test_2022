package com.kirilov.kontur_test.domain.repository

import com.kirilov.kontur_test.domain.model.Contact
import com.kirilov.kontur_test.domain.model.ContactsList
import io.reactivex.Single

interface ContactsRepository {

    fun getContacts(isRefresh: Boolean): Single<ContactsList>

    fun getContactById(id: String): Single<Contact>
}