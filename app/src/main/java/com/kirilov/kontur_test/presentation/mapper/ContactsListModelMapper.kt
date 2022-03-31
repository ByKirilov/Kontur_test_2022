package com.kirilov.kontur_test.presentation.mapper

import com.kirilov.kontur_test.domain.mapper.OutputMapper
import com.kirilov.kontur_test.domain.model.Contact
import com.kirilov.kontur_test.domain.model.ContactsList
import com.kirilov.kontur_test.domain.model.EducationPeriod
import com.kirilov.kontur_test.presentation.model.ContactModel
import com.kirilov.kontur_test.presentation.model.ContactsListModel
import org.threeten.bp.Instant
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

class ContactsListModelMapper @Inject constructor() :
    OutputMapper<ContactsList, ContactsListModel> {
    override fun transformFromDomain(item: ContactsList): ContactsListModel =
        ContactsListModel(item.map(ContactModelMapper()::transformFromDomain))
}

class ContactModelMapper @Inject constructor() : OutputMapper<Contact, ContactModel> {
    override fun transformFromDomain(item: Contact): ContactModel =
        ContactModel(
            id = item.id,
            name = item.name,
            phone = item.phone,
            height = item.height.toString(),
            biography = item.biography,
            temperament = item.temperament?.value ?: "",
            educationPeriod = transformEducationPeriod(item.educationPeriod)
        )

    private fun transformEducationPeriod(educationPeriod: EducationPeriod): String {
        val formatter = DateTimeFormatter.ISO_DATE_TIME
        val outputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val startDate = OffsetDateTime.parse(educationPeriod.start, formatter).format(outputFormatter)
        val endDate = OffsetDateTime.parse(educationPeriod.end, formatter).format(outputFormatter)

        return "$startDate - $endDate"
    }
}