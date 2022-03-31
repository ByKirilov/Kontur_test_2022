package com.kirilov.kontur_test.presentation.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.kirilov.kontur_test.R
import com.kirilov.kontur_test.databinding.FragmentContactsListBinding
import com.kirilov.kontur_test.domain.model.ContactsList
import com.kirilov.kontur_test.domain.model.EducationPeriod
import com.kirilov.kontur_test.domain.model.Temperament
import com.kirilov.kontur_test.presentation.ContactsApp
import com.kirilov.kontur_test.presentation.Event
import com.kirilov.kontur_test.presentation.extension.getAppComponent
import com.kirilov.kontur_test.presentation.extension.viewModel
import com.kirilov.kontur_test.presentation.model.ContactModel
import com.kirilov.kontur_test.presentation.model.ContactsListModel
import com.kirilov.kontur_test.presentation.view.adapter.ContactsListAdapter
import com.kirilov.kontur_test.presentation.viewmodel.ContactsListViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ContactsListFragment : Fragment() {

    @Inject
    lateinit var contactsListAdapter: ContactsListAdapter

    lateinit var binding: FragmentContactsListBinding
    private val navController: NavController by lazy { NavHostFragment.findNavController(this) }

    private val viewModel: ContactsListViewModel by viewModels {
        getAppComponent().viewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getAppComponent().inject(this)
        viewModel.contactsList.observe(viewLifecycleOwner, ::onContactsListChanged)
        viewModel.selectContactEvent.observe(viewLifecycleOwner, ::openContact)
        viewModel.showSnackBar.observe(viewLifecycleOwner, ::onShowSnackBarEvent)

        binding = FragmentContactsListBinding.inflate(layoutInflater)
        prepareUI()
        viewModel.getContacts()
        return binding.root
    }

    private fun prepareUI() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.contactsRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = contactsListAdapter
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }
        contactsListAdapter.clickListener = viewModel

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            viewModel.getContacts(isRefresh = true)
        }

        binding.search.apply {
            setText(viewModel.searchQuery.orEmpty())
            addTextChangedListener {
                viewModel.searchQuery = it?.toString() ?: ""
                contactsListAdapter.collection = viewModel.getContactsForView()
            }
        }
        binding.clearSearch.setOnClickListener {
            viewModel.searchQuery = ""
            binding.search.setText("")
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun onContactsListChanged(contactsList: ContactsListModel?) {
        contactsListAdapter.collection = viewModel.getContactsForView()
    }

    private fun openContact(contact: Event<ContactModel>?) {
        contact?.getContentIfNotHandled()?.let {
            navController.navigate(
                ContactsListFragmentDirections.actionContactsListFragment2ToContactDetailsFragment(
                    it.id
                )
            )
        }
    }

    private fun onShowSnackBarEvent(showSnackBar: Event<Boolean>?) {
        showSnackBar?.getContentIfNotHandled()?.let {
            if (it) {
                Snackbar.make(
                    requireView(),
                    requireContext().getText(R.string.no_internet_message),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }
}