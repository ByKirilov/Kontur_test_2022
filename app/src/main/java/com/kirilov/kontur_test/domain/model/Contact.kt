package com.kirilov.kontur_test.domain.model

class ContactsList(list: List<Contact>) : List<Contact> by list

data class Contact(
    val id: String,
    val name: String,
    val phone: String,
    val height: Float,
    val biography: String,
    val temperament: Temperament?,
    val educationPeriod: EducationPeriod
)

data class EducationPeriod(
    val start: String,
    val end: String
)

enum class Temperament(val value: String) {
    MELANCHOLIC("melancholic"),
    PHLEGMATIC("phlegmatic"),
    SANGUINE("sanguine"),
    CHOLERIC("choleric");

    companion object {
        private val map = values().associateBy(Temperament::value)
        fun fromString(value: String) = map[value]
    }
}