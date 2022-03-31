package com.kirilov.kontur_test.data.entity

import com.google.gson.annotations.SerializedName

data class ContactsEntity(
    val contacts: List<ContactEntity>
)

data class ContactEntity(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("height")
    val height: Float,
    @SerializedName("biography")
    val biography: String,
    @SerializedName("temperament")
    val temperament: String,
    @SerializedName("educationPeriod")
    val educationPeriod: EducationPeriodEntity
)

data class EducationPeriodEntity(
    @SerializedName("start")
    val start: String,
    @SerializedName("end")
    val end: String
)
