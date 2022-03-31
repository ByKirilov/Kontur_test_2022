package com.kirilov.kontur_test.data.mapper

import com.kirilov.kontur_test.data.db.ContactDBEntity
import com.kirilov.kontur_test.data.entity.ContactEntity
import com.kirilov.kontur_test.data.entity.EducationPeriodEntity

object ContactDBMapper {
    fun transformToDBEntity(contact: ContactEntity): ContactDBEntity {
        return ContactDBEntity(
            id = contact.id,
            name = contact.name,
            phone = contact.phone,
            height = contact.height,
            biography = contact.biography,
            temperament = contact.temperament,
            educationStart = contact.educationPeriod.start,
            educationEnd = contact.educationPeriod.end
        )
    }

    fun transformFromDBEntity(dbContact: ContactDBEntity): ContactEntity {
        return ContactEntity(
            id = dbContact.id,
            name = dbContact.name,
            phone = dbContact.phone,
            height = dbContact.height,
            biography = dbContact.biography,
            temperament = dbContact.temperament,
            educationPeriod = EducationPeriodEntity(dbContact.educationStart, dbContact.educationEnd)
        )
    }
}