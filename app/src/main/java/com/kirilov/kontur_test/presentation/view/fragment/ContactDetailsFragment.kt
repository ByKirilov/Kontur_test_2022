package com.kirilov.kontur_test.presentation.view.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.kirilov.kontur_test.R
import com.kirilov.kontur_test.databinding.FragmentContactDetailsBinding
import com.kirilov.kontur_test.databinding.FragmentContactsListBinding
import com.kirilov.kontur_test.presentation.extension.getAppComponent
import com.kirilov.kontur_test.presentation.model.ContactModel
import com.kirilov.kontur_test.presentation.viewmodel.ContactDetailsViewModel

class ContactDetailsFragment : Fragment() {

    lateinit var binding: FragmentContactDetailsBinding

    private val viewModel: ContactDetailsViewModel by viewModels {
        getAppComponent().viewModelFactory()
    }
    private val navController: NavController by lazy { NavHostFragment.findNavController(this) }

    private val args by navArgs<ContactDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactDetailsBinding.inflate(layoutInflater)

        viewModel.getContactById(args.contactId)

        prepareUI()
        return binding.root
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun prepareUI() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.toolbar.setNavigationOnClickListener { navController.navigateUp() }

        binding.phoneNumber.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${viewModel.contact.value?.phone}")
            }
            if (intent.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(intent)
            }
        }
    }
}