package com.kirilov.kontur_test.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts_table")
data class ContactDBEntity(
    @PrimaryKey
    @ColumnInfo(name="id")
    val id: String,

    @ColumnInfo(name="name")
    val name: String,

    @ColumnInfo(name="phone")
    val phone: String,

    @ColumnInfo(name="height")
    val height: Float,

    @ColumnInfo(name="biography")
    val biography: String,

    @ColumnInfo(name="temperament")
    val temperament: String,

    @ColumnInfo(name="educationStart")
    val educationStart: String,

    @ColumnInfo(name="educationEnd")
    val educationEnd: String,
)