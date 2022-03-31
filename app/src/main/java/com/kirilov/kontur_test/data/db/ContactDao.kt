package com.kirilov.kontur_test.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Single

@Dao
interface ContactDao {

    @Query("SELECT * FROM contacts_table")
    fun getAllContacts(): Single<List<ContactDBEntity>>

    @Query("SELECT * FROM contacts_table WHERE id LIKE :id LIMIT 1")
    fun getContactById(id: String): Single<ContactDBEntity?>

    @Query("DELETE FROM contacts_table")
    fun deleteAll()

    @Insert
    fun insertAll(contacts: List<ContactDBEntity>)
}