package com.kirilov.kontur_test.data.repository

import android.content.Context
import com.kirilov.kontur_test.data.db.ContactDao
import com.kirilov.kontur_test.data.entity.ContactsEntity
import com.kirilov.kontur_test.data.mapper.ContactDBMapper
import com.kirilov.kontur_test.data.mapper.ContactEntityMapper
import com.kirilov.kontur_test.data.mapper.ContactsEntityMapper
import com.kirilov.kontur_test.domain.model.Contact
import com.kirilov.kontur_test.domain.model.ContactsList
import com.kirilov.kontur_test.domain.model.EducationPeriod
import com.kirilov.kontur_test.domain.model.Temperament
import com.kirilov.kontur_test.domain.repository.ContactsRepository
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ContactsStorage @Inject constructor(
    private val contactsApi: ContactsApi,
    private val contactDao: ContactDao,
    private val context: Context
) : ContactsRepository {

    private val isDataObsolete: Boolean
        get() {
            val lastDownloadTime = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
                .getLong(LAST_DOWNLOAD_TIME_KEY, 0L)
            val currentTime = System.currentTimeMillis()
            return currentTime - lastDownloadTime > TimeUnit.MILLISECONDS.convert(1, TimeUnit.MINUTES)
        }

    private var isApplicationStart = true

    override fun getContacts(isRefresh: Boolean): Single<ContactsList> {
        if (isRefresh || (isDataObsolete && isApplicationStart)) {
            isApplicationStart = false
            return Single.zip(
                contactsApi.getContacts(1),
                contactsApi.getContacts(2),
                contactsApi.getContacts(3)
            ) { firstSource, secondSource, thirdSource ->
                firstSource + secondSource + thirdSource
            }
                .doOnSuccess {
                    contactDao.deleteAll()
                    contactDao.insertAll(it.map(ContactDBMapper::transformToDBEntity))
                    updateLastDownloadTime()
                }
                .map { ContactsEntityMapper.transformToDomain(it) }
        }
        return contactDao.getAllContacts()
            .map {
                ContactsEntityMapper.transformToDomain(
                    it.map(ContactDBMapper::transformFromDBEntity)
                )
            }
    }

    override fun getContactById(id: String): Single<Contact> {
        return contactDao.getContactById(id)
            .map {
                ContactEntityMapper.transformToDomain(
                    ContactDBMapper.transformFromDBEntity(it)
                )
            }
    }

    private fun updateLastDownloadTime() {
        val prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putLong(LAST_DOWNLOAD_TIME_KEY, System.currentTimeMillis()).apply()
    }

    companion object {
        const val LAST_DOWNLOAD_TIME_KEY = "lastDownloadTime"
    }
}