package com.kirilov.kontur_test.data.mapper

import com.kirilov.kontur_test.data.entity.ContactEntity
import com.kirilov.kontur_test.data.entity.ContactsEntity
import com.kirilov.kontur_test.data.entity.EducationPeriodEntity
import com.kirilov.kontur_test.domain.mapper.InputMapper
import com.kirilov.kontur_test.domain.model.Contact
import com.kirilov.kontur_test.domain.model.ContactsList
import com.kirilov.kontur_test.domain.model.EducationPeriod
import com.kirilov.kontur_test.domain.model.Temperament

object ContactsEntityMapper : InputMapper<List<ContactEntity>, ContactsList> {
    override fun transformToDomain(item: List<ContactEntity>): ContactsList {
        return ContactsList(item.map(ContactEntityMapper::transformToDomain))
    }
}

object ContactEntityMapper : InputMapper<ContactEntity, Contact> {
    override fun transformToDomain(item: ContactEntity): Contact {
        return Contact(
            id = item.id,
            name = item.name,
            phone = item.phone,
            height = item.height,
            biography = item.biography,
            temperament = Temperament.fromString(item.temperament),
            educationPeriod = EducationPeriodEntityMapper.transformToDomain(item.educationPeriod)
        )
    }
}

object EducationPeriodEntityMapper : InputMapper<EducationPeriodEntity, EducationPeriod> {
    override fun transformToDomain(item: EducationPeriodEntity): EducationPeriod {
        return EducationPeriod(
            start = item.start,
            end = item.end
        )
    }
}