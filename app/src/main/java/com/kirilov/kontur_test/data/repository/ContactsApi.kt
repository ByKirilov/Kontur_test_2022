package com.kirilov.kontur_test.data.repository

import com.kirilov.kontur_test.data.entity.ContactEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ContactsApi {
    @GET("SkbkonturMobile/mobile-test-droid/master/json/generated-0{id}.json")
    fun getContacts(@Path("id") id: Int): Single<List<ContactEntity>>
}