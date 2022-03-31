package com.kirilov.kontur_test.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kirilov.kontur_test.domain.interactor.GetContactsUseCase
import com.kirilov.kontur_test.presentation.Event
import com.kirilov.kontur_test.presentation.mapper.ContactsListModelMapper
import com.kirilov.kontur_test.presentation.model.ContactModel
import com.kirilov.kontur_test.presentation.model.ContactsListModel
import javax.inject.Inject

class ContactsListViewModel @Inject constructor(
    private val getContactsUseCase: GetContactsUseCase
) : ViewModel(), ContactModel.ItemClickListener {

    val isLoading = MutableLiveData(false)
    var searchQuery = ""

    val contactsList = MutableLiveData<ContactsListModel>()

    private val _selectContactEvent = MutableLiveData<Event<ContactModel>>()
    val selectContactEvent: LiveData<Event<ContactModel>>
        get() = _selectContactEvent

    private val _showSnackBar = MutableLiveData<Event<Boolean>>()
    val showSnackBar: LiveData<Event<Boolean>>
        get() = _showSnackBar

    fun getContacts(isRefresh: Boolean = false) {
        if (searchQuery.isEmpty()) {
            isLoading.value = true
            getContactsUseCase.execute(
                isRefresh,
                onSuccess = {
                    contactsList.value = ContactsListModelMapper().transformFromDomain(it)
                },
                onError = {
                    _showSnackBar.value = Event(true)
                    it.printStackTrace()
                },
                onFinished = {
                    isLoading.value = false
                }
            )
        }
    }

    override fun onClick(item: ContactModel) {
        _selectContactEvent.value = Event(item)
    }

    fun getContactsForView(): List<ContactModel> {
        return contactsList.value?.filter { model ->
                model.name.lowercase().contains(searchQuery)
                        || clearPhoneNumber(model.phone) == clearPhoneNumber(searchQuery)
            }?.toList() ?: emptyList()
    }

    private fun clearPhoneNumber(phoneNumber: String): String =
        phoneNumber.replace(" ", "")
            .replace("+", "")
            .replace("-", "")
            .replace("(", "")
            .replace(")", "")
}