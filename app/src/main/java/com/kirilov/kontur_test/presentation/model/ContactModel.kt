package com.kirilov.kontur_test.presentation.model

import android.view.View
import com.kirilov.kontur_test.domain.model.EducationPeriod
import com.kirilov.kontur_test.domain.model.Temperament

class ContactsListModel(list: List<ContactModel>) : List<ContactModel> by list

data class ContactModel(
    val id: String,
    val name: String,
    val phone: String,
    val height: String,
    val biography: String,
    val temperament: String,
    val educationPeriod: String
) {
    interface ItemClickListener {
        fun onClick(item: ContactModel)
    }
}